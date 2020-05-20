import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Menu class extends JMenuBar
 * enables:
 *      * saving and opening files
 *      * choosing what figure we want to create (Circle, Rectangle, Triangle)
 *      * choosing color
 *      * shows popup window with info and instruction how to use application
 *
 * Has listener for actions connect with buttons
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
     * adds buttons for:
     *      * saving and opening files
     *      * choosing what figure we want to create (Circle, Rectangle, Triangle)
     *      * choosing color
     *      * shows popup window with info and instruction how to use application
     *
     * sets color of colorChoosingOption to BLACK
     */
    public MyMenu(){
        fileChooser = new JFileChooser();
        previousColor = Color.BLACK;

        JMenu mFile = new JMenu("File");
        mFile.setMaximumSize(new Dimension(60, mFile.getPreferredSize().height));
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
        mCircle.setMaximumSize(new Dimension(60, mCircle.getPreferredSize().height));
        add(mCircle);
        mCircle.addActionListener(actionEvent -> {
            setMenuOption(mCircle);
            listener.circleSelected();
        });

        mRect = new JMenuItem ("Rectangle");
        mRect.setMaximumSize(new Dimension(60, mRect.getPreferredSize().height));
        add(mRect);
        mRect.addActionListener(actionEvent -> {
            setMenuOption(mRect);
            listener.rectangleSelected();
        });

        mTriangle = new JMenuItem("Triangle");
        mTriangle.setMaximumSize(new Dimension(60, mTriangle.getPreferredSize().height));
        add(mTriangle);
        mTriangle.addActionListener(actionEvent -> {
            setMenuOption(mTriangle);
            listener.triangleSelected();
        });

        mColor = new JMenuItem ("Color");
        mColor.setMaximumSize(new Dimension(60, mColor.getPreferredSize().height));
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
        mInfo.setMaximumSize(new Dimension(20, mInfo.getPreferredSize().height));
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
        mHelp.setPreferredSize(new Dimension(140, mHelp.getPreferredSize().height));
        add(mHelp);
        String message1 = "MyPaint\n"+
                "1. Creating figures \n"+
                "\tChoose one of 3 shape types from menu: Circle, Rectangle, Triangle.\n"+
                "\tThen by left clicking on our canvas create shapes. After being created figure will be set to active.\n"+
                " If you want to stop simply right click\n"+
                "2. Modifying figures\n"+
                "\tBy clicking on a shape you selected it as your active figure.\n"+
                "If you right clicked a color menu will open for changing it's color.\n"+
                "You can then scale your figure by moving mouse wheel or by left clicking inside active shape you might move it.\n"+
                "To accept movement left click, by right clicking you will reset shape to its original location.";
        mHelp.addActionListener(actionEvent -> {
            JOptionPane.showMessageDialog(this, message1, "Info", JOptionPane.INFORMATION_MESSAGE);
            setMenuOption(null);
        });
    }

    /**
     * @param listener to be set
     */
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

    /**
     * Updates color and changes background color of menuItem responsible for choosing color
     * @param color that updates previousColor
     */
    public void setChooseColorOptionBackgroundColor(Color color){
        previousColor=color;
        mColor.setBackground(color);
    }

    /**
     * Listener for choosing menuItems, specifically:
     *      * saving and opening files
     *      * choosing what figure we want to create (Circle, Rectangle, Triangle)
     *      * choosing color
     */
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