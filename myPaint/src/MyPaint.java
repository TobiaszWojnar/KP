import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
/*
2. Ekran miga przy przesuwaniu kursora.//TODO how???
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
    ShapesReaderWriter readerWriter;
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
        menu = new MyMenu();
        setJMenuBar(menu);

        readerWriter = new ShapesReaderWriter();

        canvas.setListener(new MyCanvas.Listener() {
            @Override
            public void onShapeFinished() {
                menu.setMenuOption(null);
            }

            @Override
            public void onColorChanged(Color color) {
                menu.setChooseColorOptionBackgroundColor(color);
            }
        });
        menu.setListener(new MyMenu.Listener() {
            @Override
            public void fileToSaveChosen(File file)  {
                try {
                    readerWriter.write(canvas.getShapes(), file);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            @Override
            public void fileToOpenChosen(File file) {//TODO better to make in canvas those methods: clear shapes, addShapes (zasada demeter toread)
                try {
                    List<MyShape> shapes = readerWriter.read(file);
                    canvas.getShapes().removeAll(canvas.getShapes());
                    canvas.getShapes().addAll(shapes);
                    canvas.repaint();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fileToAddChosen(File file) {
                try {
                    List<MyShape> shapes = readerWriter.read(file);
                    canvas.getShapes().addAll(shapes);
                    canvas.repaint();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void circleSelected() {
                canvas.setAppState('c');
            }

            @Override
            public void rectangleSelected() {
                canvas.setAppState('r');
            }

            @Override
            public void triangleSelected() {
                canvas.setAppState('t');
            }

            @Override
            public void colorChosen(Color color) {
                canvas.setColor(color);
            }
        });
    }
}