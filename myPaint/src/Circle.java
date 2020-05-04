import java.awt.*;
import java.awt.event.MouseEvent;

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

    @Override
    public boolean pointIn(MouseEvent mE) {
        int distance = (int)Math.sqrt((mE.getY() - center[1]) * (mE.getY() - center[1]) + (mE.getX() - center[0]) * (mE.getX() - center[0]));
        if(distance<=radius){
            return true;
        }
        return false;
    }

    @Override
    public void move(int[] point, MouseEvent mE) {
        center[0]+=mE.getX()-point[0];
        center[1]+=mE.getY()-point[1];
    }

    @Override
    public void resize(double factor) {
        radius*=factor;
    }

    @Override
    public String toFile() {
        String value =
                "C\t"+
                getCenter()[0]+"\t"+getCenter()[1]+"\t"+
                getRadius()+"\t"+
                getColor().getRed()+"\t"+getColor().getGreen()+"\t"+getColor().getBlue()+"\n";
        System.out.println(value);
        return value;
    }
}
