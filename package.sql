create table Customer{
customerID		CHAR(30),
name			CHAR(30) not null,
address		CHAR(50) not null,
email			EMAIL,
phone#		INT not null
methodOfPayment	CHAR(20),
primary key (customerID)
};

create table Manages{
customerID	CHAR(30),
employeeID 	CHAR(30),
starttime	CHAR(30) not null,
endtime	CHAR(30) not null,
instance	CHAR(50) not null,
PRIMARY KEY (customerID, employeeID),
FOREIGN KEY (customerID) REFERENCES Customer
ON DELETE 
ON UPDATE CASCADE
FOREIGN KEY (employeeID) REFERENCES CustomerService
ON DELETE 
ON UPDATE CASCADE
};

create table CustomerService{
employeeID 	CHAR(30),
companyID	CHAR(30) not null,
name		CHAR(30) not null,
language	CHAR(30) not null,
PRIMARY KEY (employeeID)
FOREIGN KEY (companyID) REFERENCES DeliveryCompany
ON DELETE
ON UPDATE CASCADE
};

create table DeliveryType{
typename	CHAR(30),
rate		DOUBLE not null,
deliveryTime	CHAR(30) not null,
PRIMARY KEY (typename)
};

create table DeliveryCompany{
companyID	CHAR(30),
cname		CHAR(30) not null,
PRIMARY KEY (companyID)
};

create table DeliveryCompanyAddress{
companyID	CHAR(30),
branch#	INT not null,
caddress	CHAR(50) not null,
PRIMARY KEY (companyID, branch#)
FOREIGN KEY (companyID) REFERENCES DeliveryCompany
};

create table CreateOrder{
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
PRIMARY KEY (orderID, customerID, companyID, typename)
FOREIGN KEY (companyID) REFERENCES DeliveryCompany
ON DELETE
ON UPDATE CASCADE
FOREIGN KEY (customerID) REFERENCES Customer
ON DELETE
ON UPDATE CASCADE
FOREIGN KEY (typename) REFERENCES DeliveryType
ON DELETE
ON UPDATE CASCADE
};



