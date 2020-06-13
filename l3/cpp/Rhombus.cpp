/**
 * Creates rhombus
 * It is extends Quadrangle, which extends Figure
 * It inheritance method for calculating circumference and surface area
 * Method for calculating circumference is defined in Quadrangle
 *
 * @author Tobiasz Wojnar
 * excise for university course
 * @see Main.cpp for full requirements
 * @see Rhombus.hpp
 * @see Quadrangle.hpp
 * @see Quadrangle.cpp
 */
#include "Rhombus.hpp"
#include <cmath>

/**
 * Constructor of rhombus, uses quadrangle constructor
 * @param side all sides are equal and positive, double precision number
 * @param angle in degrees;  double precision number, in range (0,180)
 */
Rhombus :: Rhombus (double side1, double angle):Quadrangle (side1,side1,side1,side1,angle){

}
/**
 * calculates surface area of rhombus
 * important to remember that sin() functions needs angle radians
 *
 * @return surface area of regular rhombus
 */
double Rhombus :: Surface () {
    return side1*side1*sin(M_PI*angle/180);
}