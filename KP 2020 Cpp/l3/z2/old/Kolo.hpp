#include "Figura.hpp"

class Kolo : public Figura {
   public:
      int promien;
      Kolo (int promien);
      double Pole ();
      double Obwod ();
};
