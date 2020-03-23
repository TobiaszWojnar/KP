/**
 * @author Tobiasz Wojnar
 * Java Programing Course 2020
 * list 2 exercise 1
 * Implement method translating arabic to roman numbers
 **/


public class Main {
    public static void main (String[] liczba){
        int i;
        for (String s : liczba) {
            try {
                i = Integer.parseInt(s);
                System.out.println(RzymArab.arab2rzym(i));
            }
            catch (RzymArabException k){
                System.out.println(k.getMessage());
            }
            catch (NumberFormatException d) {
                try {
                    i = RzymArab.rzym2arab(s);
                    System.out.println(i);
                }
                catch (RzymArabException k) {
                    System.out.println(k.getMessage());
                }
            }
        }
    }
}