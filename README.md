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

![image](https://github.com/user-attachments/assets/4637f99f-484e-4871-b532-8a1f181cf963)


no data present **in both the table, as it gets rolled back.**

![image](https://github.com/user-attachments/assets/312e6db3-eb5b-420c-9c94-d21d361e4d8e)

**case 3 : with transactional (Propagation Required_NEW)**

we have a requirement that, **we need to save log of request in audit table, be it success case or exception case.**
In webservice, **saveEmployee() method have propagation.REQUIRED**, this is alway use existing transaction if exists.
so We create **AuditService in that saveInAuditTable() method will have propagation.REQUIRED_NEW** , which will always **create new transaction**.
so in exception case, **saveEmployee() transaction will get rolled back properly** and it's failure log will get stored in audit table with new **transaction as propagation.REQUIRED_NEW**

![image](https://github.com/user-attachments/assets/58d50b7e-a235-42dc-9c73-097c56fa6328)

modify webService

![image](https://github.com/user-attachments/assets/18efb623-23fe-440d-b92d-9ab4b90d564c)

created auditService

![image](https://github.com/user-attachments/assets/250d6962-361c-450c-ae38-3774bb25d296)


transactions Logs

![image](https://github.com/user-attachments/assets/e503894d-913e-4974-ab28-bc636f9c872b)

**in db, in case of exception, employee & address table data gets rolled back & in audit table there is a log entry**

![image](https://github.com/user-attachments/assets/679ce3c9-0581-4cf8-946a-163557003ea5)

