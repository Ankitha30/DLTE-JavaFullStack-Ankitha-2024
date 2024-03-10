Before Normalization:
------------------------------------------------------------------------------------------------------------------
create table recharge_user_before_norm(username varchar(255) primary key,upi varchar(255),mobile_number number,email varchar(255),wallet_type varchar(50),recharge_date date,recharge_provider varchar(70),recharged_to varchar(100),recharged_amount number);


After Normalization:

------------------------------------------------------------------------------------------------------------------
create table user_details(username varchar(255) , email varchar(255) primary key,mobile_number number );

create table wallet_details(upi varchar(255)primary key ,wallet_type varchar(255),email varchar(255), foreign key(email) references user_details(email));

create table recharge_details(upi varchar(255)primary key,recharge_date date,recharge_provider varchar(70),recharged_to varchar(100),recharged_amount number,foreign key(upi) references wallet_details(upi));