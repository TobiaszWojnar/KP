import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * abstract class enabling having one array of all shapes
 * is extended by
 * @see Circle
 * @see MyRectangle
 * @see Triangle
 */
public interface MyShapes {
    public abstract void setColor (Color newColor);
    public abstract Color getColor ();
    public abstract boolean pointIn(MouseEvent mE);
    public abstract void move (int[] point, MouseEvent mE);
    public abstract void resize (double factor);
    public abstract String toFile();
}
