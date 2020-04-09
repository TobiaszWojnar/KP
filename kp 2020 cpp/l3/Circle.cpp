/**
 * Creates circle
 * It extends virtual class Figure and inheritance methods surface and circumference
 *
 * @author Tobiasz Wojnar
 * excise for university course
 * @see Main.cpp for full requirements
 * @see Circle.hpp
 * @see Figure.hpp
 */
#include "Circle.hpp"
#include <cmath>

/**
 * Constructor of circle, since we create circle it needs only one parameter
 * @param radius is positive, double precision number
 */
Circle :: Circle (double radius){
    this->radius=radius;
}
/**
 * @return surface area of circle
 */
double Circle :: Surface () {
    return radius*radius*M_PI;
}
/**
 * @return circumference of circle
 */
double Circle :: Circumference () {
    return radius*2*M_PI;
}