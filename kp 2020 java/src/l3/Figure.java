package l3;

/**
 * Abstract class for creating figures, with methods for calculating surface and circumference
 * It is extended by 4 subclasses, Hexagon, Pentagon, Quadrangle and Circle
 *
 * @author Tobiasz Wojnar
 * execise for university course
 * @see l3.Figury for full requriments
 * @see l3.Hexagon
 * @see l3.Pentagon
 * @see l3.Quadrangle
 * @see l3.Circle
 */
public abstract class Figure {
    /**
     * @return surface area of figure
     */
    public abstract double surface ();

    /**
     * @return circumference of figure
     */
    public abstract double circumference ();
}
