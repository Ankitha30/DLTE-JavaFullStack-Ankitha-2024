create table Transaction(transaction_id number primary key,transaction_date date not null,transaction_to varchar(255) not null,transaction_amount number not null, transaction_remarks varchar(255) not null);

insert into Transaction values(1,'02-Feb-2024','Divija',1000,'Family');
insert into Transaction values(2,'08-Feb-2024','Eeksha',1400,'Education');
insert into Transaction values(3,'22-Feb-2024','Sunidhi',1500,'Friend');
insert into Transaction values(4,'12-Feb-2024','Sunidhi',500,'Friend');
insert into Transaction values(5,'15-Feb-2024','Suvi',900,'Emergency');

1)Create view dateFilter as select * from Transaction where transaction_date between '10-feb-2024' and '25-mar-2024';
 View created
2)create view minimumAmountselect as select min(transaction_amount) from Transaction;
 View created
3)create view maxAmount as select max(transaction_amount) from Transaction;
 View created
4)create view transactionCount as select count(transaction_to) from Transaction where transaction_to='Sunidhi';
 View created
5)select * from Transaction where transaction_remarks='Friend';
 View created
