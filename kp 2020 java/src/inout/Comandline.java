package inout;

import java.util.Scanner;

public class comandline {
    public static void main(String[] args) {
        String sth;
        String[] splitted;
        Scanner myIn = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter sth");

        sth = myIn.nextLine();  // Read user input
        splitted=sth.split(" ");
        System.out.println("Its sth: " + sth + "   "+ splitted[0]);  // Output user input
        myIn.hasNextLine();
    }
}
/*
Method          Description
nextBoolean() 	Reads a boolean value from the user
nextByte() 	    Reads a byte value from the user
nextDouble() 	Reads a double value from the user
nextFloat() 	Reads a float value from the user
nextInt() 	    Reads a int value from the user
nextLine()  	Reads a String value from the user
nextLong()  	Reads a long value from the user
nextShort() 	Reads a short value from the user
 */
/*
 Scanner sc = new Scanner(new File("myNumbers"));
      while (sc.hasNextLong()) {
          long aLong = sc.nextLong();
      }
 */
/*
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

 *
 * Java program to read file using Scanner class in Java.
 * java.util.Scanner is added on Java 5 and offer convenient method to read data
 *
 * @author
public class ScannerExample {

    public static void main(String args[]) throws FileNotFoundException {

        //creating File instance to reference text file in Java
        File text = new File("C:/temp/test.txt");

        //Creating Scanner instnace to read File in Java
        Scanner scnr = new Scanner(text);

        //Reading each line of file using Scanner class
        int lineNumber = 1;
        while(scnr.hasNextLine()){
            String line = scnr.nextLine();
            System.out.println("line " + lineNumber + " :" + line);
            lineNumber++;
        }

    }

}
 */