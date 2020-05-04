import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class MyCanvas extends Canvas  implements MouseListener, MouseMotionListener, MouseWheelListener {

    public ArrayList<MyShapes> shapes;
    private Color activeColor = Color.BLACK;
    private char option;
    private int painting;
    private int activeShape;
    private MyMenu menu;
    private boolean moving;
    private int[] movingPoint;
    private int[] movingVector;
    double rescaleFactor;

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
        activeShape=0;
        painting = 0;
        moving = false;
        movingPoint=new int[2];
        movingVector=new int[2];
        rescaleFactor=.95;

        paintSth();
    }
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
                    MyRectangle r = (MyRectangle) s;
                    if(moving&&shapes.indexOf(s)==activeShape) {
                        g2d.fillRect(
                                Math.min(r.getRect()[0][0], r.getRect()[1][0])+movingVector[0],
                                Math.min(r.getRect()[0][1], r.getRect()[1][1])+movingVector[1],
                                r.getRect()[2][0],
                                r.getRect()[2][1]);
                    } else {
                        g2d.fillRect(Math.min(r.getRect()[0][0], r.getRect()[1][0]), Math.min(r.getRect()[0][1], r.getRect()[1][1]), r.getRect()[2][0], r.getRect()[2][1]);
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
                    }catch (Exception notTriangle){
                        
                    }
                }
            }
        }
    }
    private int pointInShape(MouseEvent mE){
        for(MyShapes s:shapes){
            if(s.pointIn(mE))
                return shapes.indexOf(s);
        }
        return -1;
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
                setOption('0');
            else{
                setOption('e');
                Color temp=JColorChooser.showDialog(null, "", shapes.get(activeShape).getColor());
                if(temp!=null) {
                    shapes.get(activeShape).setColor(temp);
                }
            }
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
                    if(moving)
                        System.out.println(mE.getX()+"\t"+mE.getY());
                    movingVector=new int[]{mE.getX()-movingPoint[0],mE.getY()-movingPoint[1]};
                    break;
                default:
                    break;
            }
        }else{
            if(option=='e'&&moving){
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



    @Override
    public void mouseWheelMoved(MouseWheelEvent mWE) {
        double factor=rescaleFactor;
        if(option=='e') {
            if (mWE.getWheelRotation() < 0)
                factor = 1 / factor;
            shapes.get(activeShape).resize(factor);
            repaint();
        }
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
    public String savingPaint(){
        StringBuilder result = new StringBuilder();
        for(MyShapes s:shapes){
            result.append(s.toFile());
        }
        return result.toString();
    }
    public void shapesFromFile (String data){
        String [] newData = data.split("\n");
        String[] shapeData;
        for(String s: newData){
            shapeData = s.split("\t");
            if(shapeData[0].equals("C")){
                if(shapeData.length==7){
                    Circle c = new Circle(
                            new int[]{Integer.parseInt(shapeData[1]),Integer.parseInt(shapeData[2])},
                            Double.parseDouble(shapeData[3]),
                            new Color(Integer.parseInt(shapeData[4]),Integer.parseInt(shapeData[5]),Integer.parseInt(shapeData[6])));
                    shapes.add(c);
                }else
                    return;
            }else if(shapeData[0].equals("R")){
                if(shapeData.length==8){
                    MyRectangle r = new MyRectangle(
                            new int[]{Integer.parseInt(shapeData[1]),Integer.parseInt(shapeData[2])},
                            new int[]{Integer.parseInt(shapeData[3]),Integer.parseInt(shapeData[4])},
                            new Color(Integer.parseInt(shapeData[5]),Integer.parseInt(shapeData[6]),Integer.parseInt(shapeData[7])));
                    shapes.add(r);
                }else
                    return;
            }else if(shapeData[0].equals("T")){
                if(shapeData.length==10){
                    Triangle t = new Triangle(
                            new int[]{Integer.parseInt(shapeData[1]),Integer.parseInt(shapeData[2]),Integer.parseInt(shapeData[3])},
                            new int[]{Integer.parseInt(shapeData[4]),Integer.parseInt(shapeData[5]),Integer.parseInt(shapeData[6])},
                            new Color(Integer.parseInt(shapeData[7]),Integer.parseInt(shapeData[8]),Integer.parseInt(shapeData[9])));
                    shapes.add(t);
                }else
                    return;
            }else
                return;
        }
        repaint();
    }
}
