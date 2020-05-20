import java.awt.Color;
/**
 * abstract class enabling having one array of all shapes
 * is extended by
 * @see Circle
 * @see MyRectangle
 * @see Triangle
 */
public interface MyShape {
    /**
     * @param newColor updates color
     */
    void setColor (Color newColor);

    /**
     * @return color of figure
     */
    Color getColor ();

    /**
     * checks if point [x,y] is in figure
     * @param x coordinate of point
     * @param y coordinate of point
     * @return true if figure contains point; false if not
     */
    boolean pointIn(int x, int y);

    /**
     * moves figure by vector [xDistance,yDistance]
     * @param xDistance x coordinate of vector
     * @param yDistance y coordinate of vector
     */
    void move (int xDistance, int yDistance);

    /**
     * resizes dimensions of figure by factor
     * @param factor double
     */
    void resize (double factor);

    /**
     * Method enabling saving to document
     * @return Parameters of figure concatenated into string.
     * Format for returning:
     *      * Upper case letter for determining what type of figure is it ('C', 'R', 'T')
     *      * Points and dimensions
     *      * Color as 3 integers for RGB (0-255)
     */
    String toFile();

    /**
     * @param visitor painter visitor
     */
    void accept(PainterVisitor visitor);
}
