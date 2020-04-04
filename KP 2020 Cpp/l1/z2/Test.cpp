/**
 * @author Tobiasz Wojnar
 * Java Programing Course 2020
 * list 1 exercise 2
 * create public class PrimeNumbers that creates table of prime numbers in range 1,..,n
 * and method number that returns m-th prime number form that table
 **/

#include <iostream>
#include <sstream>
#include "PrimeNumbers.hpp"

using namespace std;

int main (int argc, char* parameters []) {
   if (argc < 2)
      return -4;
   int n;
   istringstream in(parameters[1]);
   if (!(in >> n)) {
      cout << parameters[1] << " - not integer\n";
      return -1;
   }
   if (n < 1) {
      cout << n << " - wrong range\n";
      return -2;
   }
   PrimeNumbers table = PrimeNumbers (n);

   for (int i = 2; i < argc; i++)
   {
      istringstream input(parameters[i]);
      if (!(input >> n)) {
         cout << parameters[i] << " - wrong data\n";
         return -3;
      }
         if (n<0 || n>=table.length) {
           cout << n << " - number out of range\n";
           continue;
         }
         else {
            cout << n << " - " << table.number (n) << endl;
         }
   }
   return 0;
}

