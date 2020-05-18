import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Circle has center as integer table, double radius, and color.
 * @see MyShapes extedns
 */
public class Circle implements MyShapes{
    private int[] center;
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
     * @param mE point to be checked
     * @return if point is inside circle
     */
    @Override
    public boolean pointIn(MouseEvent mE) {
        int distance = (int)Math.sqrt((mE.getY() - center[1]) * (mE.getY() - center[1]) + (mE.getX() - center[0]) * (mE.getX() - center[0]));
        return distance <= radius;
    }

    /**
     * Moves circle by vector point>mE
     * @param point form which we move circle
     * @param mE point to which we move circle
     */
    @Override
    public void move(int[] point, MouseEvent mE) {
        center[0]+=mE.getX()-point[0];
        center[1]+=mE.getY()-point[1];
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
}
