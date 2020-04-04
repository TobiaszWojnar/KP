#include "Prostakat.hpp"
#include <cmath>

Prostakat :: Prostakat (int bok1, int bok2) : Czworokat(bok1, bok2, bok1, bok2, kat){}

double Prostakat :: Pole () {
   return bok1*bok2;
}
