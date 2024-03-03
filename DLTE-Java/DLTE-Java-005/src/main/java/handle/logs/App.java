package handle.logs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        final String name="Ankitha";
//        System.out.println( "Hello World!" );
//        name="Anu";
        ArrayList a = new ArrayList();
        CreditCard c=new CreditCard(8765678765678L,"Ankitha",new Date(2034,12,30),555,100000,new Date(2024,3,11),new Date(2024,03,30),1111);
        a.add(c);
        System.out.println(a);

    }
}
