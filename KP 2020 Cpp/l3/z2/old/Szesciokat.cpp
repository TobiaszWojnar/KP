#include "Szesciokat.hpp"
#include <cmath>

Szesciokat :: Szesciokat (int bok){
   this->bok=bok;
}
double Szesciokat :: Pole () {
   return (bok*bok*sqrt(3)*3)/2;
}

double Szesciokat :: Obwod () {
   return bok*6;
}
