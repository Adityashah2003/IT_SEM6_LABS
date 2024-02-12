def list_sum(lst):
    if not lst:
        return 0
    else:
        return lst[0] + list_sum(lst[1:])

lst = list(map(int, input("Enter the list elements separated by space: ").split()))
print("Sum: ", list_sum(lst))
