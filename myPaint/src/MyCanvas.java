import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * MyCanvas class extends Canvas and implements listeners for mouse movement
 * Contains all shapes and state of program
 */
public class MyCanvas extends Canvas  implements MouseListener, MouseMotionListener, MouseWheelListener {
    private ArrayList<MyShape> shapes;
    private Color activeColor = Color.BLACK;
    enum States {c,r,t,e,no}
    States appState;
    private int painting;
    private int activeShape;
    private boolean moving;
    private Point movingPoint;
    private final Point movingVector;
    private final double rescaleFactor;
    private Listener listener;

    /**
     * Constructor of canvas
     * adds listeners for mouse movement
     * Parameters:
     * appState 'c' >   creating circle
     *          'r' >   creating rectangle
     *          't' >   creating triangle
     *          'no' >   no action
     *          'e' > editing active figure
     * shapes   - list of all shapes
     * activeShape - index of active shape
     * painting: equals '0' when no new points have been painted
     *           equals '1' when 1 point of new figure has been painted
     *           equals '2' when 1 point of new figure has been painted (only for triangle)
     * moving   - equals true if moving active figure
     * movingPoint - coordinates of point from which we move figure
     */
    public MyCanvas(){
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        appState = States.no;
        shapes = new ArrayList<>();
        activeShape=0;
        painting = 0;
        moving = false;
        movingPoint=new Point();
        movingVector=new Point();
        rescaleFactor=.95;
        paintSth();
    }
    /**
     * Paints all figures from shapes
     * Iterates for all items in shapes
     * For active figure uses paintActive, for rest  paintNormal
     * @param g paints on it
     * @see PainterActive
     * @see PainterNormal
     * @see MyShape
     * @see PainterVisitor
     */
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        PainterNormal paintNormal = new PainterNormal(g2d);
        PainterActive paintActive = new PainterActive(g2d);
        PainterActive paintMoving = new PainterActive(g2d,movingVector);
        for(int i=0; i<shapes.size();i++){
            MyShape s=shapes.get(i);
            g2d.setColor(s.getColor());
            if(i==activeShape){
                if(moving){
                    s.accept(paintMoving);
                }else {
                    s.accept(paintActive);
                }
            }else {
                s.accept(paintNormal);
            }
        }
    }

    /**
     * @param mE Point where mouse is
     * @return index of figure in which we clicked or -1 if no such figure existed
     * @see MyShape
     */
    private int pointInShape(MouseEvent mE){
        for (int i=shapes.size()-1;i>=0;i--){
            if(shapes.get(i).pointIn(mE.getX(),mE.getY()))
                return i;
        }
        return -1;
    }

    /**
     * @return list of shapes
     * @see MyShape
     */
    public ArrayList<MyShape> getShapes() { return shapes; }

    /**
     * adds list of new shapes to existing list
     * @param newShapes list of MyShapes
     * @see MyShape
     */
    public void addShapes(ArrayList<MyShape> newShapes) {
        shapes.addAll(newShapes);
    }

    /**
     * clears list of shapes
     * @see MyShape
     */
    public void clearShapes() {
        shapes.clear();
    }

    /**
     * @param shapes sets shapes as this shapes
     */
    public void setShapes(ArrayList<MyShape> shapes) {
        this.shapes = shapes;
    }
    /**
     * sets state of program
     * @param newAppState one of 5 states
     * 'c' >    creating circle
     * 'r' >    creating rectangle
     * 't' >    creating triangle
     * '0' >    no action
     * 'e' >    editing active figure
     * @see MyCanvas.States
     */
    public void setAppState(States newAppState) {
        if(painting!=0) {
            shapes.remove(shapes.size() - 1);
            painting=0;
        }
        appState = newAppState;
        if(appState!=States.e)
            activeShape =-1;
        repaint();
    }

    /**
     * @return active color
     */
    public Color getColor(){
        return activeColor;
    }

    /**
     * @param activeColor sets active color
     */
    public void setColor(Color activeColor) {
        this.activeColor=activeColor;
    }

    /**
     * @param listener sets listener
     */
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * listener for mouse being clicked
     * if crating figures
     *      left clicks adds points
     *      right click stops action
     * right click if inside of figure sets it's as active figure and a color menu will open
     * if in editing active figure
     *      by left clicking inside active shape might be in moving state
     *      right click stops action
     * if in moving active figure
     *      by left clicking you stop moving it
     * @param mE MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent mE) {
        if(SwingUtilities.isLeftMouseButton(mE)){
            switch (appState){
                case c:
                    if(painting==0) {
                        shapes.add(new Circle(mE.getPoint(), 0, activeColor));
                        activeShape = shapes.size()-1;
                        painting = 1;
                    }else{
                        Circle c = (Circle) shapes.get(activeShape);
                        double radius = c.getCenter().distance(mE.getPoint());
                        c.setRadius(radius);
                        painting = 0;
                        appState = States.e;
                        listener.onShapeFinished();
                    }
                    break;
                case r:
                    if(painting==0) {
                        shapes.add(new MyRectangle(mE.getPoint(), mE.getPoint(), activeColor));
                        activeShape = shapes.size()-1;
                        painting = 1;
                    }else{
                        MyRectangle r = (MyRectangle) shapes.get(activeShape);
                        r.setSecond(mE.getX(), mE.getY());
                        painting = 0;
                        appState = States.e;
                        listener.onShapeFinished();
                    }
                    break;
                case t:
                    int[][] points = new int[2][3];
                    Triangle t;
                    switch (painting){
                        case 0:
                            points[0]=new int[] {mE.getX(),mE.getX(),mE.getX()};
                            points[1]=new int[] {mE.getY(),mE.getY(),mE.getY()};
                            shapes.add(new Triangle(points[0], points[1], activeColor));
                            activeShape = shapes.size()-1;
                            painting = 1;
                            break;
                        case 1:
                            t = (Triangle) shapes.get(activeShape);
                            t.setSecond(mE.getX(), mE.getY());
                            painting = 2;
                            break;
                        case 2:
                            t = (Triangle) shapes.get(activeShape);
                            t.setThird(mE.getX(), mE.getY());
                            painting = 0;
                            appState = States.e;
                            listener.onShapeFinished();
                            break;
                        default:
                            break;
                    }
                    break;
                case e:
                    if(moving) {
                        shapes.get(activeShape).move(mE.getX()-movingPoint.x,mE.getY()-movingPoint.y);
                        movingPoint.setLocation(0,0);
                        moving = false;
                    }else{
                        if (shapes.get(activeShape).pointIn(mE.getX(),mE.getY())) {
                            moving=true;
                            movingPoint = mE.getPoint();
                            movingVector.setLocation(0,0);
                        }
                        if(pointInShape(mE)!=-1)
                            activeShape = pointInShape(mE);
                    }
                    break;
                default:
                    activeShape = pointInShape(mE);
                    if(activeShape!=-1) {
                        setAppState(States.e);
                    }
                    break;
            }
        }else if (SwingUtilities.isRightMouseButton(mE)){
            if(painting!=0) {
                shapes.remove(shapes.size() - 1);
                repaint();
                painting=0;
                listener.onShapeFinished();
            }
            if(moving){
                repaint();
                moving = false;
                movingPoint.setLocation(0,0);
            }else {
                activeShape = pointInShape(mE);
                if (activeShape == -1) {
                    setAppState(States.no);
                } else {
                    Color temp = JColorChooser.showDialog(this, "", shapes.get(activeShape).getColor());
                    if (temp != null) {
                        shapes.get(activeShape).setColor(temp);
                        listener.onColorChanged(temp);
                    }
                    setAppState(States.e);
                }
            }
        }
        repaint();
    }

    /**
     * if creating figure will update it's shape
     * in moving active figure will update it's coordinates
     * @param mE coordinates
     */
    @Override
    public void mouseMoved(MouseEvent mE) {
        if(painting>0){
            switch (appState) {
                case c:
                    Circle c = (Circle) shapes.get(shapes.size() - 1);
                    double radius = c.getCenter().distance(mE.getPoint());
                    c.setRadius(radius);
                    break;
                case r:
                    MyRectangle r = (MyRectangle) shapes.get(shapes.size() - 1);
                    r.setSecond(mE.getX(), mE.getY());
                    break;
                case t:
                    Triangle t;
                    if(painting==1){
                        t = (Triangle) shapes.get(activeShape);
                        t.setSecond(mE.getX(), mE.getY());
                    }else if(painting==2){
                        t = (Triangle) shapes.get(activeShape);
                        t.setThird(mE.getX(), mE.getY());
                    }
                    break;
                default:
                    break;
            }
            repaint();
        }else{
            if(appState==States.e&&moving){
                movingVector.setLocation(mE.getX()-movingPoint.x,mE.getY()-movingPoint.y);
                repaint();
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) { }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) { }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) { }

    @Override
    public void mouseExited(MouseEvent mouseEvent) { }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) { }

    /**
     * if in editing state enables rescaling active figure
     * @param mWE wheel movement
     */
    @Override
    public void mouseWheelMoved(MouseWheelEvent mWE) {
        double factor=rescaleFactor;
        if(appState==States.e) {
            if (mWE.getWheelRotation() < 0)
                factor = 1 / factor;
            shapes.get(activeShape).resize(factor);
            repaint();
        }
    }

    /**
     * method for testing loads starting shapes
     */
    public void paintSth (){
        Circle c;
        activeShape=-1;
        for(int j=0;j<4;j++) {
            for (int i = 0; i < 8; i++) {
                c = new Circle(new Point(40 + i * 80, 60+j*100), 25, new Color(i * 10+j*30, i * 20+j*20, i * 30+j*10));
                shapes.add(c);
            }
        }
    }

    /**
     * Listener enabling to change menu state
     * @see MyMenu
     */
    public interface Listener {
        void onShapeFinished ();
        void onColorChanged(Color color);
    }
}