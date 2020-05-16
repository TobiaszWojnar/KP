import java.awt.*;

public class Pixel {
    Color color;
    int time;
    public Pixel(int speed){
        color = new Color(Rand.intColor(), Rand.intColor(), Rand.intColor());
        time = (int)Rand.time(speed);

    }
    public synchronized static void pixelAction (int functionality, int m, int n,int i, int j, int p, Color[][] colors){
        if(functionality==3){
            if(Rand.probability() < p )
                colors[i][j] = randomColor();
        }else {
            if(Rand.probability() < p )
                colors[i][j] = randomColor();
            else
                colors[i][j] = averageColor(m,n,i,j,colors);
        }
    }

    public static Color randomColor (){
        Color color = new Color(Rand.intColor(), Rand.intColor(), Rand.intColor());
        return color;
    }

    private static Color averageColor(int m, int n, int i, int j, Color[][] colors){
        int l=(i+m-1)%m;
        int r=(i+m+1)%m;
        int u=(j+n-1)%n;
        int d=(j+n+1)%n;
        int red = (colors[l][j].getRed()+colors[r][j].getRed()+colors[i][u].getRed()+colors[i][d].getRed())/4;
        int green = (colors[l][j].getGreen()+colors[r][j].getGreen()+colors[i][u].getGreen()+colors[i][d].getGreen())/4;
        int blue = (colors[l][j].getBlue()+colors[r][j].getBlue()+colors[i][u].getBlue()+colors[i][d].getBlue())/4;
        Color color = new Color(red,green,blue);
        return color;
    }
}
