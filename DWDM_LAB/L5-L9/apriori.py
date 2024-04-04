import pandas as pd
import numpy as np
from itertools import combinations
from collections import defaultdict

data = defaultdict(list)

with open('L5-L9/transactions.txt', 'r') as file:
    for line in file:
        items = line.strip().split()
        transaction_id = 'T' + str(len(data) + 1)  
        data[transaction_id] = items

is_integers = all(isinstance(item, int) for transaction in data.values() for item in transaction)
if is_integers:
    print("Transactions contain integers.")
else:
    print("Transactions contain characters.")

unique_items = set()
for transaction in data.values():
    unique_items.update(transaction)

init = sorted(unique_items)
print("Unique Items:", init)

min_support_count = 2

# Calculate support count for each item
item_counts = {}
for item in init:
    count = sum(item in transaction for transaction in data.values())
    item_counts[item] = count

print("C1:")
for item, count in item_counts.items():
    print(f"[{item}]: {count}")

# Find frequent 1-itemsets (L1)
frequent_1_itemsets = {frozenset([item]): count for item, count in item_counts.items() if count >= min_support_count}

print("\nL1:")
for itemset, count in frequent_1_itemsets.items():
    print(f"{list(itemset)}: {count}")

# Generate frequent itemsets and association rules
frequent_itemsets = frequent_1_itemsets
for size in range(2, len(init) + 1):
    candidate_itemsets = [set(itemset) for itemset in combinations(init, size)]
    frequent_itemsets_next = {}
    for candidate in candidate_itemsets:
        support_count = sum(all(item in transaction for item in candidate) for transaction in data.values())
        if support_count >= min_support_count:
            frequent_itemsets_next[frozenset(candidate)] = support_count
    if not frequent_itemsets_next:
        break
    frequent_itemsets.update(frequent_itemsets_next)

print("\nFrequent Itemsets:")
for itemset, count in frequent_itemsets.items():
    print(f"{list(itemset)}: {count}")

# Generate association rules
print("\nAssociation Rules:")
for itemset in frequent_itemsets:
    if len(itemset) > 1:
        for size in range(1, len(itemset)):
            for subset in combinations(itemset, size):
                antecedent = frozenset(subset)
                consequent = itemset - antecedent
                antecedent_count = frequent_itemsets.get(antecedent, 0)
                if antecedent_count != 0:
                    confidence = frequent_itemsets[itemset] / antecedent_count
                    if confidence >= 0.5:  # Set your confidence threshold
                        print(f"{list(antecedent)} -> {list(consequent)}: {confidence}")
