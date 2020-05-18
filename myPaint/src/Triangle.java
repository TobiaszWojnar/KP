import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Rectangle has set of x coordinates and y coordinates of triangle's vertex as integer tables (rect[0] = x , rect[1] = y) and color
 * @see MyShapes extedns
 */
public class Triangle implements MyShapes{
    private Color color;
    private final int[][] points = new int[2][3];//triangle[0] = X; triangle[1] = Y;

    /**
     * Constructor of triangle
     * @param x set of x coordinates of triangle's vertex
     * @param y set of y coordinates of triangle's vertex
     * @param color of triangle
     */
    public Triangle(int[] x, int[] y, Color color){
        points[0]=x;
        points[1]=y;
        this.color=color;
    }
    /**
     * @return set of x coordinates of triangle's vertex
     */
    public int[] getX(){
        return points[0];
    }

    /**
     * @return set of y coordinates of triangle's vertex
     */
    public int[] getY(){
        return points[1];
    }

    /**
     * @param point index in triangle
     * @return x coordinates of zero's, first or second triangle's vertex
     */
    public int getX(int point){
        return points[0][point];
    }

    /**
     * @param point index in triangle
     * @return y coordinates of zero's, first or second point
     */
    public int getY(int point){
        return points[1][point];
    }

    /**
     * updates coordinates of second point and of triangle
     * @param x coordinate
     * @param y coordinate
     */
    public void setSecond(int x, int y) {
        points[0][1]=x;
        points[1][1]=y;
    }

    /**
     * updates coordinates of third point and of triangle
     * @param x coordinate
     * @param y coordinate
     */
    public void setThird(int x, int y) {
        points[0][2]=x;
        points[1][2]=y;
    }
    /**
     * @param newColor updates color of triangle
     */
    @Override
    public void setColor(Color newColor) {
        color=newColor;
    }

    /**
     * @return color of triangle
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * @param mE point to be checked
     * @return if point is inside triangle
     */
    @Override
    public boolean pointIn(MouseEvent mE) {
        Polygon p = new Polygon(getX(),getY(),3);
        return p.contains(mE.getX(),mE.getY());
    }

    /**
     * Moves triangle by vector point>mE
     * @param point form which triangle moved
     * @param mE point to which triangle moved
     */
    @Override
    public void move(int[] point, MouseEvent mE) {
        points[0][0]+=mE.getX()-point[0];
        points[0][1]+=mE.getX()-point[0];
        points[0][2]+=mE.getX()-point[0];
        points[1][0]+=mE.getY()-point[1];
        points[1][1]+=mE.getY()-point[1];
        points[1][2]+=mE.getY()-point[1];
    }

    /**
     * @param factor by which triangle resized
     */
    @Override
    public void resize(double factor) {
        points[0][1]+= (int) ((points[0][0]-points[0][1])*(1-factor));
        points[0][2]+= (int) ((points[0][0]-points[0][2])*(1-factor));
        points[1][1]+= (int) ((points[1][0]-points[1][1])*(1-factor));
        points[1][2]+= (int) ((points[1][0]-points[1][2])*(1-factor));
    }

    /**
     * @return triangle parameters
     */
    @Override
    public String toFile() {
        return "T\t"+
                getX(0)+"\t"+getX(1)+"\t"+getX(2)+"\t"+
                getY(0)+"\t"+getY(1)+"\t"+getY(2)+"\t"+
                getColor().getRed()+"\t"+getColor().getGreen()+"\t"+getColor().getBlue()+"\n";
    }
}