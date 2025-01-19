we have 2 tables, Employee & Address.
while receiving request, we save data first in employee table then in Address table.

**Case 1 : without transaction** 

(save data in employee table, & exception while saving in address table. data not rolled back from employee table)
in address table, we have statecode field with varchar max length 2, so pass value in statecode length more than 2 to create exception

![image](https://github.com/user-attachments/assets/1b38ef1a-c167-4d0b-a6f1-471e4bec3919)

![image](https://github.com/user-attachments/assets/67dafecd-8097-4385-a053-7032c0bb7ce2)

![image](https://github.com/user-attachments/assets/23a8be7c-5011-4d6a-8612-00bdb99c6055)

due to **exception in address table no data inserted**, as below it is diplayed as 5*0 for address table.
and **as transactional is not implemented**, from **employee table, data is not rolled back**.

![image](https://github.com/user-attachments/assets/2cc1ddb6-2b93-428e-9d00-de62a849abf7)
