def is_palindrome(lst):
    return lst == lst[::-1]

lst = list(map(int, input("Enter elements of the list separated by space: ").split()))
print("Is Palindrome: ", is_palindrome(lst))
