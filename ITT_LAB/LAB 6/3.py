def merge_mails(mails):
    return ", ".join(mails)

mails = input("Enter emails separated by space: ").split()
print(merge_mails(mails))
