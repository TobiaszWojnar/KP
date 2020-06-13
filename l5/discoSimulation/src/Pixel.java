import java.awt.*;
import java.util.Random;

/**
 * The <code>Pixel</code> class is responsible changing its color depending on situation.
 * Each <code>Pixel</code> is part of <code>board<code> and after time <code>t</code> changes it's color.
 * Time <code>t</code> is random number of milliseconds in range <code>[0.5k, 1.5k]</code>.
 * <p>
 * For "functionality" <code>3</code> each rectangle with probability <code>p</code>  changes its color to random.
 * Else each rectangle with probability <code>p</code>  changes its color to random or else to average of its neighbours.
 *
 * @version     24 May 2020
 * @author      Tobiasz Wojnar
 * @see         L5
 * @see         DiscoSimulation
 */
public class Pixel extends Thread implements Runnable{
    private volatile boolean isAlive;
    private volatile boolean isRunning;
    private final int f;
    private final int p;
    private final Pixel[][] board;
    private final int x;
    private final int y;
    private final int time;
    private volatile Color color;
    private final Random r;

    /**
     * Creates "pixel" a thread that changes color depending on it's parameters.
     * It starts with random color and after <code>t</code> milliseconds changes color depending of functionality.
     * @param functionality Determined by exercise specification.
     *          For "functionality" <code>3</code> each rectangle with probability <code>p</code>  changes its color to random.
     *          Else each rectangle with probability <code>p</code>  changes its color to random or else to average of its neighbours.
     * @param k Parameter responsible for speed of changing color.
     *          Each rectangle changes it's color after random number of milliseconds in range <code>[0.5k, 1.5k]</code>.
     * @param probability Probability for which action to preformed.
     *                    Integer number in range of 0 to 100.
     * @param board 2D table containing all pixels.
     * @param x coordinate of pixel in board.
     * @param y coordinate of pixel in board.
     * @param random Random number generator.
     */
    public Pixel (int functionality, int k, int probability, Pixel[][] board, int x, int y, Random random){
        isRunning = true;
        isAlive = true;
        f=functionality;
        p=probability;
        this.board=board;
        this.x=x;
        this.y=y;
        r=random;
        time = (int)((r.nextDouble()+.5)*k);
        color = randomColor();
    }

    /**
     * Function for changing pixels color.
     * For "functionality" <code>3</code> each rectangle with probability <code>p</code>  changes its color to random.
     * Else each rectangle with probability <code>p</code> changes its color to random or else to average of its neighbours.
     * Synchronizes setting color.
     */
    private void pixelAction(){
        Color tmpCol = null;
        if(f==3){
            if(r.nextInt(101) < p )
               tmpCol = randomColor();
        }else {
            if(r.nextInt(101) < p )
                tmpCol = randomColor();
            else
                tmpCol= averageColor();
        }
        synchronized (this){
            color = (tmpCol == null ? color : tmpCol);
        }
    }

    /**
     * Generates random color.
     * @return random color.
     */
    private Color randomColor(){
        return new Color(r.nextInt(256),r.nextInt(256),r.nextInt(256));
    }

    /**
     * Returns color of a pixel.
     * @return color of pixel.
     */
    public synchronized Color getColor(){
        return color;
    }

    /**
     * Returns the red component in the range 0-255 in the default sRGB space or 0 if pixel isn't running.
     * @return the red component.
     */
    public synchronized int getRed(){
        if(isRunning)
            return color.getRed();
        else
            return 0;
    }
    /**
     * Returns the green component in the range 0-255 in the default sRGB space or 0 if pixel isn't running.
     * @return the green component.
     */
    public synchronized int  getGreen(){
        if(isRunning)
            return color.getGreen();
        else
            return 0;
    }
    /**
     * Returns the blue component in the range 0-255 in the default sRGB space or 0 if pixel isn't running.
     * @return the blue component.
     */
    public synchronized int getBlue(){
        if(isRunning)
            return color.getBlue();
        else
            return 0;
    }

    /**
     * Calculates average color of pixel neighbours. If neighbour isn't running it's ignored.
     * @return average color of pixel neighbours.
     */
    private Color averageColor (){
        int l= ( x+board.length- 1 ) % board.length;
        int r= ( x+board.length+ 1 ) % board.length;
        int u=(y+board[0].length-1)%board[0].length;
        int d=(y+board[0].length+1)%board[0].length;
        int numNeighbours=board[l][y].getStateInt()+board[r][y].getStateInt()+board[x][u].getStateInt()+board[x][d].getStateInt();
        if(numNeighbours==0) {
                return null;
        }
        int red = (board[l][y].getRed()+board[r][y].getRed()+board[x][u].getRed()+board[x][d].getRed())/numNeighbours;
        int green = (board[l][y].getGreen()+board[r][y].getGreen()+board[x][u].getGreen()+board[x][d].getGreen())/numNeighbours;
        int blue = (board[l][y].getBlue()+board[r][y].getBlue()+board[x][u].getBlue()+board[x][d].getBlue())/numNeighbours;
        return new Color(red,green,blue);
    }

    /**
     * While pixel isAlive it changes color depending on it's parameters
     */
    @Override
    public void run() {
        while(isAlive) {
            if(isRunning){
                    pixelAction();
                try {
                    sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Inverts state of pixel.
     */
    public synchronized void changeState() {
        isRunning =! isRunning;
    }

    /**
     * Returns state of pixel. 1 if running else 0
     * @return state of pixel
     */
    public synchronized int getStateInt(){
        return (isRunning ? 1 : 0);
    }

    /**
     * Kills pixel. By setting isAlive to false it breaks pixel form while loop.
     */
    public void endPixel(){
        isAlive = false;
    }
}