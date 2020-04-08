package l3;

/**
 * Creates rhombus
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
public class Rhombus extends Quadrangle{
    /**
     * Constructor of rhombus, uses quadrangle constructor
     * @param side all sides are equal and positive, double precision number
     * @param angle in degrees;  double precision number, in range (0,180)
     */
    public Rhombus (double side, double angle){//TODO how to create exception if number is not positive
        super (side, side, side, side, angle);
    }

    /**
     * calculates surface area of rhombus
     * important to remember that Math.sin() functions needs angle radians
     *
     * @return surface area of regular rhombus
     */
    public double surface () {
        return sides[0]*sides[0]*Math.sin(Math.toRadians(angle));
    }
}