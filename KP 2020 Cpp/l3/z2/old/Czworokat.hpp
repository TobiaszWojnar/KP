#ifndef _Czworokat_HPP
#define _Czworokat_HPP

#include "Figura.hpp"

class Czworokat : public Figura {
   public:
      int bok1;
      int bok2;
      int bok3;
      int bok4;
      int kat;
      Czworokat (int bok1, int bok2, int bok3, int bok4, int kat);
      double Obwod ();
};

#endif
