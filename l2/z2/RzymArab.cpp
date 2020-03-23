#include <iostream>
#include "RzymArabException.hpp"
#include "RzymArab.hpp"
#include <string>

string RzymArab :: numbers[] =
      {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
      //0     1    2    3     4     5    6     7    8     9    10    11   12
int RzymArab :: values[] =
      { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

int RzymArab :: rzym2arab(string rzym) {
   int result = 0;
   for (int i = 12; i >= 0; i--){
      int j=0;
      while (rzym.find(numbers[i])==0) {
         j++;
         if (j>3)
              throw RzymArabException("wrong roman number (1)");
         result+=values[i];
         rzym=rzym.substr (numbers[i].size(), rzym.size()-numbers[i].size());
         if (i%4!=0) {
              if(i%2==0) {
                  i=i-(i%4)+1;
              } else{
                  i=i-(i%4);
              }
              break;
          }
      }
   }
   if(rzym.size()>0){
      throw RzymArabException("wrong roman number (2)");
   }
   if (result == 0){
      throw RzymArabException("empty number");
   }
   if (result > 3999){
      throw RzymArabException("to big number");
   }
   return result;
}

string RzymArab :: arab2rzym(int arab) {
   string result = "";
   if(arab<4000 && arab>0) {
      for (int i = 12; i >= 0; i--){
          while (arab>=values[i]) {
              result+=numbers[i];
              arab-=values[i];
          }
      }
   } 
   else {
      throw RzymArabException("number out of range");
   }
   return result;
}
