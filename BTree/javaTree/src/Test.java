
public class Test {

    enum DataType {sth,nothing}

    public static void main(String[] args) {
        DataType dataType=DataType.sth;
        String myData = "sthd";

        try {
            if(dataType == DataType.valueOf(myData)){
                System.out.println("works");
            }
            else {
                System.out.println("Shit");
            }
        }catch (Exception e){
            System.out.println("bad type");
        }
    }
}
