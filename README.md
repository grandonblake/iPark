# iPark
A simple locally-hosted Automated Parking with Cashless Payment System using Java and LiveSQL.

This project was a group effort that was submitted on Jun 8, 2022 as a Final Project requirement during my 2nd year in College.

## About
iPark is a cashless payment platform that also offers an automated parking system. iPark provides customers with an easy and secure cashless payment platform through money transfers via credit card, paypal, or gcash. It contains a virtual wallet which customers could cash in for their future parking expenses. It can store the necessary details of their payment methods once the users have registered those. 

The overall project aims to improve the quality of parking services through lessening the time consumed by parking itself, and clearing long lines for queues in parking and payment booths. With the ongoing pandemic, it will also contribute in lessening the spread of the COVID-19 virus through a less physical contact transaction between the staff and its customers.

## Project Objectives
The system is focused on helping users to park their vehicles in an automated way. Instead of waiting in long lines before parking, they may use the application to queue up beforehand. Additionally, it brings convenience to customers in terms of finding parking space since the application can notify customers if there are slots for them to park.
Specifically,
- To apply cashless payment for parking services
- To reduce the cost of labor(management).
- To decrease physical contact.
- To be more time efficient.
- To make parking lots’ space efficient.

## Problem Statement
**ADMIN.** Parking spaces tend to not have data regarding available and occupied parking slots. With that, they may not be able to manage the number of customers going inside and outside the parking spaces which may cause overcrowding. Using the iPark application, administrators may be able to track the number of available and occupied slots which can be used to limit the number of customers going inside parking spaces.

**CUSTOMER.** As customers, for parking management, it is time-consuming and troublesome to find a parking slot especially in malls which have multiple-story parking spaces. Using the iPark app, it will automatically reserve a slot everytime there is an available one, and it will notify the user the necessary details of the slot such as the slot number and location etc.

**QUEUE MANAGEMENT.** Parking spaces tend to have long lines of vehicles outside its area which can cause overcrowding within the vicinity. With the app, users may be able to queue themselves via app on a parking space they will choose ahead of time. Once a slot becomes available, the user in front of the queue will be the first to be reserved on that particular slot.

**PAYMENT.** Payment booths inside parking spaces/areas tend to have long lines also which can cause overcrowding inside the area. With the app, the user have the option to pay for their parking service via credit/debit card or online banking for their own convenience.

**PHYSICAL CONTACT.** Even parking in parking spaces involves physical contact between the customer and the employees. As observed in different parking spaces, cards are given to customers once they enter the parking space which indicates the time they entered the area. It is also given back once they pay and leave the area, and the cycle continues. As the pandemic still exists, this could still be a medium for the virus to spread. With iPark, all necessary processes to park can be accessed inside the application such as the details of the customer, and payment.

## Significance of the Study
The system of this project will redound to the benefit of the following individuals or group of people:

- **CUSTOMERS.** Customers may use the app to have a better and safer parking experience as it provides a systematic and less physical contact way of parking. It also brings convenience to payment for parking service since it offers a cashless payment platform via money transfers.
- **ADMIN.** Admins may be able to record or track vehicles and accounts going inside and outside the parking area. As well as, the daily number  of customers.
- **ESTABLISHMENTS.** Establishments that have parking spaces may take advantage of the app which could give parking service to its customers.
- **FUTURE DEVELOPERS.** Future developers may be able to use this study as a reference if they are conducting a similar study/ or create a similar application.

## System Analysis and Design
### Context Diagram
<img src="/screenshots/ContextDiagram.png" width=50% height=50%>

### DFD Level 0
<img src="/screenshots/DFDLevel0.png" width=50% height=50%>

### Entity Relationship Diagram
<img src="/screenshots/EntityRelationshipDiagram.png" width=50% height=50%>

### Hierarchical Input Process Output
<img src="/screenshots/Hierarchical Input Process Output.png" width=50% height=50%>

### Database Design
<img src="/screenshots/DatabaseDesign.png" width=50% height=50%>

## Project Screenshots
### Customer UIs
<img src="/screenshots/CustomerUIs/LoginScreen.png" width=30% height=30%>
<img src="/screenshots/CustomerUIs/SignUpScreen.png" width=30% height=30%>
<img src="/screenshots/CustomerUIs/AccountDetailsScreen.png" width=30% height=30%>
<img src="/screenshots/CustomerUIs/HomeScreen.png" width=30% height=300%>
<img src="/screenshots/CustomerUIs/HomeScreenParked.png" width=30% height=30%>
<img src="/screenshots/CustomerUIs/PaymentScreen.png" width=30% height=30%>
<img src="/screenshots/CustomerUIs/CashInScreen.png" width=30% height=30%>
<img src="/screenshots/CustomerUIs/AddPaymentMethodScreen.png" width=30% height=30%>
<img src="/screenshots/CustomerUIs/AfterPaymentScreen.png" width=30% height=30%>
<img src="/screenshots/CustomerUIs/ReceiptScreen.png" width=30% height=30%>

### Admin UIs
<img src="/screenshots/AdminUIs/HomeScreen.png" width=30% height=30%>
<img src="/screenshots/AdminUIs/MapScreen.png" width=30% height=30%>
<img src="/screenshots/AdminUIs/QueueScreen.png" width=30% height=30%>
<img src="/screenshots/AdminUIs/TransactionRecordsScreen.png" width=30% height=30%>
<img src="/screenshots/AdminUIs/FilterScreen.png" width=30% height=30%>
