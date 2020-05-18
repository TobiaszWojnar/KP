import javax.swing.*;
/*
1. Źle działa przenoszenie figur//Done? czy chodziło o to, że na początku figura mogła przeskakiwać?
2. Ekran miga przy przesuwaniu kursora.//TODO how???
3. Żeby figura była aktywna, to muszę zmienić kolor.//done?
4. Wczytywanie pliku źle działa.//Done?

do 20.05
//TODO left click making active figure
 */
/**
 * Main class of application
 * simple graphic editor enabling to create shapes, changing their color, modifying their placement and size on canvas.
 *
 * @author Tobiasz Wojnar
 * Java Programing Course 2020 list 4
 */
public class MyPaint extends JFrame{
    /**
     * Main class opens application
     */
    public static void main (String[] args){
        MyPaint app = new MyPaint();
        app.setVisible(true);
    }
    MyMenu menu;
    MyCanvas canvas;
    /**
     * MyPaint extends JFrame
     * has menu and canvas
     * @see MyMenu
     * @see MyCanvas
     */
    public MyPaint(){
        setSize(960 , 540);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new MyCanvas();
        add(canvas);

        menu = new MyMenu(this);
        setJMenuBar(menu);
    }

    /**
     * @return returns canvas
     */
    public MyCanvas getCanvas() {
        return canvas;
    }
}