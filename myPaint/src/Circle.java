import java.awt.*;

/**
 * Circle has center as integer table, double radius, and color.
 * @see MyShape extedns
 */
public class Circle implements MyShape {
    private final int[] center;
    private double radius;
    Color color;
    /**
     * Circle has
     * @param points center as integer table
     * @param radius double
     * @param color color
     */
    Circle(int[] points, double radius, Color color) {
        center = new int[2];
        center[0]=points[0];
        center[1]=points[1];
        this.radius = radius;
        this.color = color;
    }

    /**
     * @return centre of circle as integer table
     */
    public int[] getCenter() {
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
     * @return returns circle color
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * @param point to be checked
     * @return if point is inside circle
     */
    @Override
    public boolean pointIn(int[] point) {
        int distance = (int)Math.sqrt((point[1] - center[1]) * (point[1] - center[1]) + (point[0] - center[0]) * (point[0] - center[0]));
        return distance <= radius;
    }

    /**
     * Moves circle by vector point>mE
     * @param initialPoint form which we move circle
     * @param finalPoint point to which we move circle
     */
    @Override
    public void move(int[] initialPoint, int[] finalPoint) {
        center[0]+=finalPoint[0]-initialPoint[0];
        center[1]+=finalPoint[1]-initialPoint[1];
    }

    /**
     * @param factor by which circle radius is resized
     */
    @Override
    public void resize(double factor) {
        radius*=factor;
    }

    /**
     * @return Circle parameters
     */
    @Override
    public String toFile() {
        return "C\t"+
        getCenter()[0]+"\t"+getCenter()[1]+"\t"+
        getRadius()+"\t"+
        getColor().getRed()+"\t"+getColor().getGreen()+"\t"+getColor().getBlue()+"\n";
    }

    @Override
    public void accept(PainterVisitor visitor) {
        visitor.paint(this);
    }
}
