/**
 * Creates square
 * It is extends Quadrangle, which extends Figure
 * It inheritance method for calculating circumference and surface area
 * Method for calculating circumference is defined in Quadrangle
 *
 * @author Tobiasz Wojnar
 * excise for university course
 * @see Main.cpp for full requirements
 * @see Square.hpp
 * @see Quadrangle.hpp
 * @see Quadrangle.cpp
 */
#include "Square.hpp"

/**
 * Constructor of square, uses quadrangle constructor, with angle of 90 degrees
 * @param side all sides are equal and positive, double precision number
 */
Square ::Square(double side1) :Quadrangle (side1,side1,side1,side1,90){

}

/**
 * @return surface area of square
 */
double Square :: Surface () {
    return side1*side1;
}