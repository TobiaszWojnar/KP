/**
 * Creates circle
 * It extends virtual class Figure and inheritance methods surface and circumference
 * Since it's  circle it needs only one parameter - radius
 *
 * @author Tobiasz Wojnar
 * excise for university course
 * @see Main.cpp for full requirements
 * @see Circle.cpp
 * @see Figure.hpp
 */
#ifndef _CIRCLE_HPP
#define _CIRCLE_HPP
#include "Figure.hpp"

class Circle : public Figure {
    public:
        double radius;
        Circle (double radius);
        double Surface ();
        double Circumference ();
};

#endif //_CIRCLE_HPP
