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
        for (int i = 0; i <= n; i++)                  //initialise
            tabbool[i] = false;

        int counter = 0;
        for(int i = 2; i<=n; i++) {                   //Sieve of Eratosthenes
            if (! tabbool [i]){
                counter ++;
                for (int j = i+i; j <= n; j += i) {
                    tabbool [j] = true;
                }
            }
        }
        tab = new int [counter];
        int j = 0;
        for(int i = 2; i<=n; i++) {                   //Sieve of Eratosthenes
            if (! tabbool [i]){
                tab [j]=i;
                j++;
            }
        }
    }
}
