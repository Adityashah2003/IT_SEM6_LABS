import pandas as pd

def auto_map_attributes(data):
    attribute_maps = {}
    for column in data.columns:
        unique_values = data[column].unique()
        attribute_maps[column] = {value: index for index, value in enumerate(unique_values)}
    return attribute_maps

def algo(csv_file):
    # Read the CSV file
    data = pd.read_csv(csv_file)

    # Generate attribute mappings
    attribute_maps = auto_map_attributes(data)

    # Apply mappings to respective columns
    for column, attribute_map in attribute_maps.items():
        data[column] = data[column].map(attribute_map)

    mp = dict()
    for i in range(len(data)):
        row = data.iloc[i]
        y = row[-1]
        if (y not in mp):
            mp[y] = list()
        mp[y].append(row)
    
    # Testing data
    test = [2, 1, 0, 1]

    probYes = 1
    countYes = sum(data.iloc[:,-1] == 1)
    totalYes = len(data[data.iloc[:,-1] == 1])
    probYes *= countYes / totalYes
    for i in range(len(test)):
        count = sum(row[i] == test[i] for row in mp[1])
        total = len(mp[1])
        probYes *= count / total

    probNo = 1
    countNo = sum(data.iloc[:,-1] == 0)
    totalNo = len(data[data.iloc[:,-1] == 0])
    probNo *= countNo / totalNo
    for i in range(len(test)):
        count = sum(row[i] == test[i] for row in mp[0])
        total = len(mp[0])
        probNo *= count / total

    prob = probYes / (probYes + probNo)

    print("Probability of playing golf:", prob * 100, "%")

    return data

csv_file = 'L5-L9/data.csv'
dataset = algo(csv_file)

# print(dataset)
