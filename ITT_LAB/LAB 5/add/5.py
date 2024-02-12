def reverse_list(lst):
    result = []
    for i in range(len(lst) - 1, -1, -1):
        result.append(lst[i])
    return result

lst = list(map(int, input("Enter the list elements separated by space: ").split()))
print("Reversed list: ", reverse_list(lst))
