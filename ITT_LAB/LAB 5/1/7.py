def fibonacci(n):
    if n <= 1:
        return n
    else:
        return fibonacci(n-1) + fibonacci(n-2)

n = int(input("Enter the number of terms: "))
print("Fibonacci sequence: ", [fibonacci(i) for i in range(n)])
