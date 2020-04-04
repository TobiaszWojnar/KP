#include "Kolo.hpp"
#include <cmath>

Kolo :: Kolo (int promien){
   this->promien=promien;
}
double Kolo :: Pole () {
   return promien*promien*M_PI;
}

double Kolo :: Obwod () {
   return promien*2*M_PI;
}
