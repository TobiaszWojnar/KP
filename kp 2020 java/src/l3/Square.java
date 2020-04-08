package l3;

/**
 * Creates square
 * It is extends Quadrangle, which extends Figure
 * It inheritance method for calculating circumference and surface area
 * Method for calculating circumference is defined in Quadrangle
 *
 * @author Tobiasz Wojnar
 * execise for university course
 * @see l3.Figury for full requriments
 * @see l3.Quadrangle
 * @see l3.Figure
 */
public class Square extends Quadrangle {
    /**
     * Constructor of square, uses quadrangle constructor, with angle of 90 degrees
     * @param side all sides are equal and positive, double precision number
     */
    public Square (double side){ //TODO how to create exception if number is not positive
        super (side,side,side,side,90);
    }

    /**
     * @return surface area of rectangle
     */
    public double surface (){
        return sides[0]*sides[0];
    }
}