def matrix_mul(A, B):
    result = [[0 for _ in range(len(B[0]))] for _ in range(len(A))]

    for i in range(len(A)):
        for j in range(len(B[0])):
            for k in range(len(B)):
                result[i][j] += A[i][k] * B[k][j]
    return result

rows_A = int(input("Enter the number of rows for matrix A: "))
cols_A = int(input("Enter the number of columns for matrix A: "))
A = [[int(input()) for _ in range(cols_A)] for _ in range(rows_A)]

rows_B = int(input("Enter the number of rows for matrix B: "))
cols_B = int(input("Enter the number of columns for matrix B: "))
B = [[int(input()) for _ in range(cols_B)] for _ in range(rows_B)]

print("Result: ", matrix_mul(A, B))
