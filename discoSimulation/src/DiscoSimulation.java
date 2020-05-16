import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DiscoSimulation extends JPanel implements MouseListener {
    int f;
    private final int m;
    private final int n;
    private final int k;
    private final int p;
    private final Color[][] colors;
    private final int[][] time;
    Pixel[][] board;
    Thread[][] threads;
    private JFrame frame;

    public DiscoSimulation (int functionality, int m, int n, int k, int p) {
        if(functionality==5)
            addMouseListener(this);
        f=functionality;
        this.m=m;
        this.n=n;
        this.k=k;
        this.p=p;
        colors = new Color[m][n];
        time = new int[m][n];
        board = new Pixel[m][n];
        threads = new Thread[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                colors[i][j] = Pixel.randomColor();
                time[i][j] = (int)Rand.time(k);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int I = i;
                int J = j;
                threads[I][J] = new Thread(() -> {
                    while (true) {
                        Pixel.pixelAction(f,m, n, I, J, p, colors);
                        repaint();
                        try {
                            Thread.sleep(time[I][J]);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                threads[I][J].start();
            }
        }
    }

    public void createAndShowGUI() {
        frame = new JFrame("DiscoSimulation");
        frame.setPreferredSize(new Dimension(640, 640));
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        frame.setAutoRequestFocus(false);
    }

    public void paintComponent(Graphics g) {
        double width = (double) getWidth()/(double) m;
        double height = (double)getHeight()/(double)n;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                g.setColor(colors[i][j]);
                g.fillRect( (int)(i*width), (int)(j*height), (int)(width+1), (int)(height+1));
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent mE) {
        System.out.println(mE.getX());
        threads[0][0].stop();
    }

    @Override public void mousePressed(MouseEvent mouseEvent) { }

    @Override public void mouseReleased(MouseEvent mouseEvent) { }

    @Override public void mouseEntered(MouseEvent mouseEvent) { }

    @Override public void mouseExited(MouseEvent mouseEvent) { }
}