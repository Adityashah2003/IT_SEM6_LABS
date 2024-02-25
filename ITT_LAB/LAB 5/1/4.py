def matrix_add(A, B):
    result = []
    for i in range(len(A)):
        row = []
        for j in range(len(A[0])):
            row.append(A[i][j] + B[i][j])
        result.append(row)
    return result

rows_A = int(input("Enter the number of rows for matrix A: "))
cols_A = int(input("Enter the number of columns for matrix A: "))
A = [[int(input()) for _ in range(cols_A)] for _ in range(rows_A)]

rows_B = int(input("Enter the number of rows for matrix B: "))
cols_B = int(input("Enter the number of columns for matrix B: "))
B = [[int(input()) for _ in range(cols_B)] for _ in range(rows_B)]
print("Result: ", matrix_add(A, B))