package basics.service;

public interface MyBank {
    Loan[] loan = new Loan[10];
    void addNewLoan();
    void checkAvailableLoans();
    void checkOnlyClosedLoans();
}
