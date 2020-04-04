#include "Romb.hpp"
#include <cmath>

Romb :: Romb (int bok1, int kat):Czworokat(bok1, bok1, bok1, bok1, kat) {}

double Romb :: Pole () {
   return bok1*bok1*sin(M_PI*kat/180);
}
