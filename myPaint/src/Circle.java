import java.awt.*;

public class Circle extends MyShapes{
    private int[] center;
    private double radius;
    Color color;

    Circle(int[] points, double radius, Color color) {
        center = new int[2];
        center[0]=points[0];
        center[1]=points[1];
        this.radius = radius;
        this.color = color;
    }
    public void setRadius(double radius) {
        this.radius = radius;
    }
    public int[] getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public void setColor(Color newColor) {
        color=newColor;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
