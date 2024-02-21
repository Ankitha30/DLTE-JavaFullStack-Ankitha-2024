package basics.service;

public interface MyBank {
    Loan[] loan = new Loan[10];
    public void addNewloan();
    public void checkAvailableLoans();
    public void checkOnlyClosedLoans();
}
