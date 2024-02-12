def transpose_matrix(matrix):
    return [[row[i] for row in matrix] for i in range(len(matrix[0]))]

rows = int(input("Enter the number of rows: "))
cols = int(input("Enter the number of columns: "))

matrix = []
print("Enter the entries row-wise:")

for i in range(rows):
    a =[]
    for j in range(cols):
        a.append(int(input()))
    matrix.append(a)

print("Original Matrix:")
for row in matrix:
    print(row)

transpose = transpose_matrix(matrix)

print("\nTranspose Matrix:")
for row in transpose:
    print(row)
