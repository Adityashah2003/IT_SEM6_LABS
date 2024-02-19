import re

states = "Mississippi Alabama Texas Massachusetts Kansas"
statesList = []

statesList.append(re.search(r'\b\w*xas\b', states, re.I).group())

statesList.append(re.search(r'\bk\w*s\b', states, re.I).group())

statesList.append(re.search(r'\bM\w*s\b', states, re.I).group())

statesList.append(re.search(r'\b\w*a\b', states, re.I).group())

statesList.append(re.search(r'^M\w*', states, re.I).group())

print(statesList)