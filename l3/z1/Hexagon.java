/**
 * @author Tobiasz Wojnar
 * Java Programing Course 2020
 * list 3 exercise 1
 * Calculate surface area and circumference of:
 * circle, quadrangle (square, rectangle, rhombus), regular pentagon, regular hexagon
 * implement hierarchy and abstract class Figure 
 * handle exeptions and bad data
 *
 * @input first letter of figure and paramethers 
 *	for circle			- radious
 *	for quadrangle			- 4 sides and angle
 *	for pentagon and hexagon	- side
 * eg.: java figures q 8 8 4 4 90
 **/
public class Hexagon extends Figure {
    double side;
    public Hexagon (double side){
        this.side=side;
    }
    public double Surface (){
        double value = side*side*Math.sqrt(27);
        return value;
    }
    public double Circumference (){
        return 6*side;
    }
}