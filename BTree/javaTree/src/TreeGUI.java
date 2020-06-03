import javax.swing.*;
import java.awt.*;


public class TreeGUI extends JFrame {
    TreePainter treePainter;
    GenericBinaryTree tree;
    MyClient client;

    public TreeGUI(GenericBinaryTree ntree) {
        //client=new MyClient();

        this.tree=ntree;
        JPanel container = new JPanel();
        add(container);
//ma mieć clienta
        treePainter = new TreePainter(tree.root);
        container.add(treePainter);

        SidePanel sidePanel = new SidePanel();
        sidePanel.setListener(new SidePanel.Listener() {
            @Override
            public void typeChosen(String type) {
                tree.draw();//TODO jeśli brak zmian to serwer nic nie wysyła
            }//TODO if changes delete tree and make new
            @Override
            public void elementToInsertChosen(String element) {
                tree.insert(element);//TODO where to cast //cast on server
                tree.draw();
                //client.insert(element);
            }
            @Override
            public void elementToDeleteChosen(String element) {
                //tree.delete(tree.root,element);//TODO where to cast
                tree.draw();
            }
            @Override
            public void elementToSearchChosen(String element) {//TODO popup?//Paint different color
                tree.search(element);//TODO where to cast
                tree.draw();
            }
            @Override
            public void drawChosen() {tree.draw();}
        });

        //client.listener
        //treePinter.update(repaintTree);
        container.add(sidePanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tree visualization");
        setMinimumSize(new Dimension(getPreferredSize().width,getPreferredSize().height+30));
        setVisible(true);
    }
}