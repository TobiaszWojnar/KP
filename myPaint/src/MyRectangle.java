import java.awt.*;

public class MyRectangle extends MyShapes{
    private Color color;
    private int[][] rect = new int[3][2];//rect[0] = first point; rect[1] = second point; rect[2] = rect[2]= wight &

    MyRectangle(int[] first, int[] second, Color color) {
        this.color=color;
        rect[0]=first;
        rect[1]=second;
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
    public int[][] getRect(){
        return rect;
    }

}
