create or replace PROCEDURE UPDATE_DEBITCARD_STATUS (
    p_account_number IN MYBANK_APP_DEBITCARD.account_number%TYPE,
    p_debitcard_status IN MYBANK_APP_DEBITCARD.debitcard_status%TYPE,
    p_status OUT VARCHAR2
) AS    
    v_customer_status VARCHAR2(255);
    v_account_status VARCHAR2(255);
    v_count NUMBER;
    v_debitcard_status VARCHAR2(255);
BEGIN 
        -- Check if the customer is active
        SELECT customer_status INTO v_customer_status
        FROM mybank_app_customer c
        JOIN mybank_app_debitcard d ON c.customer_id = d.customer_id
        WHERE d.account_number = p_account_number;
 
        -- Check if account is active
        SELECT account_status INTO v_account_status
        FROM mybank_app_account a
        JOIN mybank_app_debitcard d ON a.account_number = d.account_number
        WHERE d.account_number = p_account_number;
 
        -- Check whether debit card exists
        SELECT COUNT(*) INTO v_count
        FROM mybank_app_debitcard
        WHERE account_number = p_account_number;
 
        -- Check if the debit card exists and is active
        SELECT debitcard_status INTO v_debitcard_status
        FROM mybank_app_debitcard 
        WHERE account_number = p_account_number;
 
        -- Update the debit card status
        IF v_customer_status = 'active' AND v_account_status = 'active' AND v_debitcard_status = 'active' THEN
            IF v_count > 0 THEN
                UPDATE mybank_app_debitcard d
                SET d.debitcard_status = p_debitcard_status
                WHERE d.account_number = p_account_number;
                COMMIT;
                p_status := 'SQLCODE-000';
            END IF;
        ELSIF v_customer_status != 'active' THEN
            p_status := 'SQLCODE-001'; --  "Customer is not active"
        ELSIF v_account_status != 'active' THEN
            p_status := 'SQLCODE-002'; --  "Account is not active"
        ELSE 
            p_status := 'SQLCODE-003'; --  "Debit card limit update failed"
        END IF;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            p_status := 'SQLCODE-004'; -- "No data found"
        WHEN OTHERS THEN
            p_status := 'SQLCODE-005'||SQLERRM; -- other exceptions(data access exception,sql..etc)
END UPDATE_DEBITCARD_STATUS;