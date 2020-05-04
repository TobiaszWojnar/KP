import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Menu class extends Canvas and implements listeners for mouse movement
 * Contains all shapes and state of program
 */
public class MyCanvas extends Canvas  implements MouseListener, MouseMotionListener, MouseWheelListener {

    public ArrayList<MyShapes> shapes;
    private Color activeColor = Color.BLACK;
    private char appState;
    private int painting;
    private int activeShape;
    private MyMenu menu;
    private boolean moving;
    private int[] movingPoint;
    private int[] movingVector;
    double rescaleFactor;

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

        //paintSth();
    }

    /**
     * Paints all figures from shapes
     */
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for(MyShapes s:shapes){
            g2d.setColor(s.getColor());
            try{
                Circle c = (Circle)s;
                if(moving&&shapes.indexOf(s)==activeShape) {
                    g2d.fillOval( (int) (c.getCenter()[0] - c.getRadius())+movingVector[0],
                                 (int) (c.getCenter()[1] - c.getRadius())+movingVector[1],
                                    (int) (2 * c.getRadius()),
                                    (int) (2 * c.getRadius()));
                } else {
                    g2d.fillOval((int) (c.getCenter()[0] - c.getRadius()),
                            (int) (c.getCenter()[1] - c.getRadius()),
                            2 * (int) c.getRadius(),
                            2 * (int) c.getRadius());
                }
            }catch (Exception notCircle){
                try{
                    MyRectangle r = (MyRectangle)s;
                    if(moving&&shapes.indexOf(s)==activeShape) {
                        g2d.fillRect(
                                Math.min(r.getRect()[0][0], r.getRect()[1][0])+movingVector[0],
                                Math.min(r.getRect()[0][1], r.getRect()[1][1])+movingVector[1],
                                r.getRect()[2][0],
                                r.getRect()[2][1]);
                    } else {
                        g2d.fillRect(
                                Math.min(r.getRect()[0][0], r.getRect()[1][0]),
                                Math.min(r.getRect()[0][1], r.getRect()[1][1]),
                                r.getRect()[2][0],
                                r.getRect()[2][1]);
                    }
                }catch (Exception notRect){
                    try{
                        Triangle t = (Triangle)s;
                        if(painting==1){
                            g2d.drawLine(t.getX(0),t.getY(0),t.getX(1),t.getY(1));
                        }
                        if(moving&&shapes.indexOf(s)==activeShape) {
                            g2d.fillPolygon(
                                    new int[]{t.getX(0)+movingVector[0],t.getX(1)+movingVector[0],t.getX(2)+movingVector[0]},
                                    new int[]{t.getY(0)+movingVector[1],t.getY(1)+movingVector[1],t.getY(2)+movingVector[1]},
                                    3);
                        } else {
                            g2d.fillPolygon(t.getX(), t.getY(), 3);
                        }
                    }catch (Exception ignored){
                        
                    }
                }
            }
        }
    }

    /**
     * @return index of figure in which we clicked or -1 if no such figure existed
     */
    private int pointInShape(MouseEvent mE){
        for(MyShapes s:shapes){
            if(s.pointIn(mE))
                return shapes.indexOf(s);
        }
        return -1;
    }

    /**
     * sets menu
     * @param menu
     */
    public void setMenu(MyMenu menu) {
        this.menu = menu;
    }

    /**
     * sets state of program
     * @param appState one of 5 states
     * 'c' >    creating circle
     * 'r' >    creating rectangle
     * 't' >    creating triangle
     * '0' >    no action
     * 'e' >    editing active figure
     */
    public void setAppState(char appState) {
        this.appState = appState;
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
     * @return parameters of all shapes as String
     * each figure in new line, values separated by tabulators
     */
    public String savingPaint(){
        StringBuilder result = new StringBuilder();
        for(MyShapes s:shapes){
            result.append(s.toFile());
        }
        return result.toString();
    }

    /**
     * reads figure parameters form String and adds them to list of shapes
     * @param data parameters of all shapes as String
     * each figure in new line, values separated by tabulators
     */
    public void shapesFromFile (String data){
        String [] newData = data.split("\n");
        String[] shapeData;
        for(String s: newData){
            shapeData = s.split("\t");
            switch (shapeData[0]) {
                case "C":
                    if (shapeData.length == 7) {
                        Circle c = new Circle(
                                new int[]{Integer.parseInt(shapeData[1]), Integer.parseInt(shapeData[2])},
                                Double.parseDouble(shapeData[3]),
                                new Color(Integer.parseInt(shapeData[4]), Integer.parseInt(shapeData[5]), Integer.parseInt(shapeData[6])));
                        shapes.add(c);
                    } else
                        return;
                    break;
                case "R":
                    if (shapeData.length == 8) {
                        MyRectangle r = new MyRectangle(
                                new int[]{Integer.parseInt(shapeData[1]), Integer.parseInt(shapeData[2])},
                                new int[]{Integer.parseInt(shapeData[3]), Integer.parseInt(shapeData[4])},
                                new Color(Integer.parseInt(shapeData[5]), Integer.parseInt(shapeData[6]), Integer.parseInt(shapeData[7])));
                        shapes.add(r);
                    } else
                        return;
                    break;
                case "T":
                    if (shapeData.length == 10) {
                        Triangle t = new Triangle(
                                new int[]{Integer.parseInt(shapeData[1]), Integer.parseInt(shapeData[2]), Integer.parseInt(shapeData[3])},
                                new int[]{Integer.parseInt(shapeData[4]), Integer.parseInt(shapeData[5]), Integer.parseInt(shapeData[6])},
                                new Color(Integer.parseInt(shapeData[7]), Integer.parseInt(shapeData[8]), Integer.parseInt(shapeData[9])));
                        shapes.add(t);
                    } else
                        return;
                    break;
                default:
                    return;
            }
        }
        repaint();
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
                            break;
                        default:
                            break;
                    }
                    break;
                case 'e':
                    if(moving) {
                        shapes.get(activeShape).move(movingPoint, mE);
                        moving = false;
                    }else{
                        if (shapes.get(activeShape).pointIn(mE)) {
                            moving=true;
                            movingPoint=new int[]{mE.getX(),mE.getY()};
                        }
                    }
                    break;
                default:
                    break;
            }
        }else if (SwingUtilities.isRightMouseButton(mE)){
            if(painting!=0) {
                shapes.remove(shapes.size() - 1);
                painting=0;
            }
            menu.setMenuOption(null);
            activeShape = pointInShape(mE);
            if(pointInShape(mE)==-1)
                setAppState('0');
            else{
                setAppState('e');
                Color temp=JColorChooser.showDialog(null, "", shapes.get(activeShape).getColor());
                if(temp!=null) {
                    shapes.get(activeShape).setColor(temp);
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
        }else{
            if(appState=='e'&&moving){
                movingVector=new int[]{mE.getX()-movingPoint[0],mE.getY()-movingPoint[1]};
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
    }

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
}
