package basics.services;

import java.util.Date;
import java.util.Scanner;

/*
Transaction: dateOfTransaction, amountInTransaction, to, remarks(Family, Education, Emergency, Bills, Friend)

Array of Objects

Analysis:

Filter based on given ranges of date
least amount transferred
maximum amount transferred
number of transaction made to particular beneficiary
filter based on particular remarks
Sort:

based on Beneficiary in descending

based on amount in ascending
 */
public class TransactionAnalysis {
    public static void main(String[] args) {
        Transaction myTransaction[] =
                {
                        new Transaction(new Date(2024,04,02),1000,"Divija","Family"),
                        new Transaction(new Date(2024,03,01),1020,"Eeksha","Education"),
                        new Transaction(new Date(2024,04,03),2000,"Akshatha","Bill"),
                        new Transaction(new Date(2024,04,23),500,"Suni","Friend"),
                        new Transaction(new Date(2024,05,02),1400,"Suvi","Family"),
                        new Transaction(new Date(2024,03,12),900,"Suni","Friend"),


                };
        System.out.println("-------------------------------Welcome-------------------------");
        System.out.println("****Menu*****");
        Scanner scanner=new Scanner(System.in);
        Scanner scanner1=new Scanner(System.in);
        TransactionAnalysis analysis = new TransactionAnalysis();
        while (true){
            System.out.println(
                    "1. Filter based on given ranges of date\n" +
                            "2. least amount transferred\n" +
                            "3. maximum amount transferred\n" +
                            "4. number of transaction made to particular beneficiary\n" +
                            "5. filter based on particular remarks\n"+
                            "6. Sort based on amount in ascending\n"+ "7. Sort based on beneficiary in descending\n"+"8. Exit\n"
            );
            System.out.println("Enter your choice");
            int choice = scanner.nextInt();
            switch (choice){
                case 1: System.out.println("Enter the start Date(enter only date)");
                    int start = scanner.nextInt();
                    System.out.println("Enter the to date");
                    int end = scanner.nextInt();
                    analysis.filterDate(myTransaction,start,end);
                    break;
                case 2:
                    analysis.minimumAmount(myTransaction);
                    break;
                case 3: analysis.maximumAmount(myTransaction);
                        break;
                case 4:
                    System.out.println("Enter the name of Beneficiary");
                    String name= scanner1.nextLine();
                    analysis.totalTransaction(myTransaction,name);
                    break;
                case 5:
                    System.out.println("Enter the remark(Family, Education, Emergency, Bills, Friend)");
                    String remark = scanner1.nextLine();
                    for(Transaction each: myTransaction)
                    {
                        if(each.getRemarks().equals(remark)){
                            System.out.println(each.getBeneficiary()+" "+each.getAmountInTransaction()+" "+each.getDateOfTransaction());

                        }
                    }
                    break;
                case 6: analysis.sortAmount(myTransaction);
                        break;
                case 7: analysis.sortBeneficiary(myTransaction);
                        break;

                default:System.exit(0);
            }
        }


    }


    public void sortBeneficiary(Transaction[] myTransaction) {
        System.out.println("Before sorting amount");
        for(Transaction each:myTransaction){
            System.out.println(each.getBeneficiary());

        }
        for(int select=0;select<myTransaction.length;select++){
            for(int next=0;next<myTransaction.length-select-1;next++){
                if(myTransaction[next].getBeneficiary().compareTo(myTransaction[next+1].getBeneficiary())<0){
                    Transaction backup=myTransaction[next];
                    myTransaction[next]=myTransaction[next+1];
                    myTransaction[next+1]=backup;
                }
            }
        }
        System.out.println("After sorting");
        for(Transaction each:myTransaction){
            System.out.println(each.getBeneficiary());

        }
    }

    public void sortAmount(Transaction[] myTransaction) {
        System.out.println("Before sorting amount");
        for(Transaction each:myTransaction){
            System.out.println(each.getAmountInTransaction());

        }
        for(int select=0;select<myTransaction.length;select++){
            for(int next=0;next<myTransaction.length-select-1;next++){
                if(myTransaction[next].getAmountInTransaction().compareTo(myTransaction[next+1].getAmountInTransaction())>0){
                    Transaction backup=myTransaction[next];
                    myTransaction[next]=myTransaction[next+1];
                    myTransaction[next+1]=backup;
                }
            }
        }
        System.out.println("After sorting");
        for(Transaction each:myTransaction){
            System.out.println(each.getAmountInTransaction());

        }
    }

    public void totalTransaction(Transaction[] myTransaction, String name) {
        int transactioncount=0;
        for(Transaction each:myTransaction){
            if(each.getBeneficiary().equals(name))
                transactioncount++;
        }
        System.out.println("Number of Transaction made to "+name+" is "+transactioncount);
    }

    public void minimumAmount(Transaction[] myTransaction) {
        int amount = myTransaction[0].getAmountInTransaction();
        for(Transaction each: myTransaction){
            if(amount>each.getAmountInTransaction())
                amount=each.getAmountInTransaction();
        }
        System.out.println("Name       MinAmount");
        for (Transaction each:myTransaction) {
            if(each.getAmountInTransaction()==amount)
                System.out.println(each.getBeneficiary()+" " + amount);
        }
    }

    public void maximumAmount(Transaction[] myTransaction) {
        int amount = myTransaction[0].getAmountInTransaction();
        for(Transaction each: myTransaction){
            if(amount<each.getAmountInTransaction())
                amount=each.getAmountInTransaction();
        }
        System.out.println("Name       MaxAmount");
        for (Transaction each:myTransaction) {
            if(each.getAmountInTransaction()==amount)
            System.out.println(each.getBeneficiary()+" " + amount);
        }
    }


    public void filterDate(Transaction[] myTransaction, int start, int end) {
        System.out.println("Transaction between the given dates "+start+" and "+end);

        for (Transaction each: myTransaction) {
            if (each.getDateOfTransaction().getDate() >= start && each.getDateOfTransaction().getDate() <= end) {
                System.out.println(each.getBeneficiary() + " " +each.getAmountInTransaction()+" "+ each.getDateOfTransaction().getDate());
            }
        }
    }
}
