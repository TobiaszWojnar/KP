import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for reading and writing from and to files
 */
public class ShapesReaderWriter {
    /**
     * Writes content of canvas to files
     * @param shapes list of shapes <MyShape>
     * @param file chosen to write in
     * @throws IOException if problem with file
     *
     * @see MyShape
     */
    void write(List<MyShape> shapes, File file) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
        writer.write(savingPaint(shapes));
        writer.close();
    }

    /**
     * Creates string of parameters of shapes from canvas
     * @param shapes list of shapes <MyShape>
     * @return concatenated parameters of all shapes, every shape in next line
     * Format for returning (parameters separated by tabs):
     *      * Upper case letter for determining what type of figure is it ('C', 'R', 'T')
     *      * Points and dimensions
     *      * Color as 3 integers for RGB (0-255)
     * @see MyShape
     */
    private String savingPaint(List<MyShape> shapes){
        StringBuilder result = new StringBuilder();
        for(MyShape s:shapes){
            result.append(s.toFile());
        }
        return result.toString();
    }

    /**
     * Reads content file, formats it to extract list of shapes
     * @param file form which reads
     * @return list of shapes form file
     * @throws IOException if problem with file
     */
    List<MyShape> read (File file) throws IOException{
        String filePath = file.getAbsolutePath();
        String content = readFile(filePath);
        return readShapesFromFile(content);
    }

    /**
     * Reads content of file
     * @param path of file
     * @return content of file
     * @throws IOException if problem with file
     */
    private String readFile(String path) throws IOException {
        return Files.readString(Paths.get(path));
    }

    /**
     * Extracts list of shapes from string
     * @param data string of parameters of shapes
     * @return list of shapes
     * @throws IOException if data badly encoded
     * @see MyShape
     */
    private List<MyShape> readShapesFromFile (String data) throws IOException {
        String [] newData = data.split("\n");
        String[] shapeData;
        List<MyShape> shapes = new ArrayList<>();

        for(String s: newData){
            shapeData = s.split("\t");
            switch (shapeData[0]) {
                case "C":
                    if (shapeData.length == 7) {
                        Circle c = new Circle(
                                new Point(Integer.parseInt(shapeData[1]), Integer.parseInt(shapeData[2])),
                                Double.parseDouble(shapeData[3]),
                                new Color(Integer.parseInt(shapeData[4]), Integer.parseInt(shapeData[5]), Integer.parseInt(shapeData[6])));
                        shapes.add(c);
                    } else
                        throw new IOException();
                    break;
                case "R":
                    if (shapeData.length == 8) {
                        MyRectangle r = new MyRectangle(
                                new Point(Integer.parseInt(shapeData[1]), Integer.parseInt(shapeData[2])),
                                new Point(Integer.parseInt(shapeData[3]), Integer.parseInt(shapeData[4])),
                                new Color(Integer.parseInt(shapeData[5]), Integer.parseInt(shapeData[6]), Integer.parseInt(shapeData[7])));
                        shapes.add(r);
                    } else
                        throw new IOException();
                    break;
                case "T":
                    if (shapeData.length == 10) {
                        Triangle t = new Triangle(
                                new int[]{Integer.parseInt(shapeData[1]), Integer.parseInt(shapeData[2]), Integer.parseInt(shapeData[3])},
                                new int[]{Integer.parseInt(shapeData[4]), Integer.parseInt(shapeData[5]), Integer.parseInt(shapeData[6])},
                                new Color(Integer.parseInt(shapeData[7]), Integer.parseInt(shapeData[8]), Integer.parseInt(shapeData[9])));
                        shapes.add(t);
                    } else
                        throw new IOException();
                    break;
                default:
                    throw new IOException();
            }
        }
        return shapes;
    }
}