import javax.swing.*;
import java.awt.*;

class SidePanel extends JPanel {
    private final String[] labelsName = {"Insert","Search", "Delete","Draw"};
    private final JTextField[] textFields = new JTextField[labelsName.length-1];
    private Listener listener;

    public SidePanel(){

        //TODO make adding buttons nicer
        String[] typeList = {"Integer", "Double", "Char", "String"};
        JComboBox<String> typeChooser = new JComboBox<>(typeList);
        add(typeChooser);

        JButton[] buttons = new JButton[labelsName.length];
        for(int i = 0; i < labelsName.length-1; i++ ){
            textFields[i] = new JTextField();
            add(textFields[i]);
            buttons[i] = new JButton(labelsName[i]);
            add(buttons[i]);
        }
        buttons[3] = new JButton(labelsName[3]);
        add(buttons[3]);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//TODO make nicer
        setPreferredSize(new Dimension(getPreferredSize().width,getPreferredSize().height));

        //TODO change listeners to set actions later?
        typeChooser.addActionListener(actionEvent ->{
            listener.typeChosen((String) typeChooser.getSelectedItem());
        });
        buttons[0].addActionListener(e -> {
            listener.elementToInsertChosen(textFields[0].getText());
            textFields[0].setText("");
            textFields[0].requestFocus();
        });
        buttons[1].addActionListener(actionEvent -> {
            listener.elementToSearchChosen(textFields[1].getText());
            textFields[1].setText("");
            textFields[1].requestFocus();
        });
        buttons[2].addActionListener(actionEvent -> {
            listener.elementToDeleteChosen(textFields[2].getText());
            textFields[2].setText("");
            textFields[2].requestFocus();
        });
        buttons[3].addActionListener(actionEvent -> {
            listener.drawChosen();
        });
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }
    public interface Listener {
        void typeChosen (String type);
        void elementToInsertChosen(String element);
        void elementToDeleteChosen(String element);
        void elementToSearchChosen(String element);
        void drawChosen();
    }
}