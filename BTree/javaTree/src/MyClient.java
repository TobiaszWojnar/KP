import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient {
    Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    public MyClient(){
        try {
            socket = new Socket("localhost", 4443);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost"); System.exit(1);
        } catch  (IOException e) {
            System.out.println("No I/O"); System.exit(1);
        }
    }

    public String sendType(String type){
        out.println("changeType "+type);
        String input=null;
        try {
            input=in.readLine();
        } catch (IOException e) {
            System.out.println("Read failed"); System.exit(1);
        }
        return input;
    }
    public String insert(String value){
        System.out.println("client do server: "+"insert "+value);
        out.println("insert "+value);
        String input=null;
        try {
            input=in.readLine();
        } catch (IOException e) {
            System.out.println("Read failed"); System.exit(1);
        }
        System.out.println("client od server: "+input);
        return input;
    }
    public String delete(String value){
        out.println("delete "+value);
        String input=null;
        try {
            input=in.readLine();
        } catch (IOException e) {
            System.out.println("Read failed"); System.exit(1);
        }
        return input;
    }
    public String search(String value){
        out.println("search "+value);
        String input=null;
        try {
            input=in.readLine();
        } catch (IOException e) {
            System.out.println("Read failed"); System.exit(1);
        }
        return input;
    }
    public String draw(){
        out.println("draw");
        String input=null;
        try {
            input=in.readLine();
        } catch (IOException e) {
            System.out.println("Read failed"); System.exit(1);
        }
        return input;
    }
    public String[] getType(){
        out.println("getKeyTypes");
        String input=null;
        try {
            System.out.println("g");
            input=in.readLine();
            System.out.println("h");
        } catch (IOException e) {
            System.out.println("Read failed"); System.exit(1);
        }
        return input.split(" ");
    }
}