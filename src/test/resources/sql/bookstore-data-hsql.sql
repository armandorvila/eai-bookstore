insert into bookstore."Customer" (id, name, nif, address, mail, debt) 
values (1100,'Armando Jaleo', '62789475D', 'Calle La Pantomima, Madrid', 'armando@f1.com' , 0);

insert into bookstore."Customer" (id, name, nif, address, mail, debt) 
values (2100,'Julio Pederada', '65459475D', 'Calle La Anarquía, Madrid', 'julio@dbas.com' , 0);

insert into bookstore."Invoice" (id, date, amount, number) 
values (1000,'2012-11-11', 120.00, '9292821081' );

insert into bookstore."Shipment" (id, date, express,customer_id, invoice_id,shipment_number)
values(1100,'2012-11-11', false, 1100,1000, '9292821082' );

insert into bookstore."Book" (id, name, description, isbn, price , stock)
values (1, 'Spring in Action' , 'A gujide to learn Sprign framework' , '1' , 50.54, 100)

insert into bookstore."Book" (id, name, description, isbn, price , stock)
values (2, 'Mule in Action' , 'A gujide to learn Mule ESB' , '2' , 70.54, 10)

insert into bookstore."Book" (id, name, description, isbn, price , stock)
values (3, 'Enterprise Integration Patterns' , 'The base of all integration frameworks, like Apache Camel, Spring Integration, or Mule ESB' 
, '3' , 70.54, 10)

insert into bookstore."WaitingList" (book_id,customer_id) values (1,1100)
