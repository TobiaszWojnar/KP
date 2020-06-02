import javax.swing.*;

class SidePanel extends JPanel {
    String[] labelsName = {"Insert","Search", "Delete"};
    JButton[] buttons = new JButton[labelsName.length];
    JTextField[] textFields = new JTextField[labelsName.length];

    public SidePanel(GenericBinaryTree tree, TreeGUI treeGUI){
        for(int i=0; i < 3; i++ ){
            textFields[i] = new JTextField();
            add(textFields[i]);
            buttons[i] = new JButton(labelsName[i]);
            add(buttons[i]);
        }
        buttons[0].addActionListener(actionEvent -> {
            tree.insert(Integer.parseInt(textFields[0].getText()));
            treeGUI.repaint();
            tree.draw();
        });
        buttons[1].addActionListener(actionEvent -> {
            tree.search(Integer.parseInt(textFields[1].getText()));//TODO popup?
            tree.draw();
        });
        buttons[2].addActionListener(actionEvent -> {
            tree.delete(Integer.parseInt(textFields[2].getText()));
            tree.draw();
        });
        setLayout(new BoxLayout(this,1));
    }
}