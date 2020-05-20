/**
 * Interface for painter visitors
 * using Visitor Pattern
 * implemented by:
 * @see PainterActive
 * @see PainterNormal
 */
public interface PainterVisitor {
    /**
     * paints Circle
     * @param c figure to paint
     * @see Circle
     */
    void paint (Circle c);
    /**
     * paints rectangles
     * @param r figure to paint
     * @see MyRectangle
     */
    void paint (MyRectangle r);
    /**
     * paints Triangle
     * @param t figure to paint
     * @see Triangle
     */
    void paint (Triangle t);
}
