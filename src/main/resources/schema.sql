/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  rofth173
 * Created: 7/08/2019
 */


create table Product (
    productId varchar(50) primary key,
    name varchar(50) not null,
    description varchar(100) not null,
    category varchar(50) not null,
    listprice decimal(10,2) not null,
    quantityInStock decimal(10,2) not null
);

create table Customer (
    customerId integer primary key auto_increment,
    username varchar(50) unique not null,
    firstName varchar(50) not null,
    surname varchar(50) not null,
    password varchar(50) not null,
    emailAddress varchar(100) not null,
    shippingAddress varchar(100) not null
);

create table Sale (
     saleId integer primary key auto_increment, 
     date TIMESTAMP not null,
     status varchar(10),
     customerId integer,
     constraint Sale_customerId_FK foreign key (customerId) references Customer
     
);

create table SaleItem (
    salePrice decimal(10,2) not null,
    productId varchar(50) not null,
    quantity integer not null,
    saleId integer not null,
    constraint SaleItem_SaleId_FK foreign key (saleId) references Sale,
    constraint SaleItem_ProductId_FK foreign key (productId) references Product,
    constraint SaleItem_PK primary key (SaleId, productId)
);

