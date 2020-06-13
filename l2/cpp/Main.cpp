/**
 * @author Tobiasz Wojnar
 * Java Programing Course 2020
 * list 2 exercise 2
 * Implement method translating arabic to roman numbers
 **/

#include <iostream>
#include <stdexcept>
#include <sstream>
#include "RzymArabException.hpp"
#include "RzymArab.hpp"

using namespace std;

int main (int arg, char* numbers []) {
   int i,j;
   int n;
      istringstream in(numbers[j]);
      if (!(in >> n)) { //roman numbers
         try {
            i = RzymArab::rzym2arab(numbers[j]);
            cout<<numbers[j]<<" - "<<i<<";"<<endl;
         }
         catch (RzymArabException k) {
            cout<<numbers[j]<<" - "<<k.what()<<";  "<<endl;
         }
      } else { //arabic number
         try {
            cout<<numbers[j]<<" - "<<RzymArab::arab2rzym(n)<<";"<<endl;
         }
         catch (RzymArabException k) {
            cout<<numbers[j]<<" - "<<k.what()<<";  "<<endl;
         }
      }
   return 0;
}
