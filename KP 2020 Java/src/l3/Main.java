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
 * eg.: java figures q 8 8 4 4 90 //for now its going to be java main q 8 8 4 4 90
 **/

public class Main {
    public static void main (String[] args){
        //Without arguments program will do nothing
        if(args.length!=0){
            double n;
            switch (args[0]){
                case "c":
                    if(args.length==2){
                        try{
                            n = Double.parseDouble(args[1]);
                            if(n>0) {
                                Circle c = new Circle(n);
                                System.out.println("surface = " + c.Surface() + " circumference = " +c.Circumference());
                            } else {
                                System.out.println(n + " - wrong range");
                            }
                        }catch (NumberFormatException ex) {
                            System.out.println(args[1] + " - not a number");
                        }
                    } else
                        System.out.println("wrong number of arguments");
                    break;
                case "p":
                    if(args.length==2){
                        try{
                            n = Double.parseDouble(args[1]);
                            if(n>0) {
                                System.out.println("ready for pentagon");
                                Pentagon p = new Pentagon(n);
                                System.out.println("surface = " + p.Surface());
                            } else {
                                System.out.println(n + " - wrong range");
                            }
                        }catch (NumberFormatException ex) {
                            System.out.println(args[1] + " - not a number");
                        }
                    } else
                        System.out.println("wrong number of arguments");
                    break;
                case "h":
                    if(args.length==2){
                        try{
                            n = Double.parseDouble(args[1]);
                            if(n>0) {
                                System.out.println("ready for hexagon");
                                Hexagon h = new Hexagon(n);
                                System.out.println("surface = " + h.Surface());
                            } else {
                                System.out.println(n + " - wrong range");
                            }
                        }catch (NumberFormatException ex) {
                            System.out.println(args[1] + " - not a number");
                        }
                    } else
                        System.out.println("wrong number of arguments");
                    break;
                case "q":
                    if(args.length==6){
                        double[] param = new double[5];
                        for(int i=1; i<6; i++){
                            try{
                                n = Double.parseDouble(args[i]);
                                if (n>0) {
                                    param[i - 1] = n;
                                } else{
                                    System.out.println(n + " - wrong range");
                                    return;
                                }
                            }catch (NumberFormatException ex){
                                System.out.println(args[i] + " - not a number");        //will it stop the program?
                                return;                                                 //no but this will
                            }
                        }                                                               //will en up here only if paramethers are numbers
                        if (param[5]>180)
                            return;
                        if(param[0]!=param[1]||param[2]!=param[3])
                            return;
                        if (param[5]!=90){                                              //only rhoumbus or die
                            if(param[0]!=param[2])
                                return;
                            Rhombus r = new Rhombus (param[0],param[5]);
                            System.out.println("surface = " + r.Surface());
                        }
                        if(param[0]==param[2]){
                            Square s = new Square (param[0]);
                            System.out.println("surface = " + s.Surface());
                        }
                        Rectangle r = new Rectangle (param[0],param[2]);
                        System.out.println("surface = " + r.Surface());
                    } else{
                        System.out.println("wrong number of arguments");
                    }
                    break;
                default:
                    System.out.println("such shape does not exist");
            }
        }
    }
}

/*
                case "c":
                case "p":
                case "h":
                        if(args.length==2){
                            try{
                                n = Double.parseDouble(args[1]);
                                if(n>0) {
                                    //we can create object
                                    switch (args[0]){
                                        case "c":
                                            Circle c = new Circle(n);
                                            System.out.println("surface = " + c.Surface());
                                            System.out.println(" circumference = " +c.Circumference());
                                            break;
                                        case "p":
                                            Pentagon p = new Pentagon(n);
                                            System.out.println("surface = " + p.Surface());
                                            System.out.println(" circumference = " +p.Circumference());
                                            break;
                                        case "h":
                                            Hexagon h = new Hexagon(n);
                                            System.out.println("surface = " + h.Surface());
                                            System.out.println(" circumference = " +h.Circumference());
                                            break;
                                    }
                                } else {
                                    System.out.println(n + " - wrong range");
                                }
                            }catch (NumberFormatException ex) {
                                System.out.println(args[1] + " - not a number");
                            }
                        } else
                            System.out.println("wrong number of arguments");
                    break;
 */
