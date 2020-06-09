import javax.swing.*;
import java.awt.*;

public class TreeGUI extends JFrame {//TODO documentation
    MyClient client;
    TreeCanvas treeCanvas;
    SidePanel menuPanel;
    JScrollPane scrollPane;

    public TreeGUI() {
        client=new MyClient();

        menuPanel = new SidePanel(client.getType());
        add(menuPanel,BorderLayout.LINE_START);

        treeCanvas = new TreeCanvas();
        scrollPane = new JScrollPane(treeCanvas,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        add(scrollPane,BorderLayout.CENTER);

        setTitle("Tree visualization");
        setMinimumSize(new Dimension(240,240));
        setPreferredSize(new Dimension(640,320));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();

        menuPanel.setListener(new SidePanel.Listener() {
            @Override
            public void typeChosen(String type) {
                treeCanvas.treeUpdate(client.sendType(type));
                setScroll(0);
                System.out.println(scrollPane.getSize().height);
            }
            @Override
            public void elementToInsertChosen(String element) {
                treeCanvas.treeUpdate(client.insert(element));
                setScroll(0.5);
            }
            @Override
            public void elementToDeleteChosen(String element) {
                treeCanvas.treeUpdate(client.delete(element));
                setScroll(0.5);
            }
            @Override
            public void elementToSearchChosen(String element) {//TODO Paint different color
                String wasFound = "was found";
                if("null".equals(client.search(element)))
                    wasFound = "was not found";
                JOptionPane.showMessageDialog(treeCanvas, "key= "+element+" "+wasFound, "Search result", JOptionPane.INFORMATION_MESSAGE);
                setScroll(0.5);
            }
            @Override
            public void drawChosen() {
                treeCanvas.treeUpdate(client.draw());
                setScroll(0.5);
            }
        });
    }
    private void setScroll(double percent){
        JScrollBar scrollBar = scrollPane.getHorizontalScrollBar();
        int min = scrollBar.getMaximum();
        int max = scrollBar.getMaximum();
        if(0<=percent&&percent<=1)
            scrollBar.setValue((int)(min+(max-min)*percent));
    }
    public static void main(String[] args) {
        TreeGUI app = new TreeGUI();
        app.setVisible(true);
    }
}