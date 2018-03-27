drop table customer;
drop table manages;
drop table customerService;
drop table deliveryType;
drop table deliveryCompany;
drop table deliveryCompanyAddress;
drop table createOrder;
drop table updateExistingOrders;
drop table finishedOrders;
drop table packageContained;

create table customer{
customerID		CHAR(30),
name			VARCHAR(30) not null,
address		VARCHAR(50) not null,
email			VARCHAR(20),
phone#		CHAR(12) not null
methodOfPayment	CHAR(20),
primary key (customerID)
};

create table manages{
customerID	CHAR(30),
employeeID 	CHAR(30),
starttime	DATE not null,
endtime	DATE not null,
instance	CHAR(50) not null,
primary key (customerID, employeeID),
foreign key (customerID) REFERENCES Customer
ON DELETE 
ON UPDATE CASCADE
foreign key (employeeID) REFERENCES CustomerService
ON DELETE 
ON UPDATE CASCADE
};

create table customerService{
employeeID 	CHAR(30),
companyID	CHAR(30) not null,
name		CHAR(30) not null,
language	CHAR(30) not null,
primary key (employeeID)
foreign key (companyID) REFERENCES DeliveryCompany
ON DELETE
ON UPDATE CASCADE
};

create table deliveryType{
typename	CHAR(30),
rate		DOUBLE not null,
deliveryTime	CHAR(30) not null,
foreign key (typename)
};

create table deliveryCompany{
companyID	CHAR(30),
cname		CHAR(30) not null,
foreign key (companyID)
};

create table deliveryCompanyAddress{
companyID	CHAR(30),
branch#	INT not null,
caddress	CHAR(50) not null,
primary key (companyID, branch#)
foreign key (companyID) REFERENCES DeliveryCompany
};

create table createOrder{
orderID		CHAR(30)
customerID		CHAR(30),
companyID		CHAR(30) not null,
typename		CHAR(30) not null,
senderName		CHAR(30) not null,
senderAddress	CHAR(50) not null,
receiverName		CHAR(30) not null,
receiverAddress	CHAR(50) not null,
price			DOUBLE not null,
dateCreated		DATE not null,
expectedArrival	DATE not null,	
primary key (orderID, customerID, companyID, typename)
foreign key (companyID) REFERENCES DeliveryCompany
ON DELETE
ON UPDATE CASCADE
foreign key (customerID) REFERENCES Customer
ON DELETE
ON UPDATE CASCADE
foreign key (typename) REFERENCES DeliveryType
ON DELETE
ON UPDATE CASCADE
};

create table updateExistingOrders{
orderID	 	CHAR(30),
currentLocation	VARCHAR(50) not null,
status			VARCHAR(50),
companyID		INT not null,
dateupdated		DATE not null,
instance		VARCHAR(50),
primary key (orderID)
foreign key (orderID) REFERENCES CreateOrder
ON DELETE CASCADE
ON UPDATE CASCADE
foreign key (companyID) REFERENCES DeliveryCompany
ON DELETE CASCADE
ON UPDATE CASCADE
};

create table finishedOrders{
orderID	 	CHAR(30),
finishedDate DATE not null,
status CHAR(50),
primary key (orderID)
foreign key (orderID) REFERENCES CreateOrder
ON DELETE CASCADE
ON UPDATE CASCADE
};

create table packageContained{
orderID	CHAR(30),
package#	CHAR(30),
description VARCHAR(100),
weight DOUBLE not null,
primary key (orderID, package#)
foreign key (orderID) REFERENCES CreateOrder
ON DELETE CASCADE
ON UPDATE CASCADE
};

insert into customer
values('21367537', 'John Smith', '150 W 15th Ave', 'askhs@gmail.com', '6045657788', 'Cash');

insert into customer
values('57384360', 'Alan Jiang', '2463 W 10th Ave', 'fdfd@hotmail.com', '7786548967', 'Credit');

insert into customer
values('34348957', 'Julia Hebb', '3432 Cambie St', 'sdas@gmail.com', '6045998864', 'Cash');

insert into customer
values('45221778', 'Anna Roger', '955 Thurlow St', 'sdadd@gmail.com', '6047558945', 'Debit');

insert into customer
values('77654321', 'David Johonson', '1855 Nelson St', 'fedd@gmail.com', '7786554890', 'Credit');

insert into manages
values('21367537', '545782', TO_DATE('15-FEB-2017 12:21','DD-MM-YYYY HH24:MI'), 
       TO_DATE('15-FEB-2017 12:25','DD-MM-YYYY HH24:MI'), 'Lost Password');

insert into manages
values('21367537', '765829', TO_DATE('21-FEB-2017 13:21','DD-MM-YYYY HH24:MI'), 
       TO_DATE('21-FEB-2017 13:25','DD-MM-YYYY HH24:MI'), 'Lost Password');

insert into manages
values('57384360', '563742', TO_DATE('12-MAR-2017 14:21','DD-MM-YYYY HH24:MI'), 
       TO_DATE('12-MAR-2017 14:25','DD-MM-YYYY HH24:MI'), 'Lost Password');

insert into manages
values('34348957', '897643', TO_DATE('10-FEB-2018 15:21','DD-MM-YYYY HH24:MI'), 
       TO_DATE('10-FEB-2018 15:25','DD-MM-YYYY HH24:MI'), 'Status not updated');
       
insert into manages
values('77654321', '765829', TO_DATE('01-MAR-2018 16:21','DD-MM-YYYY HH24:MI'), 
       TO_DATE('01-MAR-2018 16:25','DD-MM-YYYY HH24:MI'), 'Account stolen');
       
