drop table packageContained;
drop table finishedOrders;
drop table existingOrders;
drop table orders;
drop table deliveryType;
drop table manages;
drop table customerService;
drop table deliveryCompanyAddress;
drop table deliveryCompany;
drop table customer;

create table customer(
customerID		int,
name			VARCHAR(50) not null,
address		VARCHAR(50) not null,
email			VARCHAR(20),
phone		       int,
methodOfPayment	CHAR(20),
primary key (customerID)
);

create table deliveryCompany(
companyID	int,
cname		CHAR(30) not null,
primary key (companyID)
);

create table deliveryCompanyAddress(
companyID	int,
branch 	int,
caddress	CHAR(50) not null,
primary key (companyID, branch),
foreign key (companyID) REFERENCES deliveryCompany
);

create table customerService(
employeeID 	int,
companyID	int not null,
name		CHAR(30) not null,
language	CHAR(30) not null,
primary key (employeeID),
foreign key (companyID) REFERENCES deliveryCompany
ON DELETE CASCADE
);

create table manages(
customerID	int,
employeeID 	int,
starttime	DATE not null,
endtime	DATE not null,
instance	CHAR(50) not null,
primary key (customerID, employeeID),
foreign key (customerID) REFERENCES customer
ON DELETE CASCADE,
foreign key (employeeID) REFERENCES customerService
ON DELETE CASCADE
);

create table deliveryType(
typename	CHAR(30),
rate		NUMBER not null,
deliveryTime	CHAR(30) not null,
primary key (typename) 
);

create table orders(
orderID		NUMBER,
customerID		int,
companyID		int not null,
typename		CHAR(30) not null,
senderName		VARCHAR(50) not null,
senderAddress	VARCHAR(50) not null,
receiverAddress	VARCHAR(50) not null,
receiverName		VARCHAR(50) not null,       
price			NUMBER not null,
dateCreated		DATE not null,
expectedArrival	DATE not null,	
primary key (orderID),
foreign key (companyID) REFERENCES deliveryCompany
ON DELETE CASCADE,
foreign key (customerID) REFERENCES customer
ON DELETE CASCADE,
foreign key (typename) REFERENCES deliveryType
ON DELETE CASCADE
);

create table existingOrders(
orderID	 	NUMBER,
currentLocation	VARCHAR(50) not null,
status			VARCHAR(50),
companyID		INT not null,
dateupdated		DATE not null,
instance		VARCHAR(50),
primary key (orderID),
foreign key (orderID) REFERENCES orders,
foreign key (companyID) REFERENCES deliveryCompany
);

create table finishedOrders(
orderID	 	NUMBER,
finishedDate DATE not null,
status CHAR(50),
primary key (orderID),
foreign key (orderID) REFERENCES orders
ON DELETE CASCADE
);

create table packageContained(
orderID	NUMBER,
packageNo	int,
description VARCHAR(100),
weight NUMBER not null,
primary key (orderID, packageNo),
foreign key (orderID) REFERENCES orders
ON DELETE CASCADE
);

insert into customer
values(21367537, 'John Smith', '150 W 15th Ave', 'askhs@gmail.com', '6045657788', 'Cash');

insert into customer
values(57384360, 'Alan Jiang', '2463 W 10th Ave', 'fdfd@hotmail.com', '7786548967', 'Credit');

insert into customer
values(34348957, 'Julia Hebb', '3432 Cambie St', 'sdas@gmail.com', '6045998864', 'Cash');

insert into customer
values(45221778, 'Anna Roger', '955 Thurlow St', 'sdadd@gmail.com', '6047558945', 'Debit');

insert into customer
values(77654321, 'David Johonson', '1855 Nelson St', 'fedd@gmail.com', '7786554890', 'Credit');

insert into deliveryCompany
values(94237, 'Canada Post');

insert into deliveryCompany
values(65789, 'Fedex');

insert into deliveryCompany
values(34786, 'UPS');

insert into deliveryCompany
values(98765, 'DHL');

insert into deliveryCompany
values(34322, 'YRC Worldwide');

insert into deliveryCompanyAddress
values(94237, 153, '110 Clark Road');

insert into deliveryCompanyAddress
values(65789, 226, '7526 Como Lake Ave');

insert into deliveryCompanyAddress
values(34786, 322, '692 Robinson Street');

insert into deliveryCompanyAddress
values(98765, 121, '1186 Foster Ave');

insert into deliveryCompanyAddress
values(34322, 335, '228 Austin Ave');

insert into customerService
values(545782, 94237, 'Michale Wagner', 'English');

insert into customerService
values(765829, 65789, 'Luna Fuller', 'English');

insert into customerService
values(563742, 34786, 'Thomas Fairchild', 'Korean');

