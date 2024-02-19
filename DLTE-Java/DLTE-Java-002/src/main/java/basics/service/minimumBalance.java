package basics.service;

import java.util.Arrays;
import java.util.Scanner;

public class minimumBalance {
    public static void main(String[] args) {
        double[] balance = new double[20];
        Scanner s = new Scanner(System.in);
        for(int index=0; index<balance.length;index++)
        {
            System.out.println("Enter the the balance");
            balance[index] = s.nextDouble();
        }
        System.out.println(Arrays.toString(balance));
        minimumBalance.updateBalance(balance);
        System.out.println(Arrays.toString(balance));

    }
    public static void updateBalance(double[] arr){
        for(int index=0; index<arr.length; index++)
        {
            if(arr[index]>=5000 && arr[index]<10000)
                arr[index]=arr[index]-(arr[index]*0.03);
            else if(arr[index]>= 1000 && arr[index] < 5000)
                arr[index]=arr[index]-(arr[index]*0.05);
        }
        return;
    }
}
