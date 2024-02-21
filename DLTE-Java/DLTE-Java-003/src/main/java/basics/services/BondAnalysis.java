package basics.services;

public class BondAnalysis {
    public static void main(String[] args) {
        Bond bondArray[]={
                new Bond(1200,"Muncipal bond",7.5,"Payable","Sunidhi",3),
                new Bond(1200,"Fixed Rate Bond",5.5,"Not Payable","Sunita",4),
                new Bond(1200,"Prpetual Bond",6.5,"Not Payable","Anitha",2),
                new Bond(1200,"tax free Bond",12.5,"Payable","Ananya",5),
                new Bond(1200,"Government bond",10.5,"Payable","Ashwitha",5),
        };
        BondAnalysis bond = new BondAnalysis();
         bond.sort(bondArray);
    }

    public void sort(Bond[] bondArray) {
        System.out.println("Before sorting amount");
        for(Bond each:bondArray){
            System.out.println(each.getRateOfInterest());

        }
        for(int select=0;select<bondArray.length;select++){
            for(int next=0;next<bondArray.length-select-1;next++){
                if(bondArray[next].getRateOfInterest().compareTo(bondArray[next+1].getRateOfInterest())<0){
                    Bond backup=bondArray[next];
                    bondArray[next]=bondArray[next+1];
                    bondArray[next+1]=backup;
                }
            }
        }
        System.out.println("After sorting");
        for(Bond each:bondArray){
            System.out.println(each.getRateOfInterest());

        }
    }


}
