def remove_duplicates(lst):
    result = []
    for element in lst:
        if element not in result:
            result.append(element)
    return result

lst = list(map(int, input("Enter the list elements separated by space: ").split()))
print("List without duplicates: ", remove_duplicates(lst))
