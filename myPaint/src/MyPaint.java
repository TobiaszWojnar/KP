import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class of application
 * simple graphic editor enabling to create shapes, changing their color, modifying their placement and size on canvas.
 *
 * @author Tobiasz Wojnar
 * Java Programing Course 2020 list 4
 * @version 2.0
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
     * consists of menu, canvas, readerWriter and listeners for menu and canvas
     * @see MyMenu
     * @see MyCanvas
     * @see ShapesReaderWriter
     */
    public MyPaint(){

        setSize(640 , 480);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        canvas = new MyCanvas();
        add(canvas);
        menu = new MyMenu();
        setJMenuBar(menu);

        readerWriter = new ShapesReaderWriter();

        canvas.setListener(new MyCanvas.Listener() {
            /**
             * once drawing shape is finished or canceled updates menu state
             * @see MyCanvas
             */
            @Override
            public void onShapeFinished() {
                menu.setMenuOption(null);
            }

            /**
             * once new color is chosen updates color of color choosing menu item
             * @param color new color
             * @see MyCanvas
             */
            @Override
            public void onColorChanged(Color color) {
                menu.setChooseColorOptionBackgroundColor(color);
            }
        });
        menu.setListener(new MyMenu.Listener() {
            /**
             * forwards chosen output for canvas items (shapes) to writer (readWriter)
             * @param file output file
             * @see MyShape
             * @see ShapesReaderWriter
             * @see MyMenu
             */
            @Override
            public void fileToSaveChosen(File file)  {
                try {
                    readerWriter.write(canvas.getShapes(), file);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            /**
             * forwards chosen input file  to reader (readWriter)
             * it differs form fileToAddChosen by clearing list of canvas shapes before adding new from file
             * @param file input file
             * @see ShapesReaderWriter
             * @see MyMenu
             * @see MyCanvas
             */
            @Override
            public void fileToOpenChosen(File file) {
                try {
                    List<MyShape> shapes = readerWriter.read(file);
                    canvas.clearShapes();
                    canvas.addShapes((ArrayList<MyShape>) shapes);
                    canvas.repaint();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /**
             * forwards chosen input file to reader (readWriter)
             * it differs form fileToOpenChosen by not clearing list of canvas shapes before adding new from file
             * @param file input file
             * @see ShapesReaderWriter
             * @see MyMenu
             * @see MyCanvas
             */
            @Override
            public void fileToAddChosen(File file) {
                try {
                    List<MyShape> shapes = readerWriter.read(file);
                    canvas.addShapes((ArrayList<MyShape>) shapes);
                    canvas.repaint();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            /**
             * sets appState to c
             * @see MyCanvas
             * @see MyCanvas.States
             */
            @Override
            public void circleSelected() {
                canvas.setAppState(MyCanvas.States.c);
            }
            /**
             * sets appState to r
             * @see MyCanvas
             * @see MyCanvas.States
             */
            @Override
            public void rectangleSelected() {
                canvas.setAppState(MyCanvas.States.r);
            }
            /**
             * sets appState to t
             * @see MyCanvas
             * @see MyCanvas.States
             */
            @Override
            public void triangleSelected() {
                canvas.setAppState(MyCanvas.States.t);
            }
            /**
             * sets canvas activeColor
             * @param newColor updates canvas activeColor
             * @see MyCanvas
             * @see MyCanvas
             */
            @Override
            public void colorChosen(Color newColor) {
                canvas.setColor(newColor);
            }
        });
    }
}