/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  rofth173
 * Created: 7/08/2019
 */
drop table if exists  product;

create table Product (
    productId varchar(50) primary key,
    name varchar(50) not null,
    description varchar(100) not null,
    category varchar(50) not null,
    listprice decimal(10,2) not null,
    quantityInStock decimal(10,2) not null
);
