import java.util.Arrays;

/**
 * @author Tobiasz Wojnar
 * Java Programing Course 2020 list 3 exercise 1
 *
 * Calculate surface area and circumference of:
     * circle, quadrangle (square, rectangle, rhombus), regular pentagon, regular hexagon
 * implement hierarchy and abstract class Figure
 * handle exeptions and bad data
 *
 * parameters first letter of figure (char) and parameters (double)
 *  > for circle			    - radius
 *  > for quadrangle			- 4 sides and angle
 *  > for pentagon and hexagon	- side
 *  > eg.: java figures q 8 8 4 4 90
 */

public class Figury {
    /**
     * Calculate surface area and circumference of:
     * circle, quadrangle (square, rectangle, rhombus), regular pentagon, regular hexagon
     * for no, invalid or wrong number of parameters program will print type of error and stop
     *
     * @param args first letter of figure (char type) and parameters (double type)
     *             >	for circle			        - radius
     *             >	for quadrangle			    - 4 sides and angle
     *             >	for pentagon and hexagon	- side
     *             > eg.: java Figures q 8 8 4 4 90
     */
    public static void main(String[] args) {
        try {
            String typeFigure = isFigure(args);
            double[] parameter;
            if (!typeFigure.equals("0")) {
                parameter = hasValidArgs(args);
                if (parameter != null) {
                    System.out.println(calculate(typeFigure, parameter));
                }
            }
        } catch (IllegalArgumentException | IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * checks if the type of figure is correct
     * @param args Program arguments first string should be a type of figure {c,q,p,h}
     * @return type of figure {"c","q","p","h"}, if any error returns "0"
     */
    public static String isFigure(String[] args) {
        if (args.length > 0) {
            switch (args[0]){
                case "c":
                case "p":
                case "h":
                case "q":
                    return args[0];
                default:
                    throw new IllegalArgumentException("Shape '"+ args[0] + "' does not exist");
            }
        } else {
            throw new IndexOutOfBoundsException("No parameters");
        }
    }

    /**
     * checks if program arguments for constructing figures are correct
     * @param args Program arguments, first string should be a type of figure, rest should be numbers
     * @return array of numbers - parameters for constructing figures, in case of any error returns null;
     */
    public static double[] hasValidArgs(String[] args) {
        int len = args.length - 1;
        double[] param = new double[len];
        //checks if all arguments are positive numbers
        for (int i = 0; i < len; i++) {
            try {
                param[i] = Double.parseDouble(args[i + 1]);
                if (param[i] < 0){
                    throw new IllegalArgumentException(param[i] + " is not positive number");
                }
            } catch (NumberFormatException ex) {
                return null;
            }
        }
        switch (args[0]) {
            case "c":
            case "p":
            case "h":
                if (rightNrOfAra(args[0],param)) { //checks for right number of parameters
                    return param;
                }
            case "q":
                if (rightNrOfAra(args[0],param)) { //checks for right number of parameters
                    if (param[0] != param[1] || param[2] != param[3]) { //checks if its parallelogram
                        throw new IllegalArgumentException(Arrays.toString(param) + " can't create this parallelogram");
                    }
                    if (param[4] > 180) {   //assures that angle is obtuse, right or acute
                        throw new IllegalArgumentException(param[4] + " angle to big");
                    }
                    if (param[0] != param[2] && param[4] != 90) {   //if sides are not the same length it needs to be rectangle
                        throw new IllegalArgumentException(Arrays.toString(param) + " can't create this parallelogram");
                    }
                    return param;
                }
            default:
                throw new IllegalArgumentException("Shape '"+ args[0] + "' does not exist");
        }
    }
    public static boolean rightNrOfAra (String fType, double[] args) {
        switch (fType) {
            case "c":
            case "p":
            case "h":
                if(args.length == 1){
                    return true;
                } else {
                    throw new IndexOutOfBoundsException(Arrays.toString(args) + " wrong number of parameters");
                }
            case "q":
                if(args.length == 4){
                    return true;
                } else {
                    throw new IndexOutOfBoundsException(Arrays.toString(args) + " wrong number of parameters");
                }
            default:
                throw new IllegalArgumentException("Shape '"+ fType + "' does not exist");
        }
    }
    /**
     * calculates surface area and circumference of figure
     * @param fType type of figure {"c","q","p","h"}
     * @param arg array of positive numbers that are parameters for figures
     * @return String with calculated surface area and circumference of figure
     */
    public static String calculate(String fType, double[] arg) {
        Figure f;
        if (rightNrOfAra(fType, arg)) {
            switch (fType) {
                case "c":
                    f = new Circle(arg[0]);
                    break;
                case "p":
                    f = new Pentagon(arg[0]);
                    break;
                case "h":
                    f = new Hexagon(arg[0]);
                    break;
                case "q":
                    if (arg[4] != 90) {
                        f = new Rhombus(arg[0], arg[4]);
                    } else if (arg[0] != arg[2]) {
                        f = new Rectangle(arg[0], arg[2]);
                    } else {
                        f = new Square(arg[0]);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Shape '" + fType + "' does not exist");
            }
            return "surface = " + f.surface() + "\ncircumference = " + f.circumference();
        }
        else
            return null;
    }
}
