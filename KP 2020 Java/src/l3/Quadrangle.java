package l3;

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

public abstract class Quadrangle extends Figure {
    double[] sides = new double[4];
    double angle;
    public Quadrangle (double side1, double side2, double side3, double side4, double angle){
        sides[0]=side1;
        sides[1]=side2;
        sides[2]=side3;
        sides[3]=side4;
        this.angle=angle;
    }
    public double Circumference () {
        return sides[0]+sides[1]+sides[2]+sides[3];
    }
}
