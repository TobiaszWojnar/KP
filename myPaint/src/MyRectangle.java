import java.awt.*;

/**
 * Rectangle has parameters as integer table (rect[0] = first point, rect[1] = second point, rect[2] = wight & height) and color
 * @see MyShape extedns
 */
public class MyRectangle implements MyShape {
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
     * @param point to be checked
     * @return if point is inside rectangle
     */
    @Override
    public boolean pointIn(int[] point) {
        int[] upperLeft = new int[] {Math.min(rect[0][0],rect[1][0]),Math.min(rect[0][1],rect[1][1])};
        int[] lowerRight = new int[] {Math.max(rect[0][0],rect[1][0]),Math.max(rect[0][1],rect[1][1])};
        if(point[0]>=upperLeft[0]&&point[0]<=lowerRight[0]){
            return point[1] >= upperLeft[1] && point[1] <= lowerRight[1];
        }
        return false;
    }

    /**
     * Moves rectangle by vector point>mE
     * @param initialPoint point form which rectangle moved
     * @param finalPoint point to which rectangle moved
     */
    @Override
    public void move(int[] initialPoint, int[] finalPoint) {
        rect[0][0]+=finalPoint[0]-initialPoint[0];
        rect[0][1]+=finalPoint[1]-initialPoint[1];
        rect[1][0]+=finalPoint[0]-initialPoint[0];
        rect[1][1]+=finalPoint[1]-initialPoint[1];
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
        return "R\t" +
                getRect()[0][0]+"\t"+getRect()[0][1]+"\t"+
                getRect()[1][0]+"\t"+getRect()[1][1]+"\t"+
                getColor().getRed()+"\t"+getColor().getGreen()+"\t"+getColor().getBlue()+"\n";
    }

    @Override
    public void accept(PainterVisitor visitor) {
        visitor.paint(this);
    }
}
