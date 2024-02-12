import string

def remove_punctuation(s):
    return s.translate(str.maketrans('', '', string.punctuation))

s = input("Enter a string: ")
print(remove_punctuation(s))
