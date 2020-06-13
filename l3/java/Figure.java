/**
 * Abstract class for creating figures, with methods for calculating surface and circumference
 * It is extended by 4 subclasses, Hexagon, Pentagon, Quadrangle and Circle
 *
 * @author Tobiasz Wojnar
 * execise for university course
 * @see Figury for full requriments
 * @see Hexagon
 * @see Pentagon
 * @see Quadrangle
 * @see Circle
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
