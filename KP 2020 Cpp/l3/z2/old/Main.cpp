#include <iostream>
#include <stdexcept>
#include <sstream>
#include <cstring>
#include "Figura.hpp"
#include "Czworokat.hpp"
#include "Kolo.hpp"
#include "Kwadrat.hpp"
#include "Pieciokat.hpp"
#include "Prostakat.hpp"
#include "Szesciokat.hpp"
#include "Romb.hpp"


using namespace std;

int main (int ilosc, char* dane []) {

   int nrParam = 2;
   int nrFigury;
   int i;
   int czworakat[5];
   Figura* figury [strlen(dane[1])];
   bool prawda;
   for (nrFigury = 0; nrFigury < strlen(dane[1]); nrFigury++) {
      figury [nrFigury] = NULL;
   }
      for (nrFigury = 0; nrFigury < strlen(dane[1]); nrFigury++) {
      if (nrParam > ilosc-1) {      //warunek dla o, p, s
         cout <<"nie ma wystarczajaco argomentow\n";
         break;
      }
      switch (dane[1][nrFigury]){
      case 'o':
      case 'p':
      case 's': {  istringstream in (dane[nrParam]); 
                  if (in >> i) {
                     if(i<=0)
                        cout << dane[nrParam] <<" nie jest liczba naturalna\n";
                     else {
                        switch (dane[1][nrFigury]){
                           case 'o': figury [nrFigury] = new Kolo (i);   break;
                           case 'p': figury [nrFigury] = new Pieciokat (i);   break;
                           case 's': figury [nrFigury] = new Szesciokat (i);   break;
                        }
                     }
                   } else{
                      cout << dane[nrParam] << " nie jest liczba calkowita\n";
                   }
                nrParam++;
                break;}
      case 'c':{ if (nrParam <= ilosc-5) {  //sprawdzam czy wszystkie dane o czworokacie sa sensowne
                     prawda = true;
                     for (int ParamChwil = 0; ParamChwil < 5; ParamChwil++) {
                        istringstream in(dane[nrParam+ParamChwil]);
                        if (in >> i) {
                           if(i<=0){
                              cout << dane[nrParam+ParamChwil] << "nie jest liczba naturalna\n";
                              prawda = false;
                              break;
                           } else
                           czworakat [ParamChwil]=i;
                        } else {
                           cout << dane[nrParam+ParamChwil] <<" nie jest liczba calkowita\n";
                           prawda = false;
                           break;
                        }
                     }
                     if (prawda){
                         if(czworakat [0]!=czworakat [2] || czworakat [1]!=czworakat [3]){
                            cout << "nie jest to rownoleglobok\n";   
                         } else if (czworakat [4]>=180){
                            cout << czworakat [4] << " jest za duzym katem\n";   
                         } else if (czworakat [4]==90&&czworakat [0]==czworakat [1]){
                            figury [nrFigury]= new Kwadrat (czworakat [0]); 
                         } else if (czworakat [0]==czworakat [1]){
                            figury [nrFigury]= new Romb (czworakat [0], czworakat [4]);
                         } else if (czworakat [4]==90){
                            figury [nrFigury]= new Prostakat (czworakat [0], czworakat [1]);
                         } else{
                            cout << "figura jest rownoleglobokiem\n";  
                         }
                      }
                }
                nrParam += 5;
                break;}
      default:  {cout << "nie ma takiej figury\n";}
      }
   }
   Figura* zmienna;
   for (nrFigury = 0; nrFigury < strlen(dane[1]); nrFigury++) {
      zmienna = figury [nrFigury];
      if (zmienna!=NULL) {
         cout << "pole: "<< zmienna->Pole() <<"; obwod: " << zmienna->Obwod() <<";\n";
         delete figury [nrFigury];
      }
   }
   return 0;
}
