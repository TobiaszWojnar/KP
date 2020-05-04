import java.awt.*;
import java.awt.event.MouseEvent;

public class Triangle extends MyShapes{
    private Color color;
    private int[][] points = new int[2][3];//triangle[0] = X; triangle[1] = Y;

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

    @Override
    public boolean pointIn(MouseEvent mE) {
        Polygon p = new Polygon(getX(),getY(),3);
        return p.contains(mE.getX(),mE.getY());
    }

    @Override
    public void move(int[] point, MouseEvent mE) {
        points[0][0]+=mE.getX()-point[0];
        points[0][1]+=mE.getX()-point[0];
        points[0][2]+=mE.getX()-point[0];
        points[1][0]+=mE.getY()-point[1];
        points[1][1]+=mE.getY()-point[1];
        points[1][2]+=mE.getY()-point[1];

    }

    @Override
    public void resize(double factor) {
        points[0][1]+= (int) ((points[0][0]-points[0][1])*(1-factor));
        points[0][2]+= (int) ((points[0][0]-points[0][2])*(1-factor));

        points[1][1]+= (int) ((points[1][0]-points[1][1])*(1-factor));
        points[1][2]+= (int) ((points[1][0]-points[1][2])*(1-factor));
    }

    @Override
    public String toFile() {
        return "T\t"+getX(0)+"\t"+getX(1)+"\t"+getX(2)+"\t"+getY(0)+"\t"+getY(1)+"\t"+getY(2)+"\t"+getColor().getRed()+"\t"+getColor().getGreen()+"\t"+getColor().getBlue()+"\n";
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