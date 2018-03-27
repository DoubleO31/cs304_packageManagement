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
name			CHAR(30) not null,
address		CHAR(50) not null,
email			EMAIL,
phone#		INT not null
methodOfPayment	CHAR(20),
primary key (customerID)
};

create table manages{
customerID	CHAR(30),
employeeID 	CHAR(30),
starttime	CHAR(30) not null,
endtime	CHAR(30) not null,
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
orderID		INT
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
orderID	 	INT,
currentLocation	CHAR(50) not null,
status			CHAR(50),
companyID		INT not null,
dateupdated		DATE not null,
instance		CHAR(50),
primary key (orderID)
foreign key (orderID) REFERENCES CreateOrder
ON DELETE CASCADE
ON UPDATE CASCADE
foreign key (companyID) REFERENCES DeliveryCompany
ON DELETE CASCADE
ON UPDATE CASCADE
};

create table finishedOrders{
orderID	 	INT,
finishedDate DATE not null,
status CHAR(50),
primary key (orderID)
foreign key (orderID) REFERENCES CreateOrder
ON DELETE CASCADE
ON UPDATE CASCADE
};

create table packageContained{
orderID	INT,
package#	INT,
description CHAR(100),
weight DOUBLE not null,
primary key (orderID, package#)
foreign key (orderID) REFERENCES CreateOrder
ON DELETE CASCADE
ON UPDATE CASCADE
};



