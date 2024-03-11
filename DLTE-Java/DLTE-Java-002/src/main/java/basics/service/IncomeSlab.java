package basics.service;

import java.util.Scanner;

public class IncomeSlab {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Double salary=0.0,oldRegime=0.0,newRegime=0.0;
        System.out.println("1. New Regime   2.Old Regime");
        int choice=scanner.nextInt();
        System.out.println("------Welcome to Income Slab------");
        System.out.println("Enter your Annual Income(CTC)");
        salary=scanner.nextDouble();
        switch (choice){
            case 1:
                    if(salary<250000)
                        System.out.println("Exempt in new regims");
                    else if(salary>=250000 && salary<500000){
                        newRegime=salary*0.05;
                        System.out.println("In new regime tax slab will be "+newRegime);
                    }
                    else if(salary>=50000 && salary<750000){
                        newRegime=salary*0.1;
                        System.out.println("In new regime tax slab will be "+newRegime);
                    }
                    else if(salary>=750000 && salary<1000000){
                        newRegime=salary*0.15;
                        System.out.println("In new regime tax slab will be "+newRegime);
                    }
                    else if(salary>=1000000 && salary<1250000){
                        newRegime=salary*0.2;
                        System.out.println("In new regime tax slab will be "+newRegime);
                    }
                    else if(salary>=1250000 && salary<1500000){
                        newRegime=salary*0.25;
                        System.out.println("In new regime tax slab will be "+newRegime);
                    }
                    else{
                        newRegime=salary*0.3;
                        System.out.println("In new regime tax slab will be "+newRegime);
                    }
                    break;
            case 2:  if(salary<250000)
                System.out.println("Exempt in both old and new regims");
            else if(salary>=250000 && salary<500000){
                oldRegime=salary*0.05;
                System.out.println("In old regime tax slab(5%)  for above 250000 and below 500000 will be "+oldRegime);

            }
            else if(salary>=500000 && salary<7500000){
                oldRegime=salary*0.2;
                System.out.println("In old regime tax slab(20%) for above 500000 and below 7500000 will be "+oldRegime);
            }
            else if(salary>=750000 && salary<1000000){
                oldRegime=salary*0.2;
                System.out.println("In old regime tax slab(20%) for above 7500000 and below 1000000 will be "+oldRegime);

            }
            else if(salary>=1000000 && salary<1250000){
                oldRegime=salary*0.3;
                System.out.println("In old regime tax slab(30%) for above 1000000 and below 1250000 will be "+oldRegime);

            }
            else if(salary>=1250000 && salary<1500000){
                oldRegime=salary*0.3;
                System.out.println("In old regime tax slab(30%) for above 1250000 and below 1500000 will be "+oldRegime);

            }
            else{
                oldRegime=salary*0.3;
                System.out.println("In old regime tax slab  for above 1500000 will be "+oldRegime);

            }
            break;
            default:return;

        }



    }

}
