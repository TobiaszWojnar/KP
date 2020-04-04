package l2;

public class RzymArab { private static String[] numbers =
        {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
    //0     1    2    3     4     5    6     7    8     9    10    11   12
    private static int[] values =
            { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

    public static int rzym2arab(String rzym) throws RzymArabException {
        int result = 0;
        for (int i = numbers.length-1; i >= 0; i--){
            int j=0;
            while (rzym.startsWith(numbers[i])) {
                j++;
                if (j>3)
                    throw new RzymArabException("wrong roman number (1)");
                result+=values[i];
                rzym=rzym.substring(numbers[i].length(), rzym.length());
                if (i%4!=0) {
                    if(i%2==0) {
                        i=i-(i%4)+1;
                    } else{
                        i=i-(i%4);
                    }
                    break;
                }
            }
        }
        if(rzym.length()>0){
            throw new RzymArabException("wrong roman number (2)");
        }
        if (result == 0){
            throw new RzymArabException("empty number");
        }
        if (result > 3999){
            throw new RzymArabException("to big number");
        }
        return result;
    }

    public static String arab2rzym(int arab) throws RzymArabException {
        String result = "";
        if(arab<4000 && arab>0) {
            for (int i = values.length-1; i >= 0; i--){
                while (arab>=values[i]) {
                    result+=numbers[i];
                    arab-=values[i];
                }
            }
        } else {
            throw new RzymArabException("number out of range");
        }
        return result;
    }

}
