import java.awt.*;

/**
 * Circle has center as integer table, double radius, and color.
 * @see MyShape extedns
 */
public class Circle implements MyShape {
    private final Point center;
    private double radius;
    private Color color;
    /**
     * Circle has
     * @param point center
     * @param radius double
     * @param color color
     */
    Circle(Point point, double radius, Color color) {
        center = point;
        this.radius = radius;
        this.color = color;
    }

    /**
     * @return centre of circle as Point
     * @see Point
     */
    public Point getCenter() {
        return center;
    }
    /**
     * Updates radius
     * @param radius new radius
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * @return radius of circle
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @param newColor updates color
     */
    @Override
    public void setColor(Color newColor) {
        color=newColor;
    }

    /**
     * @return returns color of circle
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * checks if point [x,y] is in figure
     * @param x coordinate of point
     * @param y coordinate of point
     * @return true if circle contains point
     * if distance between point and circle centre is smaller or equal to radius
     */
    @Override
    public boolean pointIn(int x, int y) {
        double distance = center.distance(x,y);
        return distance <= radius;
    }

    /**
     * moves circle centre by vector [xDistance,yDistance]
     * @param xDistance x coordinate of vector
     * @param yDistance y coordinate of vector
     */
    @Override
    public void move(int xDistance, int yDistance) {
        center.x+=xDistance;
        center.y+=yDistance;
    }

    /**
     * @param factor by which circle radius is resized
     */
    @Override
    public void resize(double factor) {
        radius*=factor;
    }

    /**
     * Method enabling saving to document
     * @return Parameters of figure concatenated into string.
     * Format for returning:
     *      * Upper case letter for determining what type of figure is it ('C')
     *      * x coordinate of centre, y coordinate of centre, radius
     *      * Color as 3 integers for RGB (0-255)
     */
    @Override
    public String toFile() {
        return "C\t"+
        center.x+"\t"+center.y+"\t"+
        getRadius()+"\t"+
        getColor().getRed()+"\t"+getColor().getGreen()+"\t"+getColor().getBlue()+"\n";
    }

    /**
     * @param visitor painter visitor
     */
    @Override
    public void accept(PainterVisitor visitor) {
        visitor.paint(this);
    }

}
