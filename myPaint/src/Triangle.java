import java.awt.*;

public class Triangle extends MyShapes{
    private Color color;
    private int[][] points = new int[2][3];//triangle[0] = first point; triangle[1] = second point;

    public Triangle(int[] x, int[] y, Color color){
        points[0]=x;
        points[1]=y;
        this.color=color;
    }
    public int[] getX(){
        return points[0];
    }
    public int[] getY(){
        return points[1];
    }
    public int getX(int point){
        return points[0][point];
    }
    public int getY(int point){
        return points[1][point];
    }
    @Override
    public void setColor(Color newColor) {
        color=newColor;
    }

    @Override
    public Color getColor() {
        return color;
    }

    public void setSecond(int x, int y) {
        points[0][1]=x;
        points[1][1]=y;
    }
    public void setThird(int x, int y) {
        points[0][2]=x;
        points[1][2]=y;
    }
}