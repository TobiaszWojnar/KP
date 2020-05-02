import java.awt.*;

public class Triangle extends MyShapes{
    private Color color;
    private int[][] triangle = new int[3][2];//triangle[0] = first point; triangle[1] = second point;

    public Triangle(int[] first, int[] second, int[] third, Color color){
        triangle[0]=first;
        triangle[1]=second;
        triangle[2]=third;
        this.color=color;
    }
    public int[] getX(){
        int[] x = new int[3];
        x[0]=triangle[0][0];
        x[1]=triangle[1][0];
        x[2]=triangle[2][0];
        return x;
    }
    public int[] getY(){
        int[] y = new int[3];
        y[0]=triangle[0][1];
        y[1]=triangle[1][1];
        y[2]=triangle[2][1];
        return y;
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