import pandas as pd
import numpy as np
from itertools import combinations

with open("transactions.txt", "r") as file:
    data = [line.strip().split() for line in file.readlines()]

df = pd.DataFrame(data)

Candidate_set = []
Frequent_set = []
items = pd.unique(df.values.ravel('K'))
items = items[~pd.isnull(items)]
min_support = int(input("Enter Min Support: "))

for iterno in range(1, len(items)):
    Count = {}
    intermediate = []

    if iterno == 1:
        Candidate_set.append(items)
        for txn in Candidate_set[iterno - 1]:
            ctr = 0
            for val in df.values:
                if txn in val:
                    ctr += 1
            Count[txn] = ctr
        print(f"Candidate Set C[{iterno}]: {Candidate_set[iterno - 1]}")
    else:
        Candidate_set.append(list(combinations(np.unique(np.array(Frequent_set[iterno - 2]).ravel('K')), iterno)))
        print(f"Candidate Set C[{iterno}]: {Candidate_set[iterno - 1]}")
        for txn in Candidate_set[iterno - 1]:
            ctr = 0
            for val in df.values:
                if all(i in val for i in txn):
                    ctr += 1
            Count[txn] = ctr

    for k in Count.keys():
        if Count[k] >= min_support:
            intermediate.append(k)

    if intermediate == []:
        print(f"Frequent Set L[{iterno}]: {Frequent_set}")
        break

    Frequent_set.append(intermediate)
    print(f"Frequent Set L[{iterno}]: {Frequent_set[iterno-1]}")

if len(Frequent_set) > 0:
    Frequent_set = Frequent_set[1:]

def generate_association_rules(Frequent_set, min_confidence):
    Rules = []
    for k in range(1, len(Frequent_set)):
        for itemset in Frequent_set[k]:
            m = len(itemset)
            for i in range(1, m + 1):  # Change here
                lefts = list(combinations(itemset, i))
                for left in lefts:
                    left = set(left)
                    right = set(itemset) - left
                    leftcount = 0
                    bothcount = 0
                    for val in df.values:
                        if left.issubset(set(val)):
                            leftcount += 1
                        if set(itemset).issubset(set(val)):
                            bothcount += 1
                    epsilon = 1e-9
                    confidence = bothcount / (leftcount + epsilon)
                    if confidence >= min_confidence:
                        Rules.append((str(left), str(right), confidence))
    return Rules


min_confidence = 0.6
rules = generate_association_rules(Frequent_set, min_confidence)

for rule in rules:
    print(f"Rule: {rule[0]} => {rule[1]}, Confidence: {rule[2]}")
