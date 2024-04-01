import csv
import random
import math
import numpy as np

def read_data_from_csv(filename):
    with open(filename, 'r') as file:
        reader = csv.reader(file)
        data = list(reader)
    return data

def train_test_split(data, split_ratio):
    train_size = int(len(data) * split_ratio)
    train_data = []
    test_data = list(data)
    while len(train_data) < train_size:
        index = random.randrange(len(test_data))
        train_data.append(test_data.pop(index))
    return train_data, test_data

def entropy(dataset):
    class_column = [row[-1] for row in dataset]
    class_counts = {}
    for label in class_column:
        if label not in class_counts:
            class_counts[label] = 0
        class_counts[label] += 1
    entropy_val = 0.0
    for count in class_counts.values():
        probability = count / len(dataset)
        entropy_val -= probability * math.log2(probability)
    return entropy_val

def split_dataset(dataset, column, value):
    left, right = [], []
    for row in dataset:
        if row[column] < value:
            left.append(row)
        else:
            right.append(row)
    return left, right

def gini_index(target_col):
    elements, counts = np.unique(target_col, return_counts=True)
    gini_val = 1 - np.sum([(counts[i] / np.sum(counts)) ** 2 for i in range(len(elements))])
    return gini_val

def gini_gain(data, split_attribute_name, target_name):
    total_gini = gini_index(data[target_name])
    vals, counts= np.unique(data[split_attribute_name], return_counts=True)
    weighted_gini = np.sum([(counts[i] / np.sum(counts)) * gini_index(data.where(data[split_attribute_name]==vals[i]).dropna()[target_name]) for i in range(len(vals))])
    gini_gain_val = total_gini - weighted_gini
    return gini_gain_val

def get_split(dataset):
    class_values = list(set(row[-1] for row in dataset))
    best_index, best_value, best_score, best_groups = 999, 999, 999, None
    for index in range(len(dataset[0]) - 1):
        for row in dataset:
            groups = split_dataset(dataset, index, row[index])
            gini = gini_index(groups)
            if gini < best_score:
                best_index, best_value, best_score, best_groups = index, row[index], gini, groups
    return {'index': best_index, 'value': best_value, 'groups': best_groups}

def to_terminal(group):
    outcomes = [row[-1] for row in group]
    return max(set(outcomes), key=outcomes.count)

def split(node, max_depth, min_size, depth):
    left, right = node['groups']
    del(node['groups'])
    # check for a no split
    if not left or not right:
        node['left'] = node['right'] = to_terminal(left + right)
        return
    # check for max depth
    if depth >= max_depth:
        node['left'], node['right'] = to_terminal(left), to_terminal(right)
        return
    # process left child
    if len(left) <= min_size:
        node['left'] = to_terminal(left)
    else:
        node['left'] = get_split(left)
        split(node['left'], max_depth, min_size, depth+1)
    # process right child
    if len(right) <= min_size:
        node['right'] = to_terminal(right)
    else:
        node['right'] = get_split(right)
        split(node['right'], max_depth, min_size, depth+1)

def build_tree(train, max_depth, min_size):
    root = get_split(train)
    split(root, max_depth, min_size, 1)
    return root

def predict(node, row):
    if row[node['index']] < node['value']:
        if isinstance(node['left'], dict):
            return predict(node['left'], row)
        else:
            return node['left']
    else:
        if isinstance(node['right'], dict):
            return predict(node['right'], row)
        else:
            return node['right']

def decision_tree(train, test, max_depth, min_size):
    tree = build_tree(train, max_depth, min_size)
    predictions = []
    for row in test:
        prediction = predict(tree, row)
        predictions.append(prediction)
    return predictions

def accuracy_metric(actual, predicted):
    correct = 0
    for i in range(len(actual)):
        if actual[i] == predicted[i]:
            correct += 1
    return correct / float(len(actual)) * 100.0

def evaluate_algorithm(dataset, algorithm, *args):
    train, test = train_test_split(dataset, 0.8)
    predicted = algorithm(train, test, *args)
    actual = [row[-1] for row in test]
    accuracy = accuracy_metric(actual, predicted)
    return accuracy

filename = 'data.csv'
dataset = read_data_from_csv(filename)

# Define hyperparameters for ID3 algorithm
id3_max_depth = 3
id3_min_size = 1

# Evaluate ID3 algorithm
id3_accuracy = evaluate_algorithm(dataset, decision_tree, id3_max_depth, id3_min_size)
print('ID3 Accuracy:', id3_accuracy)

# Define hyperparameters for C4.5 algorithm
c45_max_depth = 5
c45_min_size = 2

# Evaluate C4.5 algorithm
c45_accuracy = evaluate_algorithm(dataset, decision_tree, c45_max_depth, c45_min_size)
print('C4.5 Accuracy:', c45_accuracy)

# Define hyperparameters for CART algorithm
cart_max_depth = 4
cart_min_size = 3

# Evaluate CART algorithm
cart_accuracy = evaluate_algorithm(dataset, decision_tree, cart_max_depth, cart_min_size)
print('CART Accuracy:', cart_accuracy)
