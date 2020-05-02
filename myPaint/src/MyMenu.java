import javax.swing.*;
import java.awt.*;

public class MyMenu extends JMenuBar{
    JMenuItem mCircle;
    JMenuItem mRect;
    JMenuItem mTriangle;
    public MyMenu(MyCanvas c){
        JMenu mFile = new JMenu("File");
        mFile.setMaximumSize(new Dimension(80, mFile.getPreferredSize().height));
        add(mFile);
        JMenuItem mSave = new JMenuItem ("Save");
        mFile.add(mSave);
        JMenuItem mSaveImg = new JMenuItem ("Save as JPG");
        mFile.add(mSaveImg);
        JMenuItem mOpen = new JMenuItem ("Open");
        mFile.add(mOpen);
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
            c.setColor(JColorChooser.showDialog(null, "", c.getColor()));
            mColor.setBackground(c.getColor());
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
            setMenu(mCircle);
            c.setOption('c');
        });
            mRect.addActionListener(actionEvent -> {
            setMenu(mRect);
            c.setOption('r');
        });
            mTriangle.addActionListener(actionEvent -> {
            setMenu(mTriangle);
            c.setOption('t');
        });
    }
    public void setMenu(JMenuItem mItem){
        mCircle.setEnabled(true);
        mRect.setEnabled(true);
        mTriangle.setEnabled(true);
        mItem.setEnabled(false);
    }
}
