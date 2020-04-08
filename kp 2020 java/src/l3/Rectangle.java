package l3;

/**
 * Creates rectangle
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
public class Rectangle extends Quadrangle {
    /**
     * Constructor of rectangle, uses quadrangle constructor, with angle of 90 degrees
     * specification required opposite sides to be given one net to the other
     * @param side1 one of two sides in rectangle, is positive, double precision number
     * @param side2 second of two sides in rectangle, is positive, double precision number
     */
    public Rectangle (double side1, double side2){//TODO how to create exception if number is not positive
        super (side1,side1,side2,side2,90);
    }

    /**
     * @return surface area of rectangle
     */
    public double surface (){
        return sides[0]*sides[2];
    }
}
