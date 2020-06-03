import java.awt.*;
import java.awt.event.ActionEvent;
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
    private SidePanel.Listener listener;

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




    public String sendType(String type){
        out.println(type);
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

    public void setListener(SidePanel.Listener listener) {
        this.listener = listener;
    }
    public interface Listener {
        void typeChosen (String type);
        void elementToInsertChosen(String element);
        void elementToDeleteChosen(String element);
        void elementToSearchChosen(String element);
        void drawChosen();
    }
}
