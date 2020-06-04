import java.io.*;
import java.net.*;

class MyServer {
    ServerSocket server = null;
    Socket client = null;
    BufferedReader in = null;
    PrintWriter out = null;
    String line = "";//TODO why not in constructor
    String[] command;
    enum KeyType {Integer, Double, String}
    KeyType keyType;
    GenericBinaryTree tree;
    MyServer() {
        try {
            server = new ServerSocket(4444);
            tree=new GenericBinaryTree<Integer>();
            keyType=KeyType.Integer;
        }
        catch (IOException e) {
            System.out.println("Could not listen on port 4444"); System.exit(-1);
        }
    }

    public void listenSocket() {//TODO why not in constructor
        try {
            client = server.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 4444"); System.exit(-1);
        }
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Accept failed: 4444"); System.exit(-1);
        }
        while(line != null) {
            try {
                line = in.readLine();
                command=line.split(" ");
                if(command.length!=1&&command.length!=2) {
                    System.out.println(line);
                } else{
                    if(command.length==1 &&command[0].equals("draw")) {//will it work?
                        out.println(treeSerializable());
                    } else if(command[0].equals("changeType")){             //TODO does it work?
                        try{
                            if(keyType==KeyType.valueOf(command[1])) {
                                System.out.println(line+"  works no update");
                            } else {
                                keyType=KeyType.valueOf(command[1]);
                                switch (keyType) {
                                    case Integer:
                                        //tree. destroy
                                        tree = new GenericBinaryTree<Integer>();
                                        break;
                                    case Double:
                                        //tree. destroy
                                        tree = new GenericBinaryTree<Double>();
                                        break;
                                    case String:
                                        //tree. destroy
                                        tree = new GenericBinaryTree<String>();
                                        break;
                                }
                            }
                            out.println(treeSerializable());
                        }catch (Exception e){//TODO what kind of Exception
                            System.out.println("bad data type");
                        }
                    } else if(command[0].equals("insert")){
                        //tree.insert(command[0]);//TODO how to cast?
                        out.println(treeSerializable());
                    } else if(command[0].equals("delete")){
                        //tree.delete(command[0]);//TODO how to cast?
                        out.println(treeSerializable());
                    } else if(command[0].equals("search")){
                        //tree.search(command[0]);//TODO how to cast?
                        out.println(treeSerializable());//TODO make  searches it                                out.println("tree");em selected
                    }
                }
            }
            catch (IOException e) {
                System.out.println("Read failed"); System.exit(-1);
            }
        }
    }
    protected void finalize() {//TODO what to do if not finalize
        try {
            in.close();
            out.close();
            client.close();
            server.close();
        } catch (IOException e) {
            System.out.println("Could not close."); System.exit(-1);
        }
    }

    public static void main(String[] args) {
        SocketServer server = new SocketServer();
        server.listenSocket();
    }
    public String treeSerializable (){
        String treeString="";
        return treeString.concat(tree.draw());
    }
}
