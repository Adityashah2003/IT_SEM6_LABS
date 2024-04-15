from itertools import combinations
from collections import defaultdict

data = []

with open("transactions.txt", 'r') as file:
    for line in file:
        row = line.strip().split(" ")
        data.append(set(row))  

min_sup = 2
counts = defaultdict(int)

all_items = set(['1', '2', '3', '4', '5']) 

c1 = {}
l1 = []
for row in data:
    for col in row:
        if col in c1:
            c1[col] += 1
        else:
            c1[col] = 1
for i, count in c1.items():
    if count >= min_sup:
        l1.append(i)
print("Frequent items (C1):", l1)

for i in range(2, 6):
    for transaction in data:
        for itemset in combinations(sorted(transaction), i):
            counts[itemset] += 1
        
        for itemset in combinations(all_items - transaction, i):
            counts[itemset] += 0  

for itemset, count in counts.items():
    if count >= min_sup and len(itemset) == 2:
        print(f"Itemset: {itemset}, Count: {count}")
