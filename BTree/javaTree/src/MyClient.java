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
            socket = new Socket("localhost", 4444);
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
        out.println("insert "+value);
        String input=null;
        try {
            input=in.readLine();
        } catch (IOException e) {
            System.out.println("Read failed"); System.exit(1);
        }
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
        out.println("getKeyType");
        String input=null;
        try {
            input=in.readLine();
        } catch (IOException e) {
            System.out.println("Read failed"); System.exit(1);
        }
        return input.split(" ");
    }
}