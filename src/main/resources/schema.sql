create table if not exists Ingredient (
  id varchar(4) not null,
  name varchar(25) not null,
  type varchar(10) not null
);

create table if not exists Taco (
  id identity,
  name varchar(50) not null,
  createdAt timestamp not null
);

create table if not exists Taco_Ingredients (
  taco bigint not null,
  ingredient varchar(4) not null
);

alter table Taco_Ingredients
    add foreign key (taco) references Taco(id);
alter table Taco_Ingredients
    add foreign key (ingredient) references Ingredient(id);

create table if not exists Taco_Order (
  id identity,
    firstName varchar(30) not null,
    surname varchar(30) not null,
    addressLine varchar(50) not null,
    city varchar(30) not null,
    postcode varchar(10) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVC varchar(3) not null,
    orderDate timestamp not null
);

create table if not exists Taco_Order_Tacos (
  orderId bigint not null,
  tacoId bigint not null
);

alter table Taco_Order_Tacos
    add foreign key (orderId) references Taco_Order(id);
alter table Taco_Order_Tacos
    add foreign key (tacoId) references Taco(id);