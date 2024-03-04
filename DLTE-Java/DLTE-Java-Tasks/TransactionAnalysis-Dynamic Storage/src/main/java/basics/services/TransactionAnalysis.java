package basics.services;

import java.util.*;
import java.util.stream.Collectors;

/*
Transaction: dateOfTransaction, amountInTransaction, to, remarks(Family, Education, Emergency, Bills, Friend)

Array of Objects

Analysis:

Filter based on given ranges of date: using stream().filter
least amount transferred : using stream().filter
maximum amount transferred: using stream().filter
Customized sort :
let the user to decide the property and order based on sorting happens
 */
public class TransactionAnalysis {
    public static void main(String[] args) {
        List<Transaction> transaction=new ArrayList<>();


        transaction.add(new Transaction(new Date("4/20/2021"),1000,"Divija","Family"));
        transaction.add(new Transaction(new Date("6/12/2023"),1020,"Eeksha","Education"));
        transaction.add(new Transaction(new Date("4/12/2021"),2000,"Akshatha","Bill"));
        transaction.add(new Transaction(new Date("7/11/2023"),500,"Suni","Friend"));
        transaction.add(new Transaction(new Date("4/1/2021"),1400,"Suvi","Family"));
//        transaction.add(new Transaction(new Date(2024,03,12),900,"Suni","Friend"));



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
                            "4. Sorting based on either beneficiary or transaction amount\n" +
                            "5. Exit\n"
            );
            System.out.println("Enter your choice");
            int choice = scanner.nextInt();
            switch (choice){
                case 1: System.out.println("Enter the start Date(enter only date)");
                    Date start = new Date(scanner.next());
                    System.out.println("Enter the to date");
                    Date end = new Date(scanner.next());
                    List<Transaction> filterdate=transaction.stream().filter(transaction1 -> transaction1.getDateOfTransaction().after(start) && transaction1.getDateOfTransaction().before(end)).collect(Collectors.toList());
                    filterdate.forEach(transaction1 -> System.out.println(transaction1.getDateOfTransaction()+" "+transaction1.getAmountInTransaction()+" "+transaction1.getBeneficiary()));
                    break;
                case 2:
                    Transaction minTransaction= transaction.stream().min(Comparator.comparingDouble(Transaction::getAmountInTransaction)).orElse(null);
                    System.out.println("minimum amount: "+minTransaction.getAmountInTransaction());
                    break;
                case 3:  Transaction maxTransaction= transaction.stream().max(Comparator.comparingDouble(Transaction::getAmountInTransaction)).orElse(null);
                    System.out.println("Max amount"+maxTransaction.getAmountInTransaction());
                    break;
                case 4:
                    System.out.println("Enter the property to sort (Beneficiary,amountInTransaction,date");
                    String property= scanner1.nextLine();
                    System.out.println("Enter the order ascending or descending");
                    String order = scanner1.nextLine();
                    Comparator<Transaction> comparator = Comparator.comparing(Transaction::getDateOfTransaction);
                    switch (property.toLowerCase()){
                        case "beneficiary":
                             comparator = Comparator.comparing(Transaction::getBeneficiary);
                            break;
                        case "amountintransaction":
                            comparator =Comparator.comparing(Transaction::getAmountInTransaction);
                            break;
                        case "date":
                            comparator=Comparator.comparing(Transaction::getDateOfTransaction);
                            break;
                    }
                    if(order.equalsIgnoreCase("descending"))
                        comparator=comparator.reversed();
                    transaction.stream().sorted(comparator).forEach(transaction1 -> System.out.println(transaction1.getDateOfTransaction()+" "+transaction1.getAmountInTransaction()+" "+transaction1.getBeneficiary()+" "+transaction1.getRemarks()));

//                    analysis.totalTransaction(myTransaction,name);/
                    break;




                default:System.exit(0);
            }
        }


    }










//    public void filterDate(Transaction[] myTransaction, int start, int end) {
//        System.out.println("Transaction between the given dates "+start+" and "+end);
//
//        for (Transaction each: myTransaction) {
//            if (each.getDateOfTransaction().getDate() >= start && each.getDateOfTransaction().getDate() <= end) {
//                System.out.println(each.getBeneficiary() + " " +each.getAmountInTransaction()+" "+ each.getDateOfTransaction().getDate());
//            }
//        }
//    }
}
