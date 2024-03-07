1.
SQL>create user Eeksha identified by eeksha;

User created.

SQL>grant connect to Eeksha;

Grant succeeded.

SQL>grant select on Transaction to Eeksha;

Grant succeeded.

SQL>alter session set current_schema =system;

Session altered.

SQL>select transaction_to from Transaction;

TRANSACTION_TO
--------------------------------------------------------------------------------
Sunidhi
Sunidhi
Suvi
Ankitha




============================================================================================================================================================
2.

SQL>create user ankitha identified by ankitha;

User created.

SQL>grant connect to ankitha;

Grant succeeded.

SQL>grant delete on Transaction to ankitha;

Grant succeeded.

SQL>alter session set current_schema = system;

Session altered.

SQL>delete from Transaction where transaction_id=2;

1 row deleted.


======================================================================
3.
SQL>create user Divija identified by divija;

User created.

SQL>grant connect to Divija;

Grant succeeded.

SQL>grant select on Transaction to Divija;

Grant succeeded.

SQL>alter session set current_schema = system;

Session altered.

SQL> select transaction_to from Transaction where transaction_id=1;

TRANSACTION_TO
--------------------------------------------------------------------------------
Ankitha


===========================================================================================================================================================

4.
SQL>create user Sunidhi identified by suni;

User created.

SQL>grant connect to Sunidhi;

Grant succeeded.

SQL>grant insert on Transaction to Sunidhi;

Grant succeeded.

SQL> alter session set current_schema = system;

Session altered.

SQL> insert into Transaction values(7,'26-feb-2024','Shravya',3000,'Family');

1 row created

============================================================================================================================================================

5.
SQL>create user Jayashri identified by 1234;

User created.
SQL>grant update on Transaction to Jayashri;

Grant succeeded.

SQL>alter session set current_schema = system;

Session altered.

SQL>update Transaction set transaction_remarks='Education' where transaction_to='Shravya' ;

1 row updated.
============================================================================================================================================================