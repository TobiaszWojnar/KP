/**
 * @author Tobiasz Wojnar
 * Java Programing Course 2020
 * list 1 exercise 2
 * create public class PrimeNumbers that creates table of prime numbers in range 1,..,n
 * and method number that returns m-th prime number form that table
 **/

class PrimeNumbers {
   public:
   int* tab;
   int length;
   int number (int m);
   PrimeNumbers (int n);
   ~PrimeNumbers();
};

