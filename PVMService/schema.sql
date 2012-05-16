create table Country (ID integer primary key autoincrement, Name varchar(20), CurrencyName varchar(20));
insert into Country (Name, CurrencyName) values('Australia', 'AUD');
insert into Country (Name, CurrencyName) values('China', 'CNY');
insert into Country (Name, CurrencyName) values('India', 'INR');

create table Type(ID integer primary key autoincrement, Name varchar(50));
insert into Type (Name) values('Vegeterian');
insert into Type (Name) values('Mixed');
insert into Type (Name) values('Mixed No-beef');
insert into Type (Name) values('Seafood');


create table Base (ID integer primary key autoincrement, Name varchar(50), TypeID integer references Type(ID), CookMinutes integer);
insert into Base (Name, TypeID, CookMinutes) values('Satay Veg', 1, 25);
insert into Base (Name, TypeID, CookMinutes) values('Ham & Cheese', 3, 30);
insert into Base (Name, TypeID, CookMinutes) values('Hawaiian', 3, 25);
insert into Base (Name, TypeID, CookMinutes) values('Simply Cheese', 1, 30);
insert into Base (Name, TypeID, CookMinutes) values('Cheesy Pepperoni', 3, 30);
insert into Base (Name, TypeID, CookMinutes) values('Margherita with Fresh Tomato & Basil', 1, 20);
insert into Base (Name, TypeID, CookMinutes) values('Prawn, Bacon & Feta', 4, 35);
insert into Base (Name, TypeID, CookMinutes) values('GodFather', 2, 30);

create table Topping (ID integer primary key autoincrement, Name varchar(50));
insert into Topping (Name) values('Avocado');
insert into Topping (Name) values('Tuna');
insert into Topping (Name) values('Feta');
insert into Topping (Name) values('Salami');
insert into Topping (Name) values('Pistachios');

create table CountryBase (ID integer primary key autoincrement, CountryID integer references Country(ID), BaseID integer references Base(ID), BaseName varchar(50), BasePrice real, Tax real);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(1, 1, 'Satay Veg', 10, 1);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(1, 2, 'Ham & Cheese', 9, 0.9);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(1, 3, 'Hawaiian', 10, 1);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(1, 4, 'Simply Cheese', 10, 1);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(1, 5, 'Cheesy Pepperoni', 10, 1);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(1, 6, 'Margherita with Fresh Tomato & Basil', 10, 1);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(1, 7, 'Prawn, Bacon & Feta', 11, 1.1);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(1, 8, 'GodFather', 10, 1);

insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(2, 1, 'Shadie', 30, 4);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(2, 2, 'Huotui Zhishi', 32, 4);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(2, 3, 'Xiaweiyi', 35, 4);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(2, 4, 'Zhishi', 28, 4);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(2, 5, 'Zhishi Lachang', 32, 4);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(2, 6, 'Mageruita', 40, 4);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(2, 7, 'Xia, Huotui, Yanglao', 38, 4);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(2, 8, 'Jiaofu', 30, 4);

insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(3, 1, 'Satay Veg', 10, 1);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(3, 2, 'Ham & Cheese', 9, 0.9);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(3, 3, 'Hawaiian', 10, 1);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(3, 4, 'Simply Cheese', 10, 1);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(3, 5, 'Cheesy Pepperoni', 10, 1);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(3, 6, 'Margherita with Fresh Tomato & Basil', 10, 1);
insert into CountryBase (CountryID, BaseID, BaseName, BasePrice, Tax) values(3, 7, 'Prawn, Bacon & Feta', 11, 1.1);

create table CountryTopping (ID integer primary key autoincrement, CountryID integer references Country(ID), ToppingID integer references Topping(ID), ToppingName varchar(50), ToppingPrice real, Tax real);
insert into CountryTopping (CountryID, ToppingID, ToppingName, ToppingPrice, Tax) values(1, 1, 'Avocado', 2, 0.2);
insert into CountryTopping (CountryID, ToppingID, ToppingName, ToppingPrice, Tax) values(1, 2, 'Tuna', 2.5, 0.2);
insert into CountryTopping (CountryID, ToppingID, ToppingName, ToppingPrice, Tax) values(1, 3, 'Feta', 2, 0.2);
insert into CountryTopping (CountryID, ToppingID, ToppingName, ToppingPrice, Tax) values(1, 4, 'Salami', 2.2, 0.2);
insert into CountryTopping (CountryID, ToppingID, ToppingName, ToppingPrice, Tax) values(1, 5, 'Pistachios', 3, 0.3);

insert into CountryTopping (CountryID, ToppingID, ToppingName, ToppingPrice, Tax) values(2, 1, 'Eli', 6, 0);
insert into CountryTopping (CountryID, ToppingID, ToppingName, ToppingPrice, Tax) values(2, 2, 'Tunna', 7, 0);
insert into CountryTopping (CountryID, ToppingID, ToppingName, ToppingPrice, Tax) values(2, 3, 'Yaolao', 5, 0);
insert into CountryTopping (CountryID, ToppingID, ToppingName, ToppingPrice, Tax) values(2, 4, 'Lachang', 7, 0);
insert into CountryTopping (CountryID, ToppingID, ToppingName, ToppingPrice, Tax) values(2, 5, 'Kaixinguo', 8, 0);

