package l3;

/**
 * Creates quadrangle
 * It extends abstract class Figure and inheritance method for calculating circumference
 * It is extended by 3 subclasses Rhombus, Rectangle and Square
 *
 * @author Tobiasz Wojnar
 * execise for university course
 * @see l3.Figury for full requriments
 * @see l3.Figure
 */
public abstract class Quadrangle extends Figure {
    double[] sides = new double[4];
    double angle;

    /**
     * Constructor of quadrangle, specification required having 4 sides and one angle
     * @param side1 is positive, double precision number; specification required to be the side opposite to side2
     * @param side2 is positive, double precision number; specification required to be the side opposite to side1
     * @param side3 is positive, double precision number; specification required to be the side opposite to side4
     * @param side4 is positive, double precision number; specification required to be the side opposite to side3
     * @param angle in degrees;  double precision number, in range (0,180)
     */
    public Quadrangle (double side1, double side2, double side3, double side4, double angle){//TODO how to create exception if number is not positive
        sides[0]=side1;
        sides[1]=side2;
        sides[2]=side3;
        sides[3]=side4;
        this.angle=angle;
    }

    /**
     * @return circumference of quadrangle, by adding all sides
     */
    public double circumference () {
        return sides[0]+sides[1]+sides[2]+sides[3];
    }
}
