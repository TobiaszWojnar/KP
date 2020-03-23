/**
 * @author Tobiasz Wojnar
 * Java Programing Course 2020
 * list 1 exercise 1
 * create public class PrimeNumbers that creates table of prime numbers in range 1,..,n
 * and method number that returns m-th prime number form that table
 **/

import java.util.*;

public class Test {
    public static void main(String[] args) {
        int n;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException ex) {
            System.out.println(args[0] + " - not integer");
            return;
        }
        if (n < 1) {
            System.out.println(n + " - wrong range");
            return;
        }
        PrimeNumbers table = new PrimeNumbers(n);

        for (int i = 1; i < args.length; i++)     //
        {
            try {
                n = Integer.parseInt(args[i]);
            } catch (NumberFormatException ex) {
                System.out.println(args[i] + " - wrong data");
                return;
            }
            if (n < 0 || n >= table.tab.length) {
                System.out.println(n + " - number out of range");
                continue;
            } else {
                System.out.println(n + " - " + table.number(n));
            }
        }
    }
}

/*

}*/
