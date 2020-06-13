import java.awt.*;
/**
 * Class for painting active figure
 * surrounds it by line in opposite color
 * using Visitor Pattern
 * implements:
 * @see PainterVisitor
 */
public class PainterActive implements PainterVisitor{
    Graphics2D g2d;
    Point movingVector;

    /**
     * Constructor of painter visitor for painting active figure
     * @param g2d paints on
     * @param movingVector offsets where to paint figure
     */
    PainterActive(Graphics2D g2d, Point movingVector){
        this.g2d = g2d;
        this.movingVector=movingVector;
    }
    /**
     * Constructor of painter visitor for painting active figure
     * @param g2d paints on
     */
    PainterActive(Graphics2D g2d){
        this(g2d,new Point(0,0));
    }

    /**
     * paints circle and surrounds it by line in opposite color
     * paints figure moved by movingVector
     * @param c figure to paint
     * @see Circle
     */
    @Override
    public void paint(Circle c) {
        g2d.setColor(oppositeColor(c.getColor()));
        g2d.drawOval(
                (int) (c.getCenter().x - c.getRadius()) + movingVector.x-1,
                (int) (c.getCenter().y - c.getRadius()) + movingVector.y-1,
                (int) (2 * c.getRadius())+2,
                (int) (2 * c.getRadius())+2);

        g2d.setColor(c.getColor());
        g2d.fillOval(
                (int) (c.getCenter().x - c.getRadius()) + movingVector.x,
                (int) (c.getCenter().y - c.getRadius()) + movingVector.y,
                (int) (2 * c.getRadius()),
                (int) (2 * c.getRadius()));
    }
    /**
     * paints rectangles and surrounds it by line in opposite color
     * paints figure moved by movingVector
     * @param r figure to paint
     * @see MyRectangle
     */
    @Override
    public void paint(MyRectangle r) {
        g2d.setColor(oppositeColor(r.getColor()));
        g2d.drawRect(
                r.getUpperLeft().x + movingVector.x-1,
                r.getUpperLeft().y + movingVector.y-1,
                r.getWeight()+1,
                r.getHeight()+1);

        g2d.setColor(r.getColor());
        g2d.fillRect(
                r.getUpperLeft().x + movingVector.x+1,
                r.getUpperLeft().y + movingVector.y+1,
                r.getWeight()-1,
                r.getHeight()-1);
    }
    /**
     * paints triangle and surrounds it by line in opposite color
     * paints figure moved by movingVector
     * @param t figure to paint
     * @see Triangle
     */
    @Override
    public void paint(Triangle t) {
        g2d.setColor(oppositeColor(t.getColor()));
        g2d.drawPolygon(
                new int[]{t.getX(0) + movingVector.x, t.getX(1) + movingVector.x, t.getX(2) + movingVector.x},
                new int[]{t.getY(0) + movingVector.y, t.getY(1) + movingVector.y, t.getY(2) + movingVector.y},
                3);

        g2d.setColor(t.getColor());
        g2d.fillPolygon(
                new int[]{t.getX(0) + movingVector.x, t.getX(1) + movingVector.x, t.getX(2) + movingVector.x},
                new int[]{t.getY(0) + movingVector.y, t.getY(1) + movingVector.y, t.getY(2) + movingVector.y},
                3);
    }

    /**
     * @param color basic color
     * @return opposite color to basic color. If basic color is black returns yellow in order to be more visible on white background
     */
    private Color oppositeColor(Color color){
        if(color==Color.BLACK){
            return Color.YELLOW;
        }
        return new Color(255-color.getRed(),255-color.getGreen(),255-color.getGreen());
    }
}