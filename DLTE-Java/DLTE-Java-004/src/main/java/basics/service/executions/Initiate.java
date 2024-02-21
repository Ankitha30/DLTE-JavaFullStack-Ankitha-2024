package basics.service.executions;

import basics.service.SavingsAccount;

public class Initiate extends SavingsAccount {
    public  Initiate(){

    }
    public Initiate(String name,Long number,Double balance){
        this.setAccountHolder(name);
        this.setAccountBalance(balance);
        this.setAccountNumber(number);
    }
    public static void main(String[] args) {
        Initiate initiate=new Initiate();
        initiate.setAccountHolder("Ankitha");
    }
}
