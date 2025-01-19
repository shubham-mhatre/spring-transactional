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


**case 2 : with transactional (deafult propagation)**

(save data in employee table, and exception while saving data in address table, data will get rolled back from employee table as well)
deafault propagation : **Propagation.REQUIRED** means that if transaction already exist for all operation it will use same transaction, new transactional will only be created if no existing transaction.

![image](https://github.com/user-attachments/assets/5b4f074a-30d8-4445-a91b-b2627120bba2)

![image](https://github.com/user-attachments/assets/9d309c62-0aea-4a8f-a3a7-8a3c2b837d17)

no data present **in both the table, as it gets rolled back.**
![image](https://github.com/user-attachments/assets/312e6db3-eb5b-420c-9c94-d21d361e4d8e)

