/**
 * Creates regular hexagon
 * It extends abstract class Figure and inheritance methods surface and circumference
 *
 * @author Tobiasz Wojnar
 * execise for university course
 * @see Figury for full requriments
 * @see Figure
 */
public class Hexagon extends Figure {
    double side;

    /**
     * Constructor of hexagon, since we create only regular hexagons it needs only one parameter
     * @param side of hexagon, is positive, double precision number
     */
    public Hexagon (double side){//TODO how to create exception if number is not positive
        this.side=side;
    }

    /**
     * @return surface area of regular hexagon
     */
    public double surface (){
        return side*side* Math.sqrt(27);
    }

    /**
     * @return circumference of regular hexagon
     */
    public double circumference (){
        return 6*side;
    }
}
