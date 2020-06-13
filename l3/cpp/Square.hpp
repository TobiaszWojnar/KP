/**
 * Creates square
 * It is extends Quadrangle, which extends Figure
 * It inheritance method for calculating circumference and surface area
 * Method for calculating circumference is defined in Quadrangle
 *
 * @author Tobiasz Wojnar
 * excise for university course
 * @see Main.cpp for full requirements
 * @see Square.cpp
 * @see Quadrangle.hpp
 * @see Quadrangle.cpp
 */
#ifndef _SQUARE_HPP
#define _SQUARE_HPP
#include "Quadrangle.hpp"

class Square : public Quadrangle{
public:
    Square (double side1);
    double Surface();
};

#endif //_SQUARE_HPP
