import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * The <code>DiscoSimulation</code> class is responsible for creating simulation with flickering rectangles using threads.
 * It simulates a board of <code>m</code> x <code>n</code> {@link Pixel rectangles} with random initial color.
 * Each rectangle is thread that after time <code>t</code> changes it's color.
 * Time <code>t</code> is random number of milliseconds in range <code>[0.5k, 1.5k]</code>.
 * <p>
 * For "functionality" <code>3</code> each rectangle with probability <code>p</code>  changes its color to random.
 * For "functionality" <code>4</code> each rectangle with probability <code>p</code>  changes its color to random or else to average of its neighbours.
 * "Functionality" <code>5</code> adds ability to stop rectangle form changing color and after being clicked does not take part in calculating average color of its neighbours.
 *
 * @version     24 May 2020
 * @author      Tobiasz Wojnar
 * @see         L5
 * @see         Pixel
 */
public class DiscoSimulation extends JPanel implements MouseListener {
    private final int m;
    private final int n;
    private final Pixel[][] board;
    private final Timer paintingTimer;

    /**
     * Simulates a board of <code>m</code> x <code>n</code> {@link Pixel rectangles} with random initial color.
     * Each rectangle is thread that after time <code>t</code> changes it's color.
     * Time <code>t</code> is random number of milliseconds in range <code>[0.5k, 1.5k]</code>.
     * It refreshes itself each 20 milliseconds by with {@link Timer}.
     * @param functionality Determined by exercise specification.
     * For "functionality" <code>3</code> each rectangle with probability <code>p</code>  changes its color to random.
     * For "functionality" <code>4</code> each rectangle with probability <code>p</code>  changes its color to random or else to average of its neighbours.
     * "Functionality" <code>5</code> adds ability to stop rectangle form changing color and after being clicked does not take part in calculating average color of its neighbours.
     * @param m number of columns in board.
     * @param n number of rows in board.
     * @param k parameter responsible for speed of changing color. Each rectangle changes it's color after random number of milliseconds in range <code>[0.5k, 1.5k]</code>.
     * @param p Probability for which action to preformed. Integer number in range of 0 to 100.
     */
    public DiscoSimulation (int functionality, int m, int n, int k, int p) {
        Random r = new Random();
        if(functionality==5)
            addMouseListener(this);
        this.m=m;
        this.n=n;
        board = new Pixel[m][n];
        for(int i=0;i<m;i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = new Pixel(functionality, k, p, board, i, j, r);

            }
        }
        for(int i=0;i<m;i++) {
            for (int j = 0; j < n; j++) {
                board[i][j].start();
            }
        }
        paintingTimer = new Timer(20, actionEvent -> repaint());
        paintingTimer.start();
    }

    /**
     * Shows {@link #DiscoSimulation} and kills threads on closing. Uses {@link #cleanup()} method.
     */
    public void createAndShowGUI() {
        JFrame frame = new JFrame("DiscoSimulation");
        frame.setPreferredSize(new Dimension(640, 640));
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        frame.setAutoRequestFocus(false);
        frame.addWindowListener(new WindowAdapter() {
            /**
             * Listener for closing simulation. Kills {@link Pixel pixels} and repaintingTimer on closing using {@link DiscoSimulation#cleanup()} method.
             */
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosed(e);
                cleanup();
            }
        });
    }

    /**
     * Stops {@link Pixel pixels} and repaintingTimer.
     */
    public void cleanup(){
        for(int i=0;i<m;i++) {
            for (int j = 0; j < n; j++) {
                board[i][j].endPixel();
            }
        }
        paintingTimer.stop();
    }

    /**
     * Paints each rectangle.
     * @param g paints on it
     * @see Pixel
     */
    public void paintComponent(Graphics g) {
        double width  = (double) getWidth()/(double)m;
        double height = (double)getHeight()/(double)n;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                g.setColor(board[i][j].getColor());
                g.fillRect( (int)(i*width), (int)(j*height), (int)(width+1), (int)(height+1));
            }
        }
    }

    /**
     * Changes state for rectangle that was clicked.
     * @param mE place where mouse was clicked
     * @see Pixel#changeState()
     */
    @Override
    public void mouseClicked(MouseEvent mE) {
        double width = (double) getWidth()/(double) m;
        double height = (double)getHeight()/(double)n;
        int x = (int)((double)(mE.getX())/width);
        int y = (int)((double)(mE.getY())/height);
        board[x][y].changeState();
    }

    /**
     * No action performed
     */
    @Override public void mousePressed(MouseEvent mouseEvent) { }
    /**
     * No action performed
     */
    @Override public void mouseReleased(MouseEvent mouseEvent) { }
    /**
     * No action performed
     */
    @Override public void mouseEntered(MouseEvent mouseEvent) { }
    /**
     * No action performed
     */
    @Override public void mouseExited(MouseEvent mouseEvent) { }
}