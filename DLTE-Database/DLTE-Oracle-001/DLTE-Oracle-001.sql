create table Transaction(transaction_id number primary key,transaction_date date not null,transaction_to varchar(255) not null,transaction_amount number not null, transaction_remarks varchar(255) not null);

insert into Transaction values(1,'02-Feb-2024','Divija',1000,'Family');
insert into Transaction values(2,'08-Feb-2024','Eeksha',1400,'Education');
insert into Transaction values(3,'22-Feb-2024','Sunidhi',1500,'Friend');
insert into Transaction values(4,'12-Feb-2024','Sunidhi',500,'Friend');
insert into Transaction values(5,'15-Feb-2024','Suvi',900,'Emergency');

1)select * from Transaction where transaction_date between '10-feb-2024' and '25-mar-2024';
2)select min(transaction_amount) from Transaction;
3)select max(transaction_amount) from Transaction;
4)select count(transaction_to) from Transaction where transaction_to='Sunidhi';
5)select * from Transaction where transaction_remarks='Friend';