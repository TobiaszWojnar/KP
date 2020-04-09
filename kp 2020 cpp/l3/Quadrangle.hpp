/**
* Creates quadrangle
* It extends abstract class Figure and inheritance method for calculating circumference
* It is extended by 3 subclasses Rhombus, Rectangle and Square
 * *
 * @author Tobiasz Wojnar
 * excise for university course
 * @see Main.cpp for full requirements
 * @see Rhombus.cpp
 * @see Rectangle.cpp
 * @see Square.cpp
 * @see Figure.hpp
 */

#ifndef _QUADRANGLE_HPP
#define _QUADRANGLE_HPP
#include "Figure.hpp"

class Quadrangle : public Figure{
public:
    double side1;
    double side2;
    double side3;
    double side4;
    double angle;
    Quadrangle (double side1,double side2,double side3,double side4, double angle);
    double Circumference ();
};


#endif //_QUADRANGLE_HPP
