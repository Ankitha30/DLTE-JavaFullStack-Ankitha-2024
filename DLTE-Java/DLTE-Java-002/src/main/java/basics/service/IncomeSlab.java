package basics.service;

import java.util.Scanner;

public class IncomeSlab {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Double salary=0.0,oldRegime=0.0,newRegime=0.0;

        System.out.println("------Welcome to Income Slab------");
        System.out.println("Enter your Annual Income(CTC)");
        salary=s.nextDouble();
        if(salary<250000)
            System.out.println("Exempt in both old and new regims");
        else if(salary>=250000 && salary<500000){
            oldRegime=salary*0.05;
            newRegime=salary*0.05;
            System.out.println("In old regime tax slab will be "+oldRegime);
            System.out.println("In new regime tax slab will be "+newRegime);
        }
        else if(salary>=50000 && salary<750000){
            oldRegime=salary*0.05;
            newRegime=salary*0.05;
            System.out.println("In old regime tax slab will be "+oldRegime);
            System.out.println("In new regime tax slab will be "+newRegime);
        }
        else if(salary>=750000 && salary<1000000){
            oldRegime=salary*0.05;
            newRegime=salary*0.05;
            System.out.println("In old regime tax slab will be "+oldRegime);
            System.out.println("In new regime tax slab will be "+newRegime);
        }
        else if(salary>=1000000 && salary<1250000){
            oldRegime=salary*0.05;
            newRegime=salary*0.05;
            System.out.println("In old regime tax slab will be "+oldRegime);
            System.out.println("In new regime tax slab will be "+newRegime);
        }
        else if(salary>=1250000 && salary<1500000){
            oldRegime=salary*0.05;
            newRegime=salary*0.05;
            System.out.println("In old regime tax slab will be "+oldRegime);
            System.out.println("In new regime tax slab will be "+newRegime);
        }
        else{
            oldRegime=salary*0.05;
            newRegime=salary*0.05;
            System.out.println("In old regime tax slab will be "+oldRegime);
            System.out.println("In new regime tax slab will be "+newRegime);
        }
        s.close();;

    }

}
