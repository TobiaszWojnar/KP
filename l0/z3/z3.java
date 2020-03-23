/**
 * @author Tobiasz Wojnar
 * Java Programing Course 2020
 * list 0 task 2 - print arguments and their greator denominator one per line
 **/

import java.util.*;

public class z3 {
	public static int div(int n){
		for (int i =  n/2 + 1; i > 1; i--){
			if(n%i == 0)
				return i;
		}
		return 1;
	}
	public static String printDiv (String arg){
		int n;
		try {
			n=Integer.parseInt(arg);
			if (n<1)
				return "\t not a positive integer";
			else
				return  "\t " + Integer.toString(div (n));
		} catch (NumberFormatException ex) {
			return "\t not an integer";
		}
	}
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++){
			System.out.println(args[i] + printDiv(args[i]));
		}
	}
}
