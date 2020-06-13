/**
 * Creates regular hexagon
 * It extends virtual class Figure and inheritance methods surface and circumference
 *
 * @author Tobiasz Wojnar
 * excise for university course
 * @see Main.cpp for full requriments
 * @see Hexagon.hpp
 * @see Figure.hpp
 */
#include "Hexagon.hpp"
#include <cmath>
/**
 * Constructor of hexagon, since we create only regular hexagons it needs only one parameter
 * @param side of hexagon, is positive, double precision number
 */
Hexagon :: Hexagon (double side){
    this->side=side;
}
/**
 * @return surface area of regular hexagon
 */
double Hexagon :: Surface () {
    return side*side*sqrt(27)/2;
}
/**
 * @return circumference of regular hexagon
 */
double Hexagon :: Circumference () {
    return side*6;
}