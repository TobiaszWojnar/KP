/**
 * Creates regular hexagon
 * It extends virtual class Figure and inheritance methods surface and circumference
 * Since it's  regular hexagon it needs only one parameter - side length
 *
 * @author Tobiasz Wojnar
 * excise for university course
 * @see Main.cpp for full requriments
 * @see Hexagon.cpp
 * @see Figure.hpp
 */
#ifndef HEXAGON_HPP
#define HEXAGON_HPP
#include "Figure.hpp"

class Hexagon : public Figure {
public:
    double side;
    Hexagon (double side);
    double Surface ();
    double Circumference ();
};


#endif //HEXAGON_HPP
