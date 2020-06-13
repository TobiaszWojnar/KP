import java.awt.*;

/**
 * Rectangle has parameters:
 *      * first and second Point
 *      * height and wight
 * @see MyShape extedns
 */
public class MyRectangle implements MyShape {
    private Color color;
    private final Point first;
    private Point second;
    private int height;
    private int weight;
    /**
     * Constructor of rectangle
     * @param firstPoint point
     * @param secondPoint point
     * @param color of rectangle
     * constructor calculates height and weight
     */
    MyRectangle(Point firstPoint, Point secondPoint, Color color) {
        this.color=color;
        this.first=firstPoint;
        second=secondPoint;
        setDimension();
    }

    /**
     * updates coordinates of second point and recalculates dimensions of rectangle
     * @param x coordinate
     * @param y coordinate
     */
    public void setSecond (int x, int y){
        second=new Point(x,y);
        setDimension();
    }

    /**
     * @return height of rectangle
     */
    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }
    /**
     * updates dimensions of rectangle
     */
    public void setDimension(){
        weight=Math.abs(first.x-second.x);
        height=Math.abs(first.y-second.y);
    }

    /**
     * @return upper left vertex of rectangle
     */
    public Point getUpperLeft(){
        return new Point(Math.min(first.x,second.x),Math.min(first.y,second.y));
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
     * @param x,y to be checked
     * @return if point is inside rectangle
     */
    @Override
    public boolean pointIn(int x, int y) {
        if(x>= getUpperLeft().x&&x<= getUpperLeft().y+weight){
            return y >= getUpperLeft().x && y <= getUpperLeft().y+height;
        }
        return false;
    }
    /**
     * Moves rectangle by vector point>mE
     * @param xDistance point form which rectangle moved
     * @param yDistance point to which rectangle moved
     */
    @Override
    public void move(int xDistance, int yDistance) {
        first.x+=xDistance;
        first.y+=yDistance;
        second.x+=xDistance;
        second.y+=yDistance;
    }

    /**
     * @param factor by which rectangle dimension is resized
     */
    @Override
    public void resize(double factor) {
        second.x+= (first.x-second.x)*(1-factor);
        second.y+= (first.y-second.y)*(1-factor);
        setDimension();
    }
    /**
     * Method enabling saving to document
     * @return Parameters of figure concatenated into string.
     * Format for returning:
     *      * Upper case letter for determining what type of figure is it ('R')
     *      * x,y coordinates of first point, x,y coordinate of second point
     *      * Color as 3 integers for RGB (0-255)
     */
    @Override
    public String toFile() {
        return "R\t" +
                first.x+"\t"+first.y+"\t"+
                second.x+"\t"+second.y+"\t"+
                getColor().getRed()+"\t"+getColor().getGreen()+"\t"+getColor().getBlue()+"\n";
    }

    /**
     * @param visitor painter visitor
     */
    @Override
    public void accept(PainterVisitor visitor) {
        visitor.paint(this);
    }
}
