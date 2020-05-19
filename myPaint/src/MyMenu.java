import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Menu class extends JMenuBar
 * changes type of figure that can be created
 * enables saving and opening files
 */
public class MyMenu extends JMenuBar{
    private final JMenuItem mCircle;
    private final JMenuItem mRect;
    private final JMenuItem mTriangle;
    private final JMenuItem mColor;
    private final JFileChooser fileChooser;
    private Listener listener;
    private Color previousColor;

    /**
     * Constructor of menu
     */
    public MyMenu(){
        fileChooser = new JFileChooser();
        previousColor = Color.BLACK;

        JMenu mFile = new JMenu("File");
        mFile.setMaximumSize(new Dimension(80, mFile.getPreferredSize().height));
        add(mFile);
        JMenuItem mSave = new JMenuItem ("Save");
        mFile.add(mSave);
        mSave.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");

            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                listener.fileToSaveChosen(fileToSave);
            }
        });

        JMenuItem mOpen = new JMenuItem ("Open");
        mFile.add(mOpen);
        mOpen.addActionListener(e -> {
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                listener.fileToOpenChosen(selectedFile);
            }
        });

        JMenuItem mAdd = new JMenuItem ("Add from file");
        mFile.add(mAdd);
        mAdd.addActionListener(e -> {
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                listener.fileToAddChosen(selectedFile);
            }
        });

        mCircle = new JMenuItem ("Circle");
        mCircle.setMaximumSize(new Dimension(80, mCircle.getPreferredSize().height));
        add(mCircle);
        mCircle.addActionListener(actionEvent -> {
            setMenuOption(mCircle);
            listener.circleSelected();
        });

        mRect = new JMenuItem ("Rectangle");
        mRect.setMaximumSize(new Dimension(80, mRect.getPreferredSize().height));
        add(mRect);
        mRect.addActionListener(actionEvent -> {
            setMenuOption(mRect);
            listener.rectangleSelected();
        });

        mTriangle = new JMenuItem("Triangle");
        mTriangle.setMaximumSize(new Dimension(80, mTriangle.getPreferredSize().height));
        add(mTriangle);
        mTriangle.addActionListener(actionEvent -> {
            setMenuOption(mTriangle);
            listener.triangleSelected();
        });

        mColor = new JMenuItem ("Color");
        mColor.setMaximumSize(new Dimension(80, mColor.getPreferredSize().height));
        add(mColor);
        mColor.setBackground(previousColor);
        mColor.addActionListener(e -> {
            Color temp=JColorChooser.showDialog(this, "Color Chooser", previousColor);
            if(temp!=null) {
                listener.colorChosen(temp);
                mColor.setBackground(temp);
            }
        });

        JMenuItem mInfo = new JMenuItem ("Info");
        mInfo.setMaximumSize(new Dimension(80, mInfo.getPreferredSize().height));
        add(mInfo);
        String message = "MyPaint\n"+
                         "Programing Course List 4 - create basic paint app \n"+
                         "Object oriented programing course at Wroclaw University of Science and Technology 2020    \n"+
                         "author:  Tobiasz Wojnar";
        mInfo.addActionListener(actionEvent -> {
            JOptionPane.showMessageDialog(this, message,"Info",JOptionPane.INFORMATION_MESSAGE);
            setMenuOption(null);
        });

        JMenuItem mHelp = new JMenuItem ("Help - how to use");
        mHelp.setMaximumSize(new Dimension(160, mInfo.getPreferredSize().height));
        add(mHelp);
        String message1 = "MyPaint\n"+
                "1. Creating figures \n"+
                "\tChoose one of 3 shape types from menu: Circle, Rectangle, Triangle.\n"+
                "\tThen by left clicking on our canvas create shapes. If you want to stop simply right click\n"+
                "2. Modifying figures\n"+
                "\tBy right clicking on a shape you selected it as your active figure and a color menu will open for changing it's color.\n"+
                "After closing menu you can scale your figure by moving mouse wheel or by left clicking inside active shape you might move it.\n"+
                "To accept movement left click, by right clicking you will reset shape to its original location.";
        mHelp.addActionListener(actionEvent -> {
            JOptionPane.showMessageDialog(this, message1, "Info", JOptionPane.INFORMATION_MESSAGE);
            setMenuOption(null);
        });
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    /**
     * Changes selected option in menu
     * @param mItem item that is selected
     */
    public void setMenuOption(JMenuItem mItem){
        mCircle.setEnabled(true);
        mRect.setEnabled(true);
        mTriangle.setEnabled(true);
        if(mItem!=null)
            mItem.setEnabled(false);
    }
    public void setChooseColorOptionBackgroundColor(Color color){
        previousColor=color;
        mColor.setBackground(color);
    }

    public interface Listener {
        void fileToSaveChosen (File file);
        void fileToOpenChosen (File file);
        void fileToAddChosen (File file);
        void circleSelected ();
        void rectangleSelected ();
        void triangleSelected ();
        void colorChosen (Color color);
    }
}