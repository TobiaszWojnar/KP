package l3;

/**
 * Creates regular pentagons
 * It extends abstract class Figure and inheritance methods surface and circumference
 *
 * @author Tobiasz Wojnar
 * execise for university course
 * @see l3.Figury for full requriments
 * @see l3.Figure
 */
public class Pentagon extends Figure {
    double side;

    /**
     * Constructor of pentagon, since we create only regular pentagons it needs only one parameter
     * @param side of pentagon, is positive, double precision number
     */
    public Pentagon (double side){//TODO how to create exception if number is not positive
        this.side=side;
    }

    /**
     * calculates surface area of regular pentagon
     * important to remember that Math.tan() functions needs angle radians
     *
     * @return surface area of regular pentagon
     */
    public double surface (){
        return 1.25*side*side /Math.tan(Math.toRadians(36));
    }

    /**
     * @return circumference of regular pentagon
     */
    public double circumference (){
        return 5*side;
    }
}