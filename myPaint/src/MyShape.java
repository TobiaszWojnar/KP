import java.awt.Color;
/**
 * abstract class enabling having one array of all shapes
 * is extended by
 * @see Circle
 * @see MyRectangle
 * @see Triangle
 */
public interface MyShape {
    void setColor (Color newColor);
    Color getColor ();
    boolean pointIn(int[] point);
    void move (int[] initialPoint, int[] finalPoint);
    void resize (double factor);
    String toFile();
    void accept(PainterVisitor visitor);
}
