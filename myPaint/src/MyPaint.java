import javax.swing.*;

public class MyPaint extends JFrame{
    public static void main (String[] args){
        MyPaint app = new MyPaint();
        app.setVisible(true);
    }
    public MyPaint(){
        setSize(960 , 540);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //TODO nice panel for choosing shapes and color

        MyCanvas c = new MyCanvas();
        add(c);

        MyMenu menu = new MyMenu(c);
        setJMenuBar(menu);

    }
}
