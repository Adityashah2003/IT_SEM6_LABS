def caesar_encrypt(text, shift):
    result = ""

    for char in text:
        if char.isalpha():
            ascii_offset = ord('a') if char.islower() else ord('A')
            encrypted_char = chr((ord(char) - ascii_offset + shift) % 26 + ascii_offset)
            result += encrypted_char
        else:
            result += char

    return result

text = input("Enter the text to encrypt: ")
shift = int(input("Enter the shift value: "))
print("Encrypted text: ", caesar_encrypt(text, shift))
