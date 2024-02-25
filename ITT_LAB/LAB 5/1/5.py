def sum_natural(n):
    if n == 1:
        return 1
    else:
        return n + sum_natural(n-1)

n = int(input("Enter a natural number: "))
print("Result: ", sum_natural(n))