insert into customerService
values(897643, 98765, 'Liu Lin', 'Chinese');

insert into customerService
values(670876, 34322, 'Lucy Cavendish', 'English');

insert into manages
values(21367537, 545782, TO_DATE('15-FEB-2017 12:21','DD-MM-YYYY HH24:MI'), 
       TO_DATE('15-FEB-2017 12:25','DD-MM-YYYY HH24:MI'), 'Lost Password');

insert into manages
values(21367537, 765829, TO_DATE('21-FEB-2017 13:21','DD-MM-YYYY HH24:MI'), 
       TO_DATE('21-FEB-2017 13:25','DD-MM-YYYY HH24:MI'), 'Lost Password');

insert into manages
values(57384360, 563742, TO_DATE('12-MAR-2017 14:21','DD-MM-YYYY HH24:MI'), 
       TO_DATE('12-MAR-2017 14:25','DD-MM-YYYY HH24:MI'), 'Lost Password');

insert into manages
values(34348957, 897643, TO_DATE('10-FEB-2018 15:21','DD-MM-YYYY HH24:MI'), 
       TO_DATE('10-FEB-2018 15:25','DD-MM-YYYY HH24:MI'), 'Status not updated');
       
insert into manages
values(77654321, 765829, TO_DATE('01-MAR-2018 16:21','DD-MM-YYYY HH24:MI'), 
       TO_DATE('01-MAR-2018 16:25','DD-MM-YYYY HH24:MI'), 'Account stolen');
       
insert into deliveryType
values('Regular', 2.99, 'One to three weeks');

insert into deliveryType
values('Fast', 6.99, 'Within one week');

insert into deliveryType
values('Express', 10.99, 'Two business day');

insert into deliveryType
values('Internation Economic', 7.99, 'Up to two months');

insert into deliveryType
values('Internation Express', 16.99, 'Within one week');

insert into orders
values(100056478920,21367537,94237, 'Regular', 'John Smith', '150 W 15th Ave', '830 10 Ave SW, Calgary',
       'MEC Calgary', 5.45, TO_DATE('28-FEB-2018','DD-MM-YYYY'),TO_DATE('12-FEB-2018','DD-MM-YYYY'));
       
insert into orders
values(103024324887,57384360,94237, 'Fast', 'Alan Jiang', '2463 W 10th Ave', '2500 University Dr NW, Calgary',
       'University of Calgary Outdoor Centre', 7.88, TO_DATE('19-FEB-2018','DD-MM-YYYY'),TO_DATE('12-FEB-2018','DD-MM-YYYY'));
     
insert into orders
values(102064585959,21367537,34786, 'Express', 'Amazon', '109 Braid St, New Westminster', '3432 Cambie St',
       'Julia Hebb', 32.43, TO_DATE('19-FEB-2018','DD-MM-YYYY'),TO_DATE('15-FEB-2018','DD-MM-YYYY'));
       
insert into orders
values(101033209248,45221778,98765, 'Regular', 'Anna Roger', '955 Thurlow St', '22165 Dewdney Trunk Rd Maple Ridge',
       'Rick Douglas', 4.33, TO_DATE('01-MAR-2018','DD-MM-YYYY'),TO_DATE('14-FEB-2018','DD-MM-YYYY'));
    
insert into orders
values(101156437829,77654321,34322, 'Internation Express', 'David Johonson', '1855 Nelson St', '4730 University Way NE Seattle',
       'Maria Johonson', 50.8, TO_DATE('21-FEB-2018','DD-MM-YYYY'),TO_DATE('13-FEB-2018','DD-MM-YYYY'));

insert into existingOrders
values(100056478920, 'Vancouver', 'Clearing Custom', 94237, TO_DATE('26-FEB-2018','DD-MM-YYYY'), 'daily update');

insert into existingOrders
values(103024324887, 'Richmond', 'Clearing Custom', 94237, TO_DATE('17-FEB-2018','DD-MM-YYYY'), 'custom clear');

insert into existingOrders
values(102064585959, 'Whiterock', 'Clearing Custom', 34786, TO_DATE('20-FEB-2018','DD-MM-YYYY'), 'package loss');

insert into finishedOrders
values(101033209248, TO_DATE('03-MAR-2018','DD-MM-YYYY'), 'Delivered');

insert into finishedOrders
values(101156437829, TO_DATE('20-FEB-2018','DD-MM-YYYY'), 'Customer return');

insert into packageContained
values(100056478920, 43456, 'Personal', 1.2);

insert into packageContained
values(103024324887, 53345, 'Computer', 2.1);

insert into packageContained
values(102064585959, 12165, 'From amazon', 1.5);

insert into packageContained
values(101033209248, 77653, 'Fragile', 3);

insert into packageContained
values(101156437829, 34351, 'Personal', 1);

commit work;
