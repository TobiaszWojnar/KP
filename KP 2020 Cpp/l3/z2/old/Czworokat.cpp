#include <iostream>
#include "Czworokat.hpp"

Czworokat :: Czworokat (int bok1, int bok2, int bok3, int bok4, int kat){
   this->bok1=bok1;
   this->bok2=bok2;
   this->bok3=bok3;
   this->bok4=bok4;
   this->kat=kat;
}

double Czworokat :: Obwod () {
   return bok1+bok2+bok3+bok4;
}
