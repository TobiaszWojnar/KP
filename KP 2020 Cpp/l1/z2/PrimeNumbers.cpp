/**
 * @author Tobiasz Wojnar
 * Java Programing Course 2020
 * list 1 exercise 2
 * create public class PrimeNumbers that creates table of prime numbers in range 1,..,n
 * and method number that returns m-th prime number form that table
 **/


#include <iostream>
#include "PrimeNumbers.hpp"
  
int PrimeNumbers::number (int m) {
  return tab [m];
}
PrimeNumbers::PrimeNumbers (int n) {
  bool tabbool[n+1];
  for (int i = 0; i <= n; i++) {
  tabbool [i] = false;
  }
  int counter = 0;
  for(int i = 2; i<=n; i++) {                   //Sieve of Eratosthenes
     if (! tabbool [i]){
        counter ++;
        for (int j = i+i; j <= n; j += i) {
           tabbool [j] = true;
        }
     }
  }
  length = counter;
  tab = new int [counter];
  int j = 0;
  for(int i = 2; i<=n; i++) {                   //Sieve of Eratosthenes
     if (! tabbool [i]){
        tab [j]=i;
        j++;
     }
  }
}
PrimeNumbers::~PrimeNumbers(){delete [] tab;}
