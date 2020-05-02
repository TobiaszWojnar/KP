import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class MyCanvas extends Canvas  implements MouseListener, MouseMotionListener, MouseWheelListener {

    public ArrayList<MyShapes> shapes;
    private Color activeColor = Color.BLACK;
    private char option;
    private int pointsMade;
    private int painting;
    private int activeShape;
    private MyMenu menu;

    /**
     * option   {c,r,t} > creating {circle,rectangle,triangle}
     *          '0'     > no action
     *          'e'     > editing active figure
     */
    public MyCanvas(){
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        option = '0';
        shapes = new ArrayList<>();
        pointsMade = 0;
        activeShape=0;
        painting = 0;

        paintSth();
    }
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        for(MyShapes s:shapes){
            g2d.setColor(s.getColor());
            try{
                Circle c = (Circle)s;
                g2d.fillOval((int)(c.getCenter()[0]-c.getRadius()),
                             (int)(c.getCenter()[1]-c.getRadius()),
                             2*(int)c.getRadius(),
                             2*(int)c.getRadius());
            }catch (Exception notCircle){
                try{
                    MyRectangle r = (MyRectangle)s;
                    g2d.fillRect(Math.min(r.getRect()[0][0],r.getRect()[1][0]), Math.min(r.getRect()[0][1],r.getRect()[1][1]), r.getRect()[2][0], r.getRect()[2][1]);
                }catch (Exception notRect){
                    try{
                        Triangle t = (Triangle)s;
                        if(painting==1){
                            g2d.drawLine(t.getX(0),t.getY(0),t.getX(1),t.getY(1));
                        }
                        g2d.fillPolygon(t.getX(),t.getY(),3);
                    }catch (Exception notTriangle){

                    }
                }
            }
        }
    }
    public void setMenu(MyMenu menu) {
        this.menu = menu;
    }
    public void setOption(char option) {
        this.option = option;
    }
    public Color getColor(){
        return activeColor;
    }

    public void setColor(Color activeColor) {
        this.activeColor=activeColor;
    }
    @Override
    public void mouseClicked(MouseEvent mE) {

        if(SwingUtilities.isLeftMouseButton(mE)){
            switch (option){
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
                    //TODO moving?
                    break;
                default:
                    break;
            }
        }else if (SwingUtilities.isRightMouseButton(mE)){
            if(painting!=0) {
                shapes.remove(shapes.size() - 1);
                painting=0;
            }
            setOption('0');
            menu.setMenuOption(null);
            //TODO isIN

            //
        }
        repaint();
            System.out.println(mE.getX()+"\t"+ mE.getY());

    }
    @Override
    public void mouseMoved(MouseEvent mE) {
        if(painting!=0){
            switch (option) {
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
                case 'e':
                    break;
                default:
                    break;
            }
            repaint();
        }
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



    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {

    }
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
}
