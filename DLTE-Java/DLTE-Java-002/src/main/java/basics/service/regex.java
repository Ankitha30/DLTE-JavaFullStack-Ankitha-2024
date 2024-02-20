package basics.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex {
    public static void main(String[] args) {
        String password="^(?=.[a-zA-Z])(?=.[a-zA-Z0-9])(?=.*[@#$%_]).{8,}";
        Pattern pattern = Pattern.compile((password));
        Matcher matcher = pattern.matcher(args[0]);
        if(matcher.matches())
            System.out.println("valid");
        else
            System.out.println("Invalid");

    }
}
