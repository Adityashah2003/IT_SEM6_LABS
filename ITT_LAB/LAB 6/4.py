import re

def find_and_replace(s, pattern, replacement):
    return re.sub(pattern, replacement, s)

s = input("Enter a string: ")
pattern = input("Enter the pattern to replace: ")
replacement = input("Enter the replacement string: ")
print(find_and_replace(s, pattern, replacement))
