import java.io.*;
import java.net.*;
/**
 * The <code>MyServer</code> class is responsible for storing {@link GenericBinaryTree tree} and preforming operations on it.
 * It is exercise for object oriented programing course at Wroclaw University of Science and Technology.
 * The objective of this list was to implement binary search tree with generic type keys,
 * create {@link TreeGUI GUI  application} and implement {@link MyClient client} {@link MyServer server} architecture.
 * <p>
 *     <code>MyServer</code> creates <code>ServerSocket</code> and implements methods for requests form {@link MyClient client}.
 *     Requests match functions implemented in {@link GenericBinaryTree}, additionally method for returning supported types and changing tree type.
 * <p/>
 * @version     09 June 2020
 * @author      Tobiasz Wojnar
 * @see         MyClient
 * @see         TreeGUI
 * @see         GenericBinaryTree
 */
class MyServer {

    private ServerSocket server = null;
    private Socket client = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String line = "";
    /**
     * Table of supported tree key types/classes.
     */
    public final Class<?>[] keyTypes = new Class<?>[] {Integer.class, Double.class, String.class};
    /**
     * Current type of nodes in tree.
     * Element of {@link #keyTypes}.
     */
    private Class<?> keyTreeType;
    private GenericBinaryTree tree;//TODO how to make it more elegant

    /**
     * Constructor creates <code>serverSocket</code> and initializes tree.
     * Initializes tree type as first element in {@link #keyTypes} (table with supported classes)
     * @value port 4443
     */
    MyServer() {
        try {
            keyTreeType = keyTypes[0];
            tree = new GenericBinaryTree(keyTreeType);
            int port = 4443;
            server = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Could not listen on port 4443"); System.exit(-1);
        }
    }

    /**
     * Method listening for requests from <code>client</code>.
     * Initializes {@link PrintWriter writer} and {@link BufferedReader reader}.
     * If initialization or connecting with server exception is caught and client is shut down.
     * Else goes to infinite loop listening for requests form client.
     * <p>Supported requests
     * <ul>
     *     <li> "draw"          -   Returns {@linkplain TreeSerialization serialized tree}.
     *     <li> "getKeyTypes"   -   Returns supported tree types {@link #keyTypes}.
     *     <li> "changeType"    -   If new key type is not equal to present, it deletes tree and creates new.
     *                              Returns {@linkplain TreeSerialization serialized tree}.
     *     <li> "insert"        -   Try to insert new key.
     *                              Returns {@linkplain TreeSerialization serialized tree}.
     *     <li> "delete"        -   Try to delete key.
     *                              Returns {@linkplain TreeSerialization serialized tree}.
     *     <li> "search"        -   Searches for key.
     *                              Returns pointer for node or null if key not found.
     * </ul></p>
     */
    private void listenSocket() {//TODO move catching exceptions from functions to here
        try {
            client = server.accept();
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Accept failed: 4443"); System.exit(-1);
        }

        while(line != null) {
            try {
                line = in.readLine();
                String[] command = line.split(" ");
                if(command.length==1){
                    switch (command[0]){
                        case "draw":
                            out.println(draw());                 break;
                        case "getKeyTypes":
                            out.println(getKeyTypes());          break;
                    }
                } else if(command.length==2) {
                    switch (command[0]){
                        case "changeType":
                            out.println(changeType(command[1])); break;
                        case "insert":
                            out.println(insert(command[1]));     break;
                        case "delete":
                            out.println(delete(command[1]));     break;
                        case "search":
                            out.println(search(command[1]));     break;
                        default:
                            System.out.println(line);
                    }
                } else
                    System.out.println(line);
            } catch (IOException e) {
                System.out.println("Read failed"); System.exit(-1);
            }
        }
    }

    /**
     * Returns supported tree types {@link #keyTypes}.
     * @return Supported tree types {@link #keyTypes}.
     */
    private String getKeyTypes(){
        String[] names = new String[keyTypes.length];
        for(int i=0;i<names.length;i++)
            names[i]=keyTypes[i].getSimpleName();
        return String.join(" ", names);
    }

    /**
     * If new key type is not equal to present, it deletes tree and creates new.
     * @param newType Name of class of new type of elements in tree.
     * @return {@linkplain TreeSerialization Serialized tree}
     */
    private String changeType(String newType){
        try{
            Class<?> tempClass=Class.forName("java.lang."+ newType);
            if(keyTreeType ==tempClass) {
                System.out.println(line+"  works no update");
            } else {
                keyTreeType =tempClass;
                tree = new GenericBinaryTree(keyTreeType);
            }
        }catch (ClassNotFoundException e){
            System.out.println("ClassNotFoundException");
        }
        return TreeSerialization.getSerializedTree(tree);
    }

    /**
     * Returns {@linkplain TreeSerialization serialized tree}
     * @return {@linkplain TreeSerialization Serialized tree}
     */
    private String draw (){
        return TreeSerialization.getSerializedTree(tree);
    }

    /**
     * Try to insert new key into tree.
     * @param key new key to be inserted
     * @return {@linkplain TreeSerialization Serialized tree}
     */
    private String insert (String key){
        try {
            tree.insert(myCast(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TreeSerialization.getSerializedTree(tree);
    }

    /**
     * Try to delete key from tree.
     * @param key Key to be deleted.
     * @return {@linkplain TreeSerialization Serialized tree}
     */
    private String delete (String key){
        try {
            tree.delete(myCast(key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TreeSerialization.getSerializedTree(tree);
    }

    /**
     * Searches for key in tree.
     * @param key Key to be searched
     * @return Returns pointer for node or null if key not found.
     */
    private String search(String key){
        try {
            return tree.search(myCast(key)).toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Casts element to tree type.
     * @param keyToCast element to be casted.
     * @return casted element
     * @throws Exception ClassCastException
     */
    private Comparable<?> myCast (String keyToCast) throws Exception {
        if (keyTreeType == Integer.class) {
            return Integer.parseInt(keyToCast);
        } else if(keyTreeType == Double.class) {
            return Double.parseDouble(keyToCast);
        } else if (keyTreeType == String.class) {
            return keyToCast;
        } else
            throw new Exception("ClassCastException: "+keyToCast+"can not be cast");
    }

    /**
     * Closes and cleans after server
     */
    protected void finalize() {
        try {
            in.close();
            out.close();
            client.close();
            server.close();
        } catch (IOException e) {
            System.out.println("Could not close."); System.exit(-1);
        }
    }

    /**
     * Launches <code>MyServer</code>
     */
    public static void main(String[] args) {
        MyServer server = new MyServer();
        server.listenSocket();
    }
}
