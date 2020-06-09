import javax.swing.*;
import java.awt.*;
//TODO documentation
class SidePanel extends JPanel {
    private final String[] labelsName = {"Insert","Search", "Delete","Draw"};
    private final JTextField[] textFields = new JTextField[labelsName.length-1];
    private Listener listener;

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