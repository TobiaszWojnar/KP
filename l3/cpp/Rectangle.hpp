/**
 * Creates rectangle
 * It is extends Quadrangle, which extends Figure
 * It inheritance method for calculating circumference and surface area
 * Method for calculating circumference is defined in Quadrangle
 *
 * @author Tobiasz Wojnar
 * excise for university course
 * @see Main.cpp for full requirements
 * @see Rectangle.cpp
 * @see Quadrangle.hpp
 * @see Quadrangle.cpp
 */
#ifndef _RECTANGLE_HPP
#define _RECTANGLE_HPP
#include "Quadrangle.hpp"

class Rectangle : public Quadrangle{
public:
    Rectangle (double side1, double side2);
    double Surface();
};

#endif //_RECTANGLE_HPP
