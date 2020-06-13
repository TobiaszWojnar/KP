import javax.swing.*;
import java.awt.*;

/**
 * The <code>TreeGUI</code> class is responsible for creating GUI for {@link GenericBinaryTree binary tree}.
 * It is exercise for object oriented programing course at Wroclaw University of Science and Technology.
 * The objective of this list was to implement binary search tree with generic type keys,
 * create {@link TreeGUI GUI  application} and implement {@link MyClient client} {@link MyServer server} architecture.
 * <p>
 *      <code>TreeGUI</code> extends {@link JFrame} and launches {@link MyClient}.
 *      It consists of {@link TreeCanvas canvas} wrapped in {@link javax.swing.JScrollPane JScrollPane},
 *      {@link SidePanel} with types of key implemented in {@link MyServer} and implements it's {@link SidePanel.Listener listener} enabling sending requests to {@code MyServer}.
 *      It passes responses from {@link MyServer server} to {@link TreeCanvas canvas}.
 *      Also after each update on {@code canvas} method {@link #setScroll(double) setScroll}
 *      is called which enables setting scrollbar and view for specific location.
 *      The {@link #main(String[]) main method} launches {@code TreeGUI}.
 * <p/>
 * @version     09 June 2020
 * @author      Tobiasz Wojnar
 * @see         GenericBinaryTree
 * @see         MyClient
 * @see         SidePanel
 * @see         TreeCanvas
 */
public class TreeGUI extends JFrame {
    private final MyClient client;
    private final TreeCanvas treeCanvas;
    private final JScrollPane scrollPane;

    /**
     * Constructor of {@code TreeGUI} launches {@link MyClient}.
     * {@code TreeGUI} extends {@code JFrame} consists of {@link TreeCanvas canvas} wrapped in {@link JScrollPane},
     * {@link SidePanel} and implements it's {@link SidePanel.Listener listener} enabling sending requests to {@link MyServer}.
     * It passes responses from {@code MyServer server} to {@code TreeCanvas canvas}.
     * Also after each update on {@code canvas} method {@link #setScroll(double) setScroll}
     * is called which enables setting scrollbar and view for specific location.
     */
    public TreeGUI() {
        client=new MyClient();

        SidePanel menuPanel = new SidePanel(client.getTypes());
        add(menuPanel,BorderLayout.LINE_START);

        treeCanvas = new TreeCanvas();
        scrollPane = new JScrollPane(treeCanvas,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(scrollPane,BorderLayout.CENTER);

        setTitle("Binary Search Tree GUI");
        setMinimumSize(new Dimension(240,240));
        setPreferredSize(new Dimension(640,320));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        menuPanel.setListener(new SidePanel.Listener() {
            /**
             * On type changed in {@link SidePanel} request is send through {@link MyClient}.
             * Result is returned to {@link TreeCanvas}. It resets JScrollPane view (to upper left corner).
             * @param type Type of key in {@link GenericBinaryTree tree}.
             */
            @Override
            public void typeChosen(String type) {
                treeCanvas.treeUpdate(client.sendType(type));
                setScroll(0);
            }
            /**
             * Send insert request through {@link MyClient}.
             * Result is returned to {@link TreeCanvas}. It sets JScrollPane view to the middle.
             * @param element Key to insert in {@link GenericBinaryTree tree}.
             * @see SidePanel
             */
            @Override
            public void elementToInsertChosen(String element) {
                treeCanvas.treeUpdate(client.insert(element));
                setScroll(0.5);
            }
            /**
             * Send delete request through {@link MyClient}.
             * Result is returned to {@link TreeCanvas}. It sets JScrollPane view to the middle.
             * @param element Key to delete from {@link GenericBinaryTree tree}.
             * @see SidePanel
             */
            @Override
            public void elementToDeleteChosen(String element) {
                treeCanvas.treeUpdate(client.delete(element));
                setScroll(0.5);
            }
            /**
             * Send search request through {@link MyClient}.
             * Launches popup with answer.
             * @param element Key to be searched in {@link GenericBinaryTree tree}.
             * @see SidePanel
             */
            @Override
            public void elementToSearchChosen(String element) {//TODO Paint different color
                String wasFound = "was found";
                if("null".equals(client.search(element)))
                    wasFound = "was not found";
                JOptionPane.showMessageDialog(treeCanvas, "key = "+element+" "+wasFound, "Search result", JOptionPane.INFORMATION_MESSAGE);
            }
            /**
             * Repaints tree.
             * Send draw request through {@link MyClient}.
             * Result is returned to {@link TreeCanvas}. It sets JScrollPane view to the middle.
             * @see SidePanel
             */
            @Override
            public void drawChosen() {
                treeCanvas.treeUpdate(client.draw());
                setScroll(0.5);
            }
        });
    }

    /**
     * Sets location of JScrollBar. As result sets view of {@code TreeCanvas}
     * @param percent Percent of how much to right it should set scroll bar.
     *                If {@code percent} is not between 0 and 1 it sets it to left edge.
     */
    private void setScroll(double percent){
        JScrollBar scrollBar = scrollPane.getHorizontalScrollBar();
        int min = scrollBar.getMaximum();
        int max = scrollBar.getMaximum();
        if(0<=percent&&percent<=1)
            scrollBar.setValue((int)(min+(max-min)*percent));
    }

    /**
     * Launches {@code TreeGUI}
     */
    public static void main(String[] args) {
        TreeGUI app = new TreeGUI();
        app.setVisible(true);
    }
}