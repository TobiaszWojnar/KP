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
            // Polaczenie z socketem
            // Wysylanie do serwera
            out = new PrintWriter(socket.getOutputStream(), true);
            // Odbieranie z serwera
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost"); System.exit(1);
        } catch  (IOException e) {
            System.out.println("No I/O"); System.exit(1);
        }
    }

    public String sendType(String type){//TODO czy odbieranie nie powinno być w osobnym wątku
        out.println("changeType "+type);//TODO jakies API??
        String input=null;
        try {
            // Odbieranie z serwera
            input=in.readLine();
        }
        catch (IOException e) {
            System.out.println("Read failed"); System.exit(1);
        }
        return input;
    }
    public String insert(String value){
        out.println("insert "+value);
        String input=null;
        try {
            // Odbieranie z serwera
            input=in.readLine();
        }
        catch (IOException e) {
            System.out.println("Read failed"); System.exit(1);
        }
        return input;
    }
    public String delete(String value){
        out.println("delete "+value);
        String input=null;
        try {
            // Odbieranie z serwera
            input=in.readLine();
        }
        catch (IOException e) {
            System.out.println("Read failed"); System.exit(1);
        }
        return input;
    }
    public String search(String value){
        out.println("search "+value);
        String input=null;
        try {
            // Odbieranie z serwera
            input=in.readLine();
        }
        catch (IOException e) {
            System.out.println("Read failed"); System.exit(1);
        }
        return input;
    }
    public String draw(){
        out.println("draw");
        String input=null;
        try {
            // Odbieranie z serwera
            input=in.readLine();
        }
        catch (IOException e) {
            System.out.println("Read failed"); System.exit(1);
        }
        return input;
    }
}
