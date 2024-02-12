def generate_primes(m, n):
    primes = []
    for num in range(m, n + 1):
        if num > 1:
            for i in range(2, num):
                if (num % i) == 0:
                    break
            else:
                primes.append(num)
    return primes

m = int(input("Enter the start number: "))
n = int(input("Enter the end number: "))
print("Prime numbers: ", generate_primes(m, n))
