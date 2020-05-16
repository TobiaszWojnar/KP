import java.util.Random;

public class Rand {
    private static Random r = new Random();

    public static int intColor(){
        return r.nextInt(256);
    }

    public  static double time(int k){
        return (r.nextDouble()+.5)*k;
    }

    public static int probability(){
        return r.nextInt(101);
    }
}
