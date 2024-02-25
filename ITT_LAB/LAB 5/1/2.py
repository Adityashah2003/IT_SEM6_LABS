def transpose_matrix(matrix):
    transposed_matrix = []
    for i in range(len(matrix[0])):
        transposed_row = []
        for row in matrix:
            transposed_row.append(row[i])
        transposed_matrix.append(transposed_row)
    return transposed_matrix

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


# n = int(input())
# matrix = []
# for i in range(n):
#     row = []
#     for j in range(n):
#         row.append(int(input()))
#     matrix.append(row)
    
# for i in range(0,n):
#     for j in range(0,n):
#         print(matrix[i][j])