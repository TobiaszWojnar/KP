/**
 * Creates regular pentagon
 * It extends virtual class Figure and inheritance methods surface and circumference
 *
 * @author Tobiasz Wojnar
 * excise for university course
 * @see Main.cpp for full requirements
 * @see Pentagon.hpp
 * @see Figure.hpp
 */
#include "Pentagon.hpp"
#include <cmath>

/**
 * Constructor of pentagon, since we create only regular pentagon it needs only one parameter
 * @param side of pentagon, is positive, double precision number
 */
Pentagon :: Pentagon (double side){
    this->side=side;
}
/**
 * @return surface area of regular pentagon
 */
double Pentagon :: Surface () {
    return side*side*1.25/tan(M_PI/5);
}
/**
 * @return circumference of regular pentagon
 */
double Pentagon :: Circumference () {
    return side*5;
}