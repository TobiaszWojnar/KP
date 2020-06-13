import javax.swing.*;
import java.awt.*;

/**
 * The <code>SidePanel</code> class is panel enabling modifications of {@link GenericBinaryTree binary tree}.
 * It is exercise for object oriented programing course at Wroclaw University of Science and Technology.
 * The objective of this list was to implement binary search tree with generic type keys,
 * create {@link TreeGUI GUI  application} and implement {@link MyClient client} {@link MyServer server} architecture.
 * <p>
 *      <code>SidePanel</code> extends {@link JPanel} has buttons for changing type of key in {@code GenericBinaryTree binary tree}
 *      and performing "Insert","Search", "Delete" and "Draw" actions.
 * <p/>
 * @version     09 June 2020
 * @author      Tobiasz Wojnar
 * @see         TreeGUI
 * @see         MyServer
 */
class SidePanel extends JPanel {
    private final String[] labelsName = {"Insert","Search", "Delete","Draw"};
    private final JTextField[] textFields = new JTextField[labelsName.length-1];
    private Listener listener;

    /**
     * <code>SidePanel</code> extends {@link JPanel} has buttons for changing type of key in <code>GenericBinaryTree</code>
     * and performing "Insert","Search", "Delete" and "Draw" actions.
     * @param keyTypes types of key in <code>GenericBinaryTree</code>
     * @see GenericBinaryTree
     */
    public SidePanel(String[] keyTypes){
        Dimension myDimension = new Dimension(120, 25);

        JComboBox<String> typeChooser = new JComboBox<>(keyTypes);
        typeChooser.setMaximumSize(myDimension);
        add(typeChooser);

        JButton[] buttons = new JButton[labelsName.length];
        for(int i = 0; i < labelsName.length-1; i++ ){
            textFields[i] = new JTextField();
            textFields[i].setMaximumSize(myDimension);
            add(textFields[i]);
            buttons[i] = new JButton(labelsName[i]);
            buttons[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            buttons[i].setMaximumSize(myDimension);
            add(buttons[i]);
        }
        buttons[3] = new JButton(labelsName[3]);
        buttons[3].setMaximumSize(myDimension);
        buttons[3].setAlignmentX(Component.CENTER_ALIGNMENT);
        add(buttons[3]);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        typeChooser.addActionListener(actionEvent ->
            listener.typeChosen((String) typeChooser.getSelectedItem())
        );
        buttons[0].addActionListener(e -> {
            if(!textFields[0].getText().equals("")) {
                listener.elementToInsertChosen(textFields[0].getText());
                textFields[0].setText("");
                textFields[0].requestFocus();
            }
        });
        buttons[1].addActionListener(actionEvent -> {
            if(!textFields[1].getText().equals("")) {
                listener.elementToSearchChosen(textFields[1].getText());
                textFields[1].setText("");
                textFields[1].requestFocus();
            }
        });
        buttons[2].addActionListener(actionEvent -> {
            if(!textFields[2].getText().equals("")) {
                listener.elementToDeleteChosen(textFields[2].getText());
                textFields[2].setText("");
                textFields[2].requestFocus();
            }
        });
        buttons[3].addActionListener(actionEvent -> listener.drawChosen());
    }

    /**
     * @param listener to be set
     */
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    /**
     * Listener for receiving actions chosen in {@link SidePanel}
     */
    public interface Listener {
        /**
         * On type changed in {@link SidePanel}.
         * @param type Type of key in {@link GenericBinaryTree tree}.
         */
        void typeChosen (String type);
        /**
         * On insert request.
         * @param element Key to insert in {@link GenericBinaryTree tree}.
         * @see SidePanel
         */
        void elementToInsertChosen(String element);
        /**
         * On delete request.
         * @param element Key to delete from {@link GenericBinaryTree tree}.
         * @see SidePanel
         */
        void elementToDeleteChosen(String element);
        /**
         * On search request.
         * @param element Key to search in {@link GenericBinaryTree tree}.
         * @see SidePanel
         */
        void elementToSearchChosen(String element);
        /**
         * On draw request.
         * @see SidePanel
         */
        void drawChosen();
    }
}