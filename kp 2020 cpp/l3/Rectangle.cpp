/**
 * Creates rectangle
 * It is extends Quadrangle, which extends Figure
 * It inheritance method for calculating circumference and surface area
 * Method for calculating circumference is defined in Quadrangle
 *
 * @author Tobiasz Wojnar
 * excise for university course
 * @see Main.cpp for full requirements
 * @see Rectangle.hpp
 * @see Quadrangle.hpp
 * @see Quadrangle.cpp
 */
#include "Rectangle.hpp"

/**
 * Constructor of rectangle, uses quadrangle constructor, with angle of 90 degrees
 * specification required opposite sides to be given one net to the other
 * @param side1 one of two sides in rectangle, is positive, double precision number
 * @param side2 second of two sides in rectangle, is positive, double precision number
 */
Rectangle :: Rectangle (double side1, double side3):Quadrangle (side1,side1,side3,side3,90){

}
/**
 * @return surface area of rectangle
 */
double Rectangle :: Surface () {
    return side1*side3;
}