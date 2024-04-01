import csv
import math
from collections import defaultdict

def load_dataset(filename):
    dataset = []
    with open(filename, 'r') as file:
        csv_reader = csv.reader(file)
        next(csv_reader)
        for row in csv_reader:
            dataset.append([float(x) for x in row])
    return dataset

def split_dataset(dataset, split_ratio):
    split_point = int(len(dataset) * split_ratio)
    return dataset[:split_point], dataset[split_point:]


def calculate_statistics(dataset):
    separated = defaultdict(list)
    for row in dataset:
        separated[row[-1]].append(row)
    statistics = {}
    for class_value, instances in separated.items():
        statistics[class_value] = {
            'prior': len(instances) / float(len(dataset)),
            'mean': [sum(attribute) / float(len(attribute)) for attribute in zip(*instances)],
        }
    for class_value, instances in separated.items():
        statistics[class_value]['stdev'] = [
            math.sqrt(sum((x - mean)**2 for x in attribute) / (len(attribute) - 1))
            for attribute, mean in zip(zip(*instances), statistics[class_value]['mean'])
        ]
    return statistics


def calculate_probability(x, mean, stdev):
    epsilon = 1e-6 
    exponent = math.exp(-(math.pow(x - mean, 2) / (2 * math.pow(stdev + epsilon, 2))))
    return (1 / (math.sqrt(2 * math.pi) * (stdev + epsilon))) * exponent


def calculate_class_probabilities(statistics, input_vector):
    probabilities = {}
    for class_value, class_stats in statistics.items():
        probabilities[class_value] = class_stats['prior']
        for i in range(len(class_stats['mean'])):
            mean, stdev = class_stats['mean'][i], class_stats['stdev'][i]
            x = input_vector[i]
            probabilities[class_value] *= calculate_probability(x, mean, stdev)
    return probabilities

def predict(statistics, input_vector):
    probabilities = calculate_class_probabilities(statistics, input_vector)
    best_label, best_prob = None, -1
    for class_value, probability in probabilities.items():
        if best_label is None or probability > best_prob:
            best_prob = probability
            best_label = class_value
    return best_label


def get_predictions(statistics, test_set):
    predictions = []
    for row in test_set:
        prediction = predict(statistics, row)
        predictions.append(prediction)
    return predictions

def get_accuracy(test_set, predictions):
    correct = 0
    for i in range(len(test_set)):
        if test_set[i][-1] == predictions[i]:
            correct += 1
    return (correct / float(len(test_set))) * 100.0

if __name__ == "__main__":
    filename = 'diabetes.csv'
    dataset = load_dataset(filename)
    split_ratio = 0.7
    training_set, testing_set = split_dataset(dataset, split_ratio)
    statistics = calculate_statistics(training_set)
    predictions = get_predictions(statistics, testing_set)
    accuracy = get_accuracy(testing_set, predictions)
    print('Accuracy:', accuracy)
