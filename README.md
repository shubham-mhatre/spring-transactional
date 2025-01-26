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



**Case 4 : with transactional (Propagation Mandatory)**
we have requirement that we need to validate user proofs, but **we need to use current transaction only** & **failure in validating proof should not affect original save user details transaction**
in that case we can go for propagation mandatory for userIdentityService


![image](https://github.com/user-attachments/assets/974ab306-8526-4ddd-bc13-14d18538265c)


**identityLogService**
![image](https://github.com/user-attachments/assets/a2bec5cd-0931-4486-b04e-2d972afe47e0)



![image](https://github.com/user-attachments/assets/6f5c6db0-26f1-4fe6-b61f-06383b6fb026)


**case 5 : with transactional (propagation NEVER)**
propagation never should always be executed outside of transaction, if it method marked with propagation never called from existing transaction it will throw exception.
requirement is send notification to user, that can be done outside of transaction. so use progation never

![image](https://github.com/user-attachments/assets/9e5a37ba-93b2-4443-beac-e761cc8fa99e)


![image](https://github.com/user-attachments/assets/e1755fd2-ae95-48e0-b798-f6d3439b7a35)


![image](https://github.com/user-attachments/assets/b5643130-c34d-4539-bc9c-b66ea61fd6ac)


**Case 6 : with transactional (propagation NOT SUPPORTED)**
when transaction is not required and even if found then don't throw any exception, just suspend it and proceed.
requirement is send recommendations to employee to take further steps after onboarding, that can be perform using transaction not support propagation

![image](https://github.com/user-attachments/assets/22d22443-9819-4810-9c69-ac5fe05ab865)


![image](https://github.com/user-attachments/assets/7a13b520-b046-4069-85d3-696b162af23f)

![image](https://github.com/user-attachments/assets/b5e07648-899b-4dd3-9060-d64856e75365)


