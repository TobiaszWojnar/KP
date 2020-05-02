import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class MyCanvas extends Canvas  implements MouseListener, MouseMotionListener, MouseWheelListener {

    public ArrayList<MyShapes> shapes;
    private Color activeColor = Color.BLACK;
    private char option;

    public MyCanvas(){
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);

        option = 0;
        shapes = new ArrayList<>();

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
                             (int)(c.getCenter()[0]+c.getRadius()),
                             (int)(c.getCenter()[1]+c.getRadius()));
            }catch (Exception notCircle){
                try{
                    MyRectangle r = (MyRectangle)s;
                    g2d.fillRect(r.getRect()[0][0], r.getRect()[0][1],r.getRect()[1][0], r.getRect()[1][1]);
                }catch (Exception notRect){
                    try{
                        Triangle t = (Triangle)s;
                        g2d.fillPolygon(t.getX(),t.getY(),3);
                    }catch (Exception notTriangle){

                    }
                }
            }
        }
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
        System.out.println(mE.getX()+"\t"+ mE.getY());
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
    public void mouseMoved(MouseEvent mouseEvent) {

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
        Triangle t1 = new Triangle(new int[] {64,64}, new int[] {64,128}, new int[] {128,64}, Color.MAGENTA);
        shapes.add(t1);
    }
}
