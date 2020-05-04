import java.awt.*;
import java.awt.event.MouseEvent;

public class MyRectangle extends MyShapes{
    private Color color;
    private int[][] rect = new int[3][2];//rect[0] = first point; rect[1] = second point; rect[2] = rect[2]= wight &

    MyRectangle(int[] first, int[] second, Color color) {
        this.color=color;
        rect[0]=first;
        rect[1]=second;
        setDimension();
    }
    public void setSecond (int x, int y){
        rect[1][0]=x;
        rect[1][1]=y;
        setDimension();
    }
    public void setDimension(){
        rect[2][0]=Math.abs(rect[0][0]-rect[1][0]); //do we want abs
        rect[2][1]=Math.abs(rect[0][1]-rect[1][1]); //do we want abs
    }
    @Override
    public void setColor(Color newColor) {
        color=newColor;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public boolean pointIn(MouseEvent mE) {
        int[] upperLeft = new int[] {Math.min(rect[0][0],rect[1][0]),Math.min(rect[0][1],rect[1][1])};//TODO pole upper left?
        int[] lowerRight = new int[] {Math.max(rect[0][0],rect[1][0]),Math.max(rect[0][1],rect[1][1])};
        if(mE.getX()>=upperLeft[0]&&mE.getX()<=lowerRight[0]){
            if(mE.getY()>=upperLeft[1]&&mE.getY()<=lowerRight[1]){
                return true;
            }
        }
        return false;
    }

    @Override
    public void move(int[] point, MouseEvent mE) {
        rect[0][0]+=mE.getX()-point[0];
        rect[0][1]+=mE.getY()-point[1];
        rect[1][0]+=mE.getX()-point[0];
        rect[1][1]+=mE.getY()-point[1];
    }

    @Override
    public void resize(double factor) {
        rect[1][0]+= (rect[0][0]-rect[1][0])*(1-factor);
        rect[1][1]+= (rect[0][1]-rect[1][1])*(1-factor);
        setDimension();
    }

    @Override
    public String toFile() {
        return "R\t"+getRect()[0][0]+"\t"+getRect()[0][1]+"\t"+getRect()[1][0]+"\t"+getRect()[1][1]+"\t"+getColor().getRed()+"\t"+getColor().getGreen()+"\t"+getColor().getBlue()+"\n";
    }

    public int[][] getRect(){
        return rect;
    }

}
