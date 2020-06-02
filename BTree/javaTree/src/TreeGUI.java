import javax.swing.*;
import java.awt.*;

public class TreeGUI extends JFrame {
    TreeContainer treeContainer;
    GenericBinaryTree tree;
    public TreeGUI(GenericBinaryTree ntree) {
        this.tree=ntree;
        JPanel container = new JPanel();
        add(container);

        treeContainer = new TreeContainer(tree.root);
        container.add(treeContainer);

        SidePanel sidePanel = new SidePanel(tree,this);
        container.add(sidePanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tree visualization");
        setMinimumSize(new Dimension(getPreferredSize().width,getPreferredSize().height+30));
        setVisible(true);
    }
}