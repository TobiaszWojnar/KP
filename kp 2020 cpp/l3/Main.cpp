#include <iostream>
#include <stdexcept>
#include <sstream>
#include <cstring>
#include "Figure.hpp"
#include "Circle.hpp"
#include "Pentagon.hpp"
#include "Hexagon.hpp"
#include "Quadrangle.hpp"
#include "Rhombus.hpp"
#include "Square.hpp"
#include "Rectangle.hpp"

using namespace std;
/**
 * @author Tobiasz Wojnar
 * Object Programing Course 2020 list 3 exercise 2
 * Calculate surface area and circumference of:
     * circle, quadrangle (square, rectangle, rhombus), regular pentagon, regular hexagon
 * implement hierarchy and abstract class Figure
 * handle exeptions and bad data
 *
 * parameters first letter of figure (char) and parameters (double)
 *  > for circle			    - radius
 *  > for quadrangle			- 4 sides and angle
 *  > for pentagon and hexagon	- side
 *  > eg.: java figures q 8 8 4 4 90
 *
 * @param argc length of argv
 * @param argv[0] program name;
 * @param argv[1] first letter of figure {c,q,p,h}
 * @param argv[i] parameters of figure:
 *             >	for circle			        - radius
 *             >	for quadrangle			    - 4 sides and angle
 *             >	for pentagon and hexagon	- side
 *             > eg.: java Figures q 8 8 4 4 90
 * @return 0, for error returns number <-1; -8>
 */
int main(int argc, char* argv[]) {
    if(argc!=3&&argc!=7){
        cout << "wrong number of parameters\n";
        return -1;
    }
    if (strlen(argv[1])!=1){
        cout << "figure "<< argv[1] <<" does not exist\n";
        return -2;
    }
    if (argv [1][0]!='c'&& argv [1][0]!='p' && argv [1][0]!='q' && argv [1][0]!='h'){
        cout << "figure "<< argv[1] <<" undefined\n use {c,q,p,h}";
        return -3;
    }
    if (argv [1][0]=='q'){
        if(argc!=7) {
            cout << "wrong number of parameters for quadrangle\n should be length of 4 sides and angle";
            return -4;
        }
    } else{
        if(argc!=3) {
            cout << "wrong number of parameters\n should be one number\n";
            return -5;
        }
    }
    double param [argc-2];
    for (int i=2; i< argc; i++){
        param[i-2]=atof(argv[i]); //if its not a number surface=0 and circumference=0
        if (param[i-2]<0) {
            cout << param[i-2] <<" is not a positive number\n";
            return -6;
        }
    }

    Figure* f;
    switch (argv [1][0]){
        case 'c':
            f = new Circle(param[0]);
            break;
        case 'p':
            f = new Pentagon(param[0]);
            break;
        case 'h':
            f = new Hexagon(param[0]);
            break;
        case 'q':
            if(param[0]==param[1]&&param[2]==param[3]&&param[4]<180){
                if (param[4]!=90){
                    if(param[0]==param[2]){
                        f = new Rhombus(param[0],param[4]);
                    } else {
                        cout << "can't create such quadrangle\n";
                        return 0;
                    }
                }else{
                    if(param[0]==param[2]){
                        f = new Square(param[0]);
                    } else{
                        f = new Rectangle(param[0],param[2]);
                    }
                }
            } else{
		cout << "can't create such quadrangle\n";
                return -7;
	    }
            break;
        default:
	    cout << "should not be here\n";
            return -8;
    }
    cout << "Surface: "<< f->Surface() <<"; Circumference: " << f->Circumference() <<";\n";
    delete f;
    return 0;
}
