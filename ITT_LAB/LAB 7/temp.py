import sqlite3 as sq    

conn = sq.connect("es.db")
conn.execute('''DROP TABLE IF EXISTS travel''')

dest_id = int(input("Enter destination ID: "))
dest_name = input("Enter destination name: ")
country = input("Enter country: ")
city = input("Enter city: ")
duration = int(input("Enter duration: "))
cost = float(input("Enter cost: "))
budget = float(input("Enter budget: "))

# Validate data types
if not isinstance(dest_id, int):
    print("Destination ID must be an integer.")
elif not isinstance(dest_name, str):
    print("Destination name must be a string.")
elif not isinstance(country, str):
    print("Country must be a string.")
elif not isinstance(city, str):
    print("City must be a string.")
elif not isinstance(duration, int):
    print("Duration must be an integer.")
elif not isinstance(cost, float):
    print("Cost must be a float.")
elif not isinstance(budget, float):
    print("Budget must be a float.")
else: 
    pass

while True:
    if(dest_id<=0 or dest_id>=100):
        break
    else:   
        print("Enter a value between ")


# id = int(input("Enter id: "))
# dest_name = (input("Enter dest_name: "))
# country = (input("Enter country: "))
# city = (input("Enter city: "))
# cost = int(input("Enter cost: "))
# duration = int(input("Enter duration: "))
# budget = int(input("Enter budget: "))

conn.execute(''' Insert into travel values (1,'Las Vegas','US','Nevada','5','15000','10000') ''')
conn.execute(''' Insert into travel values (2, 'Paris', 'France', 'Paris', 7, 20000, 12000) ''')
conn.execute(''' Insert into travel values (3, 'Tokyo', 'Japan', 'Tokyo', 10, 25000, 15000) ''')

# ORDER BY AND AGG FUNCTIONS
# cursor = conn.execute(''' select * from travel  ''')
# cursor = conn.execute(''' select * from travel  order by dest_name asc''')
# cursor = conn.execute(''' select max(cost) from travel   ''')

# UPDATE THE VALUES
# j=0
# for i in cursor:
#     j+=1
#     if i[5]>i[6]:
#         conn.execute(f" update travel set budget ={i[5]} where dest_id={j} ")
# cursor = conn.execute(''' select * from travel  ''')
# for i in cursor:
#     print(i)




conn.close()
#commit
