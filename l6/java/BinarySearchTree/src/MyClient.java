import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
/**
 * The <code>MyClient</code> class is responsible for complicating between {@link TreeGUI} and {@link MyServer}.
 * It is exercise for object oriented programing course at Wroclaw University of Science and Technology.
 * The objective of this list was to implement binary search tree with generic type keys,
 * create {@link TreeGUI GUI  application} and implement {@link MyClient client} {@link MyServer server} architecture.
 * <p>
 *     <code>MyClient</code> creates {@code socket("localhost", 4443)} and implements methods for sending requests to server.
 *     Requests match functions implemented in {@link GenericBinaryTree}
 *     port
 * <p/>
 * @version     09 June 2020
 * @author      Tobiasz Wojnar
 * @see         MyServer
 * @see         TreeGUI
 * @see         GenericBinaryTree
 */
public class MyClient {
    private PrintWriter out = null;
    private BufferedReader in = null;

    /**
     * Constructor creates {@code socket("localhost", 4443)}, initializes {@link PrintWriter writer} and {@link BufferedReader reader}.
     * If initialization or connecting with server exception is caught and client is shut down.
     * @value port = 4443
     */
    public MyClient(){
        try {
            int port = 4443;
            Socket socket = new Socket("localhost", port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: localhost"); System.exit(1);
        } catch  (IOException e) {
            System.out.println("No I/O"); System.exit(1);
        }
    }

    /**
     * Sends request with new key type of tree.
     * @param type Type of key of tree.
     * @return answer form server, expected serialized tree.
     *         If type changed deletes old tree, else does nothing to tree
     * @see GenericBinaryTree
     * @see MyServer
     * @see TreeSerialization
     */
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
    /**
     * Sends request to insert new value into tree.
     * @param value key to insert to tree.
     * @return answer form server, expected serialized tree.
     * @see GenericBinaryTree
     * @see MyServer
     * @see TreeSerialization
     */
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
    /**
     * Sends request to delete value from tree.
     * @param value Key to delete from tree.
     * @return answer form server, expected serialized tree.
     * @see GenericBinaryTree
     * @see MyServer
     * @see TreeSerialization
     */
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
    /**
     * Sends request to find value in tree.
     * @param value Searched key in tree.
     * @return answer form server, expected pointer to element or null if not found.
     * @see GenericBinaryTree
     * @see MyServer
     * @see TreeSerialization
     */
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

    /**
     * Sends request to for serialized tree.
     * @return serialized tree.
     * @see GenericBinaryTree
     * @see MyServer
     * @see TreeSerialization
     */
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

    /**
     * Sends request to get tree types supported on server.
     * Enable having correct types in control panel.
     * @return Table of names implemented on server.
     * @see GenericBinaryTree
     * @see MyServer
     * @see SidePanel
     */
    public String[] getTypes(){
        out.println("getKeyTypes");
        String input=null;
        try {
            input=in.readLine();
        } catch (IOException e) {
            System.out.println("Read failed"); System.exit(1);
        }
        return input.split(" ");
    }
}