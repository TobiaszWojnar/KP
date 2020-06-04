import javax.swing.*;
import java.awt.*;


public class TreeGUI extends JFrame {
    TreeContainer treeContainer;
    GenericBinaryTree tree;//TODO get rid of tree
    MyClient client;

    public TreeGUI(GenericBinaryTree ntree) {
        //client=new MyClient();

        this.tree=ntree;
        JPanel container = new JPanel();
        add(container);

        TreeCanvas treeCanvas = new TreeCanvas();
        container.add(treeCanvas);

        treeContainer = new TreeContainer(tree.root);
        container.add(treeContainer);

        SidePanel sidePanel = new SidePanel(client.getType());
        sidePanel.setListener(new SidePanel.Listener() {//TODO implement TreeCanvas
            @Override
            public void typeChosen(String type) {
                tree.draw();
            }
            @Override
            public void elementToInsertChosen(String element) {
                tree.insert(element);
                tree.draw();
                //client.insert(element);
                //treeCanvas.treeUpdate();
            }
            @Override
            public void elementToDeleteChosen(String element) {
                //tree.delete(tree.root,element);
                tree.draw();
            }
            @Override
            public void elementToSearchChosen(String element) {//TODO popup?//Paint different color
                tree.search(element);
                tree.draw();
            }
            @Override
            public void drawChosen() {tree.draw();}
        });

        container.add(sidePanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tree visualization");
        setMinimumSize(new Dimension(getPreferredSize().width,getPreferredSize().height+30));
        setVisible(true);
    }
}