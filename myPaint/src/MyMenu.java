import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Menu class extends JMenuBar
 * changes type of figure that can be created
 * enables saving and opening files
 */
public class MyMenu extends JMenuBar{
    JMenuItem mCircle;
    JMenuItem mRect;
    JMenuItem mTriangle;
    JFileChooser fileChooser;

    /**
     * Constructor of menu
     * @param myPaint pointer to application
     */
    public MyMenu(MyPaint myPaint){
        myPaint.getCanvas().setMenu(this);
        fileChooser = new JFileChooser();

        JMenu mFile = new JMenu("File");
        mFile.setMaximumSize(new Dimension(80, mFile.getPreferredSize().height));
        add(mFile);
        JMenuItem mSave = new JMenuItem ("Save");
        mFile.add(mSave);
        mSave.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");

            if (fileChooser.showSaveDialog(myPaint) == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave.getAbsolutePath()));
                    writer.write(myPaint.getCanvas().savingPaint());
                    writer.close();
                } catch (IOException ignored) {
                }
            }
        });

        JMenuItem mOpen = new JMenuItem ("Open");
        mFile.add(mOpen);
        mOpen.addActionListener(e -> {
            int result = fileChooser.showOpenDialog(myPaint);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                String filePath = selectedFile.getAbsolutePath();
                try {
                    String content = readFile(filePath);
                    myPaint.getCanvas().shapesFromFile(content);
                } catch (IOException ignored) {
                }
            }
        });

        mCircle = new JMenuItem ("Circle");
        mCircle.setMaximumSize(new Dimension(80, mCircle.getPreferredSize().height));
        add(mCircle);

        mRect = new JMenuItem ("Rectangle");
        mRect.setMaximumSize(new Dimension(80, mRect.getPreferredSize().height));
        add(mRect);

        mTriangle = new JMenuItem("Triangle");
        mTriangle.setMaximumSize(new Dimension(80, mTriangle.getPreferredSize().height));
        add(mTriangle);

        JMenuItem mColor = new JMenuItem ("Color");
        mColor.setMaximumSize(new Dimension(80, mColor.getPreferredSize().height));
        mColor.setBackground(myPaint.getCanvas().getColor());
        add(mColor);
        mColor.addActionListener(e -> {
            Color temp=JColorChooser.showDialog(myPaint, "", myPaint.getCanvas().getColor());
            if(temp!=null) {
                myPaint.getCanvas().setColor(temp);
                mColor.setBackground(myPaint.getCanvas().getColor());
            }
        });

        JMenuItem mInfo = new JMenuItem ("Info");
        mInfo.setMaximumSize(new Dimension(80, mInfo.getPreferredSize().height));
        add(mInfo);
        String message = "MyPaint\n"+
                         "Programing Course List 4 - create basic paint app \n"+
                         "Object oriented programing course at Wroclaw University of Science and Technology 2020    \n"+
                         "author:  Tobiasz Wojnar";
        mInfo.addActionListener(actionEvent -> JOptionPane.showMessageDialog(myPaint, message,"Info",JOptionPane.INFORMATION_MESSAGE));

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
        mHelp.addActionListener(actionEvent -> JOptionPane.showMessageDialog(myPaint, message1,"Info",JOptionPane.INFORMATION_MESSAGE));

        mCircle.addActionListener(actionEvent -> {
            setMenuOption(mCircle);
            myPaint.getCanvas().setAppState('c');
        });
        mRect.addActionListener(actionEvent -> {
            setMenuOption(mRect);
            myPaint.getCanvas().setAppState('r');
        });
        mTriangle.addActionListener(actionEvent -> {
            setMenuOption(mTriangle);
            myPaint.getCanvas().setAppState('t');
        });
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
     * Reads form file
     * @param path path to file
     * @return file content
     * @throws IOException if file is missing
     */
    public static String readFile(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }
}
