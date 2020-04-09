/**
 * Creates rhombus
 * It is extends Quadrangle, which extends Figure
 * It inheritance method for calculating circumference and surface area
 * Method for calculating circumference is defined in Quadrangle
 *
 * @author Tobiasz Wojnar
 * excise for university course
 * @see Main.cpp for full requirements
 * @see Rhombus.cpp
 * @see Quadrangle.hpp
 * @see Quadrangle.cpp
 */
#ifndef _RHOMBUS_HPP
#define _RHOMBUS_HPP
#include "Quadrangle.hpp"

class Rhombus : public Quadrangle{
public:
    Rhombus (double side1, double angle);
    double Surface();
};

#endif //_RHOMBUS_HPP
