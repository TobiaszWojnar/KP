/**
 * Creates quadrangle
 * It extends abstract class Figure and inheritance method for calculating circumference
 * It is extended by 3 subclasses Rhombus, Rectangle and Square
 *
 * @author Tobiasz Wojnar
 * excise for university course
 * @see Main.cpp for full requriments
 * @see Rhombus.hpp
 * @see Rectangle.hpp
 * @see Square.hpp
 * @see Figure.hpp
 */

#include <iostream>
#include "Quadrangle.hpp"
/**
 * Constructor of quadrangle, since we create only regular hexagons it needs only one parameter
 * @param side1, side2, side3, side4 - sides of quadrangle, are positive, double precision numbers
 * @param angle in degrees;  double precision number, in range (0,180)
 */
Quadrangle :: Quadrangle (double side1,double side2,double side3,double side4, double angle){
    this->side1=side1;
    this->side2=side2;
    this->side3=side3;
    this->side4=side4;
    this->angle=angle;
}
/**
 * @return circumference of quadrangle, sum of its sides
 */
double Quadrangle :: Circumference () {
    return side1+side2+side3+side4;
}