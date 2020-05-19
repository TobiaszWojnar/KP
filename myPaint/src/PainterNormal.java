import java.awt.*;

public class PainterNormal implements PainterVisitor{
    Graphics2D g2d;
    PainterNormal (Graphics2D g2d){
        this.g2d = g2d;
    }
    @Override
    public void paint(Circle c) {
        g2d.fillOval((int) (c.getCenter()[0] - c.getRadius()),
                (int) (c.getCenter()[1] - c.getRadius()),
                2 * (int) c.getRadius(),
                2 * (int) c.getRadius());
    }

    @Override
    public void paint(MyRectangle r) {
        g2d.fillRect(
                Math.min(r.getRect()[0][0], r.getRect()[1][0]),
                Math.min(r.getRect()[0][1], r.getRect()[1][1]),
                r.getRect()[2][0],
                r.getRect()[2][1]);
    }

    @Override
    public void paint(Triangle t) {
        g2d.fillPolygon(t.getX(), t.getY(), 3);
    }
}