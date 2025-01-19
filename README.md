we have 2 tables, Employee & Address.
while receiving request, we save data first in employee table then in Address table.

Case 1 : without transaction (save data in employee table, & exception while saving in address table. data not rolled back from employee table)
in address table, we have statecode field with varchar max length 2, so pass value in statecode length more than 2 to create exception

![image](https://github.com/user-attachments/assets/1b38ef1a-c167-4d0b-a6f1-471e4bec3919)

![image](https://github.com/user-attachments/assets/67dafecd-8097-4385-a053-7032c0bb7ce2)
