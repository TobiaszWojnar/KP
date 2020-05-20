import java.awt.*;
/**
 * Class for painting active figure
 *
 * using Visitor Pattern
 * implements:
 * @see PainterVisitor
 */
public class PainterNormal implements PainterVisitor{
    Graphics2D g2d;
    /**
     * Constructor of painter visitor for regular figure (not active)
     * @param g2d paints on it
     */
    PainterNormal (Graphics2D g2d){
        this.g2d = g2d;
    }
    /**
     * paints Circle
     * @param c figure to paint
     * @see Circle
     */
    @Override
    public void paint(Circle c) {
        g2d.fillOval((int) (c.getCenter().x - c.getRadius()),
                (int) (c.getCenter().y - c.getRadius()),
                2 * (int) c.getRadius(),
                2 * (int) c.getRadius());
    }
    /**
     * paints rectangles
     * @param r figure to paint
     * @see MyRectangle
     */
    @Override
    public void paint(MyRectangle r) {
        g2d.fillRect(
                r.getUpperLeft().x,
                r.getUpperLeft().y,
                r.getWeight(),
                r.getHeight());
    }
    /**
     * paints Triangle
     * @param t figure to paint
     * @see Triangle
     */
    @Override
    public void paint(Triangle t) {
        g2d.fillPolygon(t.getX(), t.getY(), 3);
    }
}