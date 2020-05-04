import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Rectangle has parameters as integer table (rect[0] = first point, rect[1] = second point, rect[2] = wight & height) and color
 * @see MyShapes extedns
 */
public class MyRectangle extends MyShapes{
    private Color color;
    private final int[][] rect = new int[3][2];//rect[0] = first point; rect[1] = second point; rect[2] = wight & height

    /**
     * Constructor of rectangle
     * @param first point
     * @param second point
     * @param color  of rectangle
     */
    MyRectangle(int[] first, int[] second, Color color) {
        this.color=color;
        rect[0]=first;
        rect[1]=second;
        setDimension();
    }

    /**
     * updates coordinates of second point and recalculates dimensions of rectangle
     * @param x coordinate
     * @param y coordinate
     */
    public void setSecond (int x, int y){
        rect[1][0]=x;
        rect[1][1]=y;
        setDimension();
    }

    /**
     * updates dimensions of rectangle
     */
    public void setDimension(){
        rect[2][0]=Math.abs(rect[0][0]-rect[1][0]);
        rect[2][1]=Math.abs(rect[0][1]-rect[1][1]);
    }

    /**
     * @return points and dimensions of rectangle
     */
    public int[][] getRect(){
        return rect;
    }

    /**
     * @param newColor updates color of rectangle
     */
    @Override
    public void setColor(Color newColor) {
        color=newColor;
    }

    /**
     * @return color of rectangle
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * @param mE point to be checked
     * @return if point is inside rectangle
     */
    @Override
    public boolean pointIn(MouseEvent mE) {
        int[] upperLeft = new int[] {Math.min(rect[0][0],rect[1][0]),Math.min(rect[0][1],rect[1][1])};//TODO pole upper left?
        int[] lowerRight = new int[] {Math.max(rect[0][0],rect[1][0]),Math.max(rect[0][1],rect[1][1])};
        if(mE.getX()>=upperLeft[0]&&mE.getX()<=lowerRight[0]){
            return mE.getY() >= upperLeft[1] && mE.getY() <= lowerRight[1];
        }
        return false;
    }

    /**
     * Moves rectangle by vector point>mE
     * @param point form which  rectangle moved
     * @param mE point to which rectangle moved
     */
    @Override
    public void move(int[] point, MouseEvent mE) {
        rect[0][0]+=mE.getX()-point[0];
        rect[0][1]+=mE.getY()-point[1];
        rect[1][0]+=mE.getX()-point[0];
        rect[1][1]+=mE.getY()-point[1];
    }

    /**
     *
     * @param factor by which rectangle dimension is resized
     */
    @Override
    public void resize(double factor) {
        rect[1][0]+= (rect[0][0]-rect[1][0])*(1-factor);
        rect[1][1]+= (rect[0][1]-rect[1][1])*(1-factor);
        setDimension();
    }
    /**
     * @return Rectangle parameters
     */
    @Override
    public String toFile() {
        return "R\t"+getRect()[0][0]+"\t"+getRect()[0][1]+"\t"+getRect()[1][0]+"\t"+getRect()[1][1]+"\t"+getColor().getRed()+"\t"+getColor().getGreen()+"\t"+getColor().getBlue()+"\n";
    }

}
