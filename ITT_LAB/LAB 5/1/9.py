def set_operations(A, B):
    print(f"Union: {A | B}")
    print(f"Intersection: {A & B}")
    print(f"Difference: {A - B}")
    print(f"Symmetric Difference: {A ^ B}")

A = set(map(int, input("Enter elements of set A separated by space: ").split()))
B = set(map(int, input("Enter elements of set B separated by space: ").split()))
set_operations(A, B)
