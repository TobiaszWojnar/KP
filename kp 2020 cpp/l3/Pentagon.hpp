/**
 * Creates regular pentagon
 * It extends virtual class Figure and inheritance methods surface and circumference
 * Since it's  regular pentagon it needs only one parameter - side length
 *
 * @author Tobiasz Wojnar
 * excise for university course
 * @see Main.cpp for full requirements
 * @see Pentagon.cpp
 * @see Figure.hpp
 */
#ifndef PENTAGON_HPP
#define PENTAGON_HPP
#include "Figure.hpp"

class Pentagon : public Figure {
public:
    double side;
    Pentagon (double side);
    double Surface ();
    double Circumference ();
};


#endif //PENTAGON_HPP
