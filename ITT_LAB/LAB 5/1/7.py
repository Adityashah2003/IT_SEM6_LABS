# def convert_num(n):
#     print(f"Binary: {bin(n)}")
#     print(f"Octal: {oct(n)}")
#     print(f"Hexadecimal: {hex(n)}")

# n = int(input("Enter a decimal number: "))
# convert_num(n)

def decimal_to_binary(n):
    binary = ""
    while n > 0:
        binary = str(n % 2) + binary
        n = n // 2
    return binary

def decimal_to_octal(n):
    octal = ""
    while n > 0:
        octal = str(n % 8) + octal
        n = n // 8
    return octal

def decimal_to_hexadecimal(n):
    hexadecimal = ""
    hex_values = {10: 'A', 11: 'B', 12: 'C', 13: 'D', 14: 'E', 15: 'F'}
    while n > 0:
        remainder = n % 16
        if remainder in hex_values:
            hexadecimal = hex_values[remainder] + hexadecimal
        else:
            hexadecimal = str(remainder) + hexadecimal
        n = n // 16
    return hexadecimal

n = int(input("Enter a decimal number: "))
print("Binary: ", decimal_to_binary(n))
print("Octal: ", decimal_to_octal(n))
print("Hexadecimal: ", decimal_to_hexadecimal(n))
