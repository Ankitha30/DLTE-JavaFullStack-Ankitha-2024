create or replace PROCEDURE UPDATE_DEBITCARD_STATUS (
    p_account_number IN MYBANK_APP_DEBITCARD.account_number%TYPE,
    p_debitcard_status IN MYBANK_APP_DEBITCARD.debitcard_status%TYPE,
    p_debitcard_pin IN MYBANK_APP_DEBITCARD.debitcard_pin%TYPE,
    p_status OUT VARCHAR2
) AS    
   
    v_count NUMBER;
    v_debitcard_status VARCHAR2(255);
    v_debitcard_pin number(4);
BEGIN 
       
 
        -- Check whether debit card exists
        SELECT COUNT(*) INTO v_count
        FROM mybank_app_debitcard
        WHERE account_number = p_account_number;
 
        -- Check if the debit card exists and is active
        SELECT debitcard_status INTO v_debitcard_status
        FROM mybank_app_debitcard 
        WHERE account_number = p_account_number;
 
        -- Update the debit card status
        IF  v_debitcard_status = 'active' THEN
            IF v_count > 0 THEN
                UPDATE mybank_app_debitcard d
                SET d.debitcard_status = p_debitcard_status
                WHERE d.account_number = p_account_number and d.debitcard_pin=p_debitcard_pin;
                COMMIT;
                p_status := 'SQLCODE-000';
            END IF;
     
        ELSE 
            p_status := 'SQLCODE-003'; --  "Debit card update failed"
        END IF;
    EXCEPTION
       
        WHEN OTHERS THEN
            p_status := 'SQLCODE-005'||SQLERRM; -- other exceptions(data access exception,sql..etc)
END UPDATE_DEBITCARD_STATUS;