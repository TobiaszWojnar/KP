package l1;

/**
 * @author Tobiasz Wojnar
 * Java Programing Course 2020
 * list 1 exercise 1
 * create public class PrimeNumbers that creates table of prime numbers in range 1,..,n
 * and method number that returns m-th prime number form that table
 **/


public class PrimeNumbers {
    public int[] tab;
    public int number (int m) {
        return tab [m];
    }
    public PrimeNumbers (int n) {

        boolean[] tabbool = new boolean [n+1];
        for (int i = 0; i <= n; i++)                  //initialise a table for finding prime numbers
            tabbool[i] = false;

        int counter = 0;                              //counts how many prime numbers did we find
        for(int i = 2; i<=n; i++) {                   //Sieve of Eratosthenes
            if (! tabbool [i]){                       //if a number has not been changed to true it is prime
                counter ++;
                for (int j = i+i; j <= n; j += i) {   //in that case we mark every multiple of this number as true
                    tabbool [j] = true;
                }
            }
        }
        tab = new int [counter];                      //a new table has only primes
        int j = 0;
        for(int i = 2; i<=n; i++) {
            if (! tabbool [i]){                       //if the number has not been changed to true it is prime
                tab [j]=i;                            //we put it in a new table
                j++;
            }
        }
    }
}
