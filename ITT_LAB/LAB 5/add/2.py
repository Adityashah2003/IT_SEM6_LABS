def bubble_sort(lst):
    for i in range(len(lst)):
        for j in range(len(lst) - 1):
            if lst[j] > lst[j + 1]:
                lst[j], lst[j + 1] = lst[j + 1], lst[j]
    return lst

lst = list(map(int, input("Enter the list elements separated by space: ").split()))
print("Sorted list: ", bubble_sort(lst))
