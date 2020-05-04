import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MyMenu extends JMenuBar{
    JMenuItem mCircle;
    JMenuItem mRect;
    JMenuItem mTriangle;
    JFileChooser fileChooser;
    public MyMenu(MyCanvas c){
        c.setMenu(this);
        fileChooser = new JFileChooser();

        JMenu mFile = new JMenu("File");
        mFile.setMaximumSize(new Dimension(80, mFile.getPreferredSize().height));
        add(mFile);
        JMenuItem mSave = new JMenuItem ("Save");
        mFile.add(mSave);
        mSave.addActionListener(e -> {
            System.out.println(c.savingPaint());

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");

            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave.getAbsolutePath()));
                    writer.write(c.savingPaint());
                    writer.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        JMenuItem mOpen = new JMenuItem ("Open");
        mFile.add(mOpen);
        mOpen.addActionListener(e -> {
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                String filePath = selectedFile.getAbsolutePath();

                try {
                    String content = readFile(filePath);
                    c.shapesFromFile(content);
                } catch (IOException iOE) {

                }
            }
        });
        JMenuItem mExit = new JMenuItem ("Exit");
        mFile.add(mExit);

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
        mColor.setBackground(c.getColor());
        add(mColor);
        mColor.addActionListener(e -> {
            Color temp=JColorChooser.showDialog(null, "", c.getColor());
            if(temp!=null) {
                c.setColor(temp);
                mColor.setBackground(c.getColor());
            }
        });

        JMenuItem mInfo = new JMenuItem ("Info");
            mInfo.setMaximumSize(new Dimension(80, mInfo.getPreferredSize().height));
            add(mInfo);
            String message = "MyPaint\n"+
                             "Programing Course List 4 - create basic paint app \n"+
                             "Object oriented programing course at Wroclaw University of Science and Technology 2020    \n"+
                             "author:  Tobiasz Wojnar";
            mInfo.addActionListener(actionEvent -> JOptionPane.showMessageDialog(null, message,"Info",JOptionPane.INFORMATION_MESSAGE));

        mCircle.addActionListener(actionEvent -> {
            setMenuOption(mCircle);
            c.setOption('c');
        });
        mRect.addActionListener(actionEvent -> {
            setMenuOption(mRect);
            c.setOption('r');
        });
        mTriangle.addActionListener(actionEvent -> {
            setMenuOption(mTriangle);
            c.setOption('t');
        });
    }
    public void setMenuOption(JMenuItem mItem){
        mCircle.setEnabled(true);
        mRect.setEnabled(true);
        mTriangle.setEnabled(true);
        if(mItem!=null)
            mItem.setEnabled(false);
    }
    public static String readFile(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }
}
