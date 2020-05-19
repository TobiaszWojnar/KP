import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Menu class extends Canvas and implements listeners for mouse movement
 * Contains all shapes and state of program
 */
public class MyCanvas extends Canvas  implements MouseListener, MouseMotionListener, MouseWheelListener {

    private ArrayList<MyShape> shapes;
    private Color activeColor = Color.BLACK;
    private char appState;
    private int painting;
    private int activeShape;
    private boolean moving;
    private int[] movingPoint;
    private int[] movingVector;
    double rescaleFactor;
    private Listener listener;
    PainterNormal paintNormal;
    PainterActive paintActive;
    PainterMoving paintMoving;

    /**
     * Constructor of canvas
     * adds listeners for mouse movement
     * Parameters:
     * appState 'c' >   creating circle
     *          'r' >   creating rectangle
     *          't' >   creating triangle
     *          '0' >   no action
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

        appState = '0';
        shapes = new ArrayList<>();
        activeShape=0;
        painting = 0;
        moving = false;
        movingPoint=new int[2];
        movingVector=new int[2];
        rescaleFactor=.95;
        paintShit();
        //paintSth();
    }

    /**
     * Paints all figures from shapes
     */
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        paintNormal = new PainterNormal(g2d);
        paintActive = new PainterActive(g2d);
        paintMoving = new PainterMoving(g2d,movingVector);

        for(MyShape s:shapes){
            g2d.setColor(s.getColor());
            if(shapes.indexOf(s)==activeShape){
                if(moving){
                    s.accept(paintMoving);
                }else {
                    s.accept(paintActive);
                }
            }else {
                s.accept(paintNormal);//https://www.geeksforgeeks.org/visitor-design-pattern/ //TODO
            }
        }
    }

    /**
     * @return index of figure in which we clicked or -1 if no such figure existed
     */
    private int pointInShape(MouseEvent mE){
        for (int i=shapes.size()-1;i>=0;i--){
            if(shapes.get(i).pointIn(new int[]{mE.getX(),mE.getY()}))
                return i;
        }
        return -1;
    }

    public ArrayList<MyShape> getShapes() {
        return shapes;
    }

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
     */
    public void setAppState(char newAppState) {
        if(painting!=0) {
            shapes.remove(shapes.size() - 1);
            painting=0;
        }
        appState = newAppState;
        if(appState!='e')
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

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * listener for mouse clicked
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
                case 'c':
                    if(painting==0) {
                        shapes.add(new Circle(new int[]{mE.getX(), mE.getY()}, 0, activeColor));
                        activeShape = shapes.size()-1;
                        painting = 1;
                    }else{
                        Circle c = (Circle) shapes.get(activeShape);
                        double radius = Math.sqrt((mE.getY() - c.getCenter()[1]) * (mE.getY() - c.getCenter()[1]) + (mE.getX() - c.getCenter()[0]) * (mE.getX() - c.getCenter()[0]));
                        c.setRadius(radius);
                        painting = 0;
                        appState = 'e';
                        listener.onShapeFinished();
                    }
                    break;
                case 'r':
                    if(painting==0) {
                        shapes.add(new MyRectangle(new int[]{mE.getX(), mE.getY()}, new int[]{mE.getX(), mE.getY()}, activeColor));
                        activeShape = shapes.size()-1;
                        painting = 1;
                    }else{
                        MyRectangle r = (MyRectangle) shapes.get(activeShape);
                        r.setSecond(mE.getX(), mE.getY());
                        painting = 0;
                        appState = 'e';
                        listener.onShapeFinished();
                    }
                    break;
                case 't':
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
                            appState = 'e';
                            listener.onShapeFinished();
                            break;
                        default:
                            break;
                    }
                    break;
                case 'e':
                    if(moving) {
                        shapes.get(activeShape).move(movingPoint, new int[]{mE.getX(),mE.getY()});
                        moving = false;
                    }else{
                        if (shapes.get(activeShape).pointIn(new int[]{mE.getX(),mE.getY()})) {
                            moving=true;
                            movingPoint = new int[]{mE.getX(),mE.getY()};
                            movingVector = new int[]{0,0};
                        }
                        if(pointInShape(mE)!=-1)
                            activeShape = pointInShape(mE);
                    }
                    break;
                default:
                    activeShape = pointInShape(mE);
                    if(activeShape!=-1) {
                        System.out.println("little fucker; "+ activeShape+"\n"+moving+"\n"+movingVector[0]+"\n"+movingPoint[0]);
                        setAppState('e');
                    }
                    break;
            }
        }else if (SwingUtilities.isRightMouseButton(mE)){
            if(painting!=0) {
                shapes.remove(shapes.size() - 1);
                painting=0;
                listener.onShapeFinished();
            }
            if(moving){
                System.out.println("need to decide what to do");//TODO if(moving)
            }
            activeShape = pointInShape(mE);
            if(activeShape==-1) {
                setAppState('0');
            } else{
                Color temp=JColorChooser.showDialog(this, "", shapes.get(activeShape).getColor());
                if(temp!=null) {
                    shapes.get(activeShape).setColor(temp);
                    listener.onColorChanged(temp);
                }
                setAppState('e');
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
                case 'c':
                    Circle c = (Circle) shapes.get(shapes.size() - 1);
                    double radius = Math.sqrt((mE.getY() - c.getCenter()[1]) * (mE.getY() - c.getCenter()[1]) + (mE.getX() - c.getCenter()[0]) * (mE.getX() - c.getCenter()[0]));
                    c.setRadius(radius);
                    break;
                case 'r':
                    MyRectangle r = (MyRectangle) shapes.get(shapes.size() - 1);
                    r.setSecond(mE.getX(), mE.getY());
                    break;
                case 't':
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
            if(appState=='e'&&moving){
                movingVector=new int[]{mE.getX()-movingPoint[0],mE.getY()-movingPoint[1]};
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
        if(appState=='e') {
            if (mWE.getWheelRotation() < 0)
                factor = 1 / factor;
            shapes.get(activeShape).resize(factor);
            repaint();
        }
    }
    /*
    public void paintSth (){
        Circle c1 = new Circle(new int[] {12,14},40,Color.CYAN);
        Circle c2 = new Circle(new int[] {200,214},30,Color.RED);
        shapes.add(c1);
        shapes.add(c2);
        MyRectangle r1 = new MyRectangle(new int[] {20,40},new int[] {40,60},Color.YELLOW);
        shapes.add(r1);
        Triangle t1 = new Triangle(new int[] {64,64,128}, new int[] {64,128,128}, Color.MAGENTA);
        shapes.add(t1);
        activeShape=+4;
    }
*/
    public void paintShit (){
        Circle c;
        activeShape=-1;
        for(int j=0;j<5;j++) {
            for (int i = 0; i < 20; i++) {
                c = new Circle(new int[]{30 + i * 40, 50+j*60}, 20, new Color(i * 5+j*10, i * 10+j*10, i * 10+j*10));
                shapes.add(c);
            }
        }


    }
    public interface Listener {
        void onShapeFinished ();
        void onColorChanged(Color color);
    }
}