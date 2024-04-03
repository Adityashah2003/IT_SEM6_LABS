import csv
import random
import numpy as np
import math

def read_data_from_csv(filename):
    with open(filename, 'r') as file:
        reader = csv.reader(file)
        data = list(reader)
    features = data[0]  # Extract feature names
    data = data[1:]      # Remove feature names from data
    return features, data

def train_test_split(data, split_ratio):
    train_size = int(len(data) * split_ratio)
    train_data = []
    test_data = list(data)
    while len(train_data) < train_size:
        index = random.randrange(len(test_data))
        train_data.append(test_data.pop(index))
    return train_data, test_data

def entropy(y):
    unique, counts = np.unique(y, return_counts=True)
    probabilities = counts / len(y)
    entropy_val = -np.sum(probabilities * np.log2(probabilities))
    return entropy_val

def information_gain(X, y, feature_idx):
    entropy_parent = entropy(y)
    unique_values = np.unique(X[:, feature_idx])
    weighted_entropy_children = 0
    for value in unique_values:
        indices = np.where(X[:, feature_idx] == value)[0]
        child_entropy = entropy(y[indices])
        weight = len(indices) / len(y)
        weighted_entropy_children += weight * child_entropy
    information_gain_val = entropy_parent - weighted_entropy_children
    return information_gain_val

def id3(X, y):
    if len(np.unique(y)) == 1:
        return {'label': y[0]}
    if X.shape[1] == 0:
        return {'label': np.bincount(y).argmax()}
    best_feature = np.argmax([information_gain(X, y, i) for i in range(X.shape[1])])
    tree = {'feature': best_feature}
    for value in np.unique(X[:, best_feature]):
        indices = np.where(X[:, best_feature] == value)[0]
        X_subset, y_subset = X[indices], y[indices]
        subtree = id3(X_subset, y_subset)
        tree[value] = subtree
    return tree

def split_info(X, y, feature_idx):
    unique_values = np.unique(X[:, feature_idx])
    split_info_val = 0
    for value in unique_values:
        indices = np.where(X[:, feature_idx] == value)[0]
        proportion = len(indices) / len(y)
        split_info_val -= proportion * math.log2(proportion)
    return split_info_val

def gain_ratio(X, y, feature_idx):
    info_gain = information_gain(X, y, feature_idx)
    split_info_val = split_info(X, y, feature_idx)
    if split_info_val == 0:  # Avoid division by zero
        return 0
    gain_ratio_val = info_gain / split_info_val
    return gain_ratio_val

def c45(X, y):
    if len(np.unique(y)) == 1:
        return {'label': y[0]}
    if X.shape[1] == 0 or np.all(X == X[0, :]):
        return {'label': np.bincount(y).argmax()}
    
    best_feature = np.argmax([gain_ratio(X, y, i) for i in range(X.shape[1])])
    tree = {'feature': best_feature}
    
    unique_values = np.unique(X[:, best_feature])
    for value in unique_values:
        indices = np.where(X[:, best_feature] == value)[0]
        X_subset, y_subset = X[indices], y[indices]
        
        subtree = None
        if len(X_subset) == 0:
            # Handle missing feature value by assigning majority label
            subtree = {'label': np.bincount(y).argmax()}
        else:
            subtree = c45(X_subset, y_subset)
        
        tree[value] = subtree
    
    # Check if all possible feature values are covered
    for value in np.unique(X[:, best_feature]):
        if value not in tree:
            # Add missing feature value with majority label
            tree[value] = {'label': np.bincount(y).argmax()}
    
    return tree

def gini_index(y):
    unique, counts = np.unique(y, return_counts=True)
    probabilities = counts / len(y)
    gini_val = 1 - np.sum(probabilities ** 2)
    return gini_val

def gini_gain(X, y, feature_idx):
    total_gini = gini_index(y)
    unique_values = np.unique(X[:, feature_idx])
    weighted_gini_children = 0
    for value in unique_values:
        indices = np.where(X[:, feature_idx] == value)[0]
        child_gini = gini_index(y[indices])
        weight = len(indices) / len(y)
        weighted_gini_children += weight * child_gini
    gini_gain_val = total_gini - weighted_gini_children
    return gini_gain_val

def cart(X, y):
    if len(np.unique(y)) == 1:
        return {'label': y[0]}
    if X.shape[1] == 0:
        return {'label': np.bincount(y).argmax()}
    best_feature = np.argmax([gini_gain(X, y, i) for i in range(X.shape[1])])
    tree = {'feature': best_feature}
    for value in np.unique(X[:, best_feature]):
        indices = np.where(X[:, best_feature] == value)[0]
        X_subset, y_subset = X[indices], y[indices]
        subtree = cart(X_subset, y_subset)
        tree[value] = subtree
    return tree

def predict(tree, sample):
    if 'feature' in tree:
        value = sample[tree['feature']]
        if value in tree:
            return predict(tree[value], sample)
        else:
            print("No key for value:", value)
            print("Tree:", tree)
    else:
        print("No 'feature' key in tree")
        print("Tree:", tree)
    return tree['label']


def accuracy_metric(actual, predicted):
    correct = sum(1 for i in range(len(actual)) if actual[i] == predicted[i])
    return correct / float(len(actual)) * 100.0

def evaluate_algorithm(dataset, algorithm):
    train_set, test_set = train_test_split(dataset, 0.8)
    X_train = np.array([row[:-1] for row in train_set])
    y_train = np.array([row[-1] for row in train_set])
    X_test = np.array([row[:-1] for row in test_set])
    y_test = np.array([row[-1] for row in test_set])
    tree = algorithm(X_train, y_train)
    predictions = [predict(tree, sample) for sample in X_test]
    accuracy = accuracy_metric(y_test, predictions)
    return accuracy

filename = 'L5-L9/data.csv'
features, dataset = read_data_from_csv(filename)  # Extract features and data

print("Features:", features)
print("Data:", dataset)

# Convert feature names to numerical indices
feature_indices = {feature: i for i, feature in enumerate(features)}

print("Feature Indices:", feature_indices)

# ID3
id3_accuracy = evaluate_algorithm(dataset, id3)
print('ID3 Accuracy:', id3_accuracy)

# C4.5
c45_accuracy = evaluate_algorithm(dataset, c45)
print('C4.5 Accuracy:', c45_accuracy)

# CART
cart_accuracy = evaluate_algorithm(dataset, cart)
print('CART Accuracy:', cart_accuracy)
