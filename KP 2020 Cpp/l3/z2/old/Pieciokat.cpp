#include "Pieciokat.hpp"
#include <cmath>

Pieciokat :: Pieciokat (int bok){
   this->bok=bok;
}
double Pieciokat :: Pole () {
   return (1.25*bok*bok/tan(M_PI/5));
}

double Pieciokat :: Obwod () {
   return bok*5;
}
