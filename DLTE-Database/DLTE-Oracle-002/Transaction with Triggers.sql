create or replace trigger transaction_with_trigger 
before insert on transaction for each row 
begin
    if :new.transaction_remarks is null then :new.transaction_remarks :='General' ;
    end if;
end;
/
insert into transaction values(6,'Jaya','25-feb-2024',5000,null);