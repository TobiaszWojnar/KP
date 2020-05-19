public interface PainterVisitor {
    void paint (Circle c);
    void paint (MyRectangle r);
    void paint (Triangle t);
}
