import java.awt.*;

public class PainterMoving implements PainterVisitor{
    Graphics2D g2d;
    int[] movingVector;
    PainterMoving (Graphics2D g2d,int[] movingVector){
        this.g2d = g2d;
        this.movingVector=movingVector;
    }
    @Override
    public void paint(Circle c) {
        g2d.setColor(oppositeColor(c.getColor()));
        g2d.drawOval(
                (int) (c.getCenter()[0] - c.getRadius()) + movingVector[0]-1,
                (int) (c.getCenter()[1] - c.getRadius()) + movingVector[1]-1,
                (int) (2 * c.getRadius())+2,
                (int) (2 * c.getRadius())+2);

        g2d.setColor(c.getColor());
        g2d.fillOval(
                (int) (c.getCenter()[0] - c.getRadius()) + movingVector[0],
                (int) (c.getCenter()[1] - c.getRadius()) + movingVector[1],
                (int) (2 * c.getRadius()),
                (int) (2 * c.getRadius()));
    }

    @Override
    public void paint(MyRectangle r) {
        g2d.setColor(oppositeColor(r.getColor()));
        g2d.drawRect(
                Math.min(r.getRect()[0][0], r.getRect()[1][0]) + movingVector[0]-1,
                Math.min(r.getRect()[0][1], r.getRect()[1][1]) + movingVector[1]-1,
                r.getRect()[2][0]+1,
                r.getRect()[2][1]+1);

        g2d.setColor(r.getColor());
        g2d.fillRect(
                Math.min(r.getRect()[0][0], r.getRect()[1][0]) + movingVector[0]+1,
                Math.min(r.getRect()[0][1], r.getRect()[1][1]) + movingVector[1]+1,
                r.getRect()[2][0]-1,
                r.getRect()[2][1]-1);
    }

    @Override
    public void paint(Triangle t) {
        g2d.setColor(oppositeColor(t.getColor()));
        g2d.drawPolygon(
                new int[]{t.getX(0) + movingVector[0], t.getX(1) + movingVector[0], t.getX(2) + movingVector[0]},
                new int[]{t.getY(0) + movingVector[1], t.getY(1) + movingVector[1], t.getY(2) + movingVector[1]},
                3);

        g2d.setColor(t.getColor());
        g2d.fillPolygon(
                new int[]{t.getX(0) + movingVector[0], t.getX(1) + movingVector[0], t.getX(2) + movingVector[0]},
                new int[]{t.getY(0) + movingVector[1], t.getY(1) + movingVector[1], t.getY(2) + movingVector[1]},
                3);
    }
    private Color oppositeColor(Color color){
        if(color==Color.BLACK){
            return Color.YELLOW;
        }
        return new Color(255-color.getRed(),255-color.getGreen(),255-color.getGreen());
    }
}
/*
    private void paintMoving(Graphics2D g2d){
        MyShape s = shapes.get(activeShape);
        g2d.setColor(oppositeColor(s.getColor()));
        if (s instanceof Circle) {
            Circle c = (Circle) s;
            g2d.drawOval((int) (c.getCenter()[0] - c.getRadius()) + movingVector[0]-1,
                    (int) (c.getCenter()[1] - c.getRadius()) + movingVector[1]-1,
                    (int) (2 * c.getRadius())+2,
                    (int) (2 * c.getRadius())+2);

            g2d.setColor(s.getColor());
            g2d.fillOval((int) (c.getCenter()[0] - c.getRadius()) + movingVector[0],
                    (int) (c.getCenter()[1] - c.getRadius()) + movingVector[1],
                    (int) (2 * c.getRadius()),
                    (int) (2 * c.getRadius()));

        } else if (s instanceof MyRectangle) {
            MyRectangle r = (MyRectangle) s;
            g2d.drawRect(
                    Math.min(r.getRect()[0][0], r.getRect()[1][0]) + movingVector[0]-1,
                    Math.min(r.getRect()[0][1], r.getRect()[1][1]) + movingVector[1]-1,
                    r.getRect()[2][0]+1,
                    r.getRect()[2][1]+1);

            g2d.setColor(s.getColor());
            g2d.fillRect(
                    Math.min(r.getRect()[0][0], r.getRect()[1][0]) + movingVector[0]+1,
                    Math.min(r.getRect()[0][1], r.getRect()[1][1]) + movingVector[1]+1,
                    r.getRect()[2][0]-1,
                    r.getRect()[2][1]-1);

        } else if (s instanceof Triangle) {
            Triangle t = (Triangle) s;
            g2d.drawPolygon(
                    new int[]{t.getX(0) + movingVector[0], t.getX(1) + movingVector[0], t.getX(2) + movingVector[0]},
                    new int[]{t.getY(0) + movingVector[1], t.getY(1) + movingVector[1], t.getY(2) + movingVector[1]},
                    3);

            g2d.setColor(s.getColor());
            if (painting == 1) {
                g2d.drawLine(t.getX(0), t.getY(0), t.getX(1), t.getY(1));
            }
            g2d.fillPolygon(
                    new int[]{t.getX(0) + movingVector[0], t.getX(1) + movingVector[0], t.getX(2) + movingVector[0]},
                    new int[]{t.getY(0) + movingVector[1], t.getY(1) + movingVector[1], t.getY(2) + movingVector[1]},
                    3);
        }

 */