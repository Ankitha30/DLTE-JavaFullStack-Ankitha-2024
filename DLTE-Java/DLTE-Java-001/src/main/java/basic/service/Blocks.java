package basic.service;

import java.util.Arrays;
/*
Scopes overloading execution point
 */


public class Blocks {
    static{
        System.out.println("funds,acc,loan,service");
        Blocks.main(new  String[]{"main"});
    }
    public static void main(Integer[] args) {
        System.out.println(args.length+" "+" CLI Banking111");
    }
    public static void main(String[] args) {
        Blocks.main(new  Integer[]{4,6});
        System.out.println("String parameters : CLI Banking");
        System.out.println(Arrays.toString(args));

    }


}


class Facility{
    public static void main(String[] args) {
        System.out.println(" ATM");
    }

}
