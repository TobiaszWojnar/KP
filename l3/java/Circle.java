/**
 * Creates circles
 * It extends abstract class Figure and inheritance methods surface and circumference
 *
 * @author Tobiasz Wojnar
 * execise for university course
 * @see l3.Figury for full requriments
 * @see l3.Figure
 */
public class Circle extends Figure {
    double radius;

    /**
     * Constructor of circle, requires only one parameter
     * @param radius of circle, is positive, double precision number
     */
    public Circle (double radius){//TODO how to create exception if number is not positive
        this.radius = radius;
    }

    /**
     * @return surface area of circle
     */
    public double surface (){
        return Math.PI* radius * radius;
    }

    /**
     * @return circumference of circle
     */
    public double circumference (){
        return Math.PI* radius *2;
    }
}