insert into CountryTopping (CountryID, ToppingID, ToppingName, ToppingPrice, Tax) values(3, 1, 'Avocado', 2, 0.2);
insert into CountryTopping (CountryID, ToppingID, ToppingName, ToppingPrice, Tax) values(3, 2, 'Tuna', 2.5, 0.2);
insert into CountryTopping (CountryID, ToppingID, ToppingName, ToppingPrice, Tax) values(3, 3, 'Feta', 2, 0.2);
insert into CountryTopping (CountryID, ToppingID, ToppingName, ToppingPrice, Tax) values(3, 4, 'Salami', 2.2, 0.2);
insert into CountryTopping (CountryID, ToppingID, ToppingName, ToppingPrice, Tax) values(3, 5, 'Pistachios', 3, 0.3);

create table CountryType (ID integer primary key autoincrement, CountryID integer references Country(ID), TypeID integer references Type(ID), TypeName varchar(50));
insert into CountryType (CountryID, TypeID, TypeName) values(1, 1, 'Vegeterian');
insert into CountryType (CountryID, TypeID, TypeName) values(1, 2, 'Mixed');
insert into CountryType (CountryID, TypeID, TypeName) values(1, 3, 'Mixed');
insert into CountryType (CountryID, TypeID, TypeName) values(1, 4, 'Seafood');

insert into CountryType (CountryID, TypeID, TypeName) values(2, 1, 'Su');
insert into CountryType (CountryID, TypeID, TypeName) values(2, 2, 'Hun');
insert into CountryType (CountryID, TypeID, TypeName) values(2, 3, 'Hun');
insert into CountryType (CountryID, TypeID, TypeName) values(2, 4, 'Haixian');

insert into CountryType (CountryID, TypeID, TypeName) values(3, 1, 'Vegeterian');
insert into CountryType (CountryID, TypeID, TypeName) values(3, 3, 'Mixed');
insert into CountryType (CountryID, TypeID, TypeName) values(3, 4, 'Seafood');

create table CountryCoins (ID integer primary key autoincrement, CountryID integer references Country(ID), Name varchar(50), Value real);
insert into CountryCoins (CountryID, Name, Value) values(1, '10c', 0.1);
insert into CountryCoins (CountryID, Name, Value) values(1, '20c', 0.2);
insert into CountryCoins (CountryID, Name, Value) values(1, '50c', 0.5);
insert into CountryCoins (CountryID, Name, Value) values(1, '$1', 1);
insert into CountryCoins (CountryID, Name, Value) values(1, '$2', 2);

insert into CountryCoins (CountryID, Name, Value) values(2, '10 fen', 0.1);
insert into CountryCoins (CountryID, Name, Value) values(2, '20 fen', 0.2);
insert into CountryCoins (CountryID, Name, Value) values(2, '50 fen', 0.5);
insert into CountryCoins (CountryID, Name, Value) values(2, '1 yuan', 1);

insert into CountryCoins (CountryID, Name, Value) values(3, '10 paise', 0.1);
insert into CountryCoins (CountryID, Name, Value) values(3, '25 paise', 0.25);
insert into CountryCoins (CountryID, Name, Value) values(3, '50 paise', 0.5);
insert into CountryCoins (CountryID, Name, Value) values(3, '1 rupee', 1);
insert into CountryCoins (CountryID, Name, Value) values(3, '2 rupees', 2);
insert into CountryCoins (CountryID, Name, Value) values(3, '5 rupees', 5);

create table Vendor(ID integer primary key autoincrement, CountryID integer references Country(ID), Address varchar(250));
insert into Vendor (CountryID, Address) values(1, 'RMIT');

create table VendorStock(ID integer primary key autoincrement, VendorID integer references Vendor(ID), BaseID integer references Base(ID), ToppingID integer references Topping(ID), Count integer);
insert into VendorStock (VendorID, BaseID, ToppingID, Count) values(1, 1, -1, 10);
insert into VendorStock (VendorID, BaseID, ToppingID, Count) values(1, 2, -1, 10);
insert into VendorStock (VendorID, BaseID, ToppingID, Count) values(1, 3, -1, 10);
insert into VendorStock (VendorID, BaseID, ToppingID, Count) values(1, 4, -1, 10);
insert into VendorStock (VendorID, BaseID, ToppingID, Count) values(1, 5, -1, 10);
insert into VendorStock (VendorID, BaseID, ToppingID, Count) values(1, 6, -1, 10);
insert into VendorStock (VendorID, BaseID, ToppingID, Count) values(1, 7, -1, 10);

insert into VendorStock (VendorID, BaseID, ToppingID, Count) values(1, -1, 1, 40);
insert into VendorStock (VendorID, BaseID, ToppingID, Count) values(1, -1, 2, 40);
insert into VendorStock (VendorID, BaseID, ToppingID, Count) values(1, -1, 3, 40);
insert into VendorStock (VendorID, BaseID, ToppingID, Count) values(1, -1, 4, 40);
insert into VendorStock (VendorID, BaseID, ToppingID, Count) values(1, -1, 5, 40);

create table Orders(ID integer primary key autoincrement, VendorID integer references Vendor(ID), BaseID integer references Base(ID), ToppingID integer references Topping(ID), OrderTime integer, Amount real);