import java.io.*;
import java.net.*;

class MyServer {

    ServerSocket server = null;
    Socket client = null;
    BufferedReader in = null;
    PrintWriter out = null;
    String line = "";
    String[] command;
    Class<?>[] keyTypes = new Class<?>[] {Integer.class, Double.class, String.class};
    Class<?> keyType;
    GenericBinaryTree tree;

    MyServer() {
        try {
            tree=new GenericBinaryTree<Integer>();
            keyType=Integer.class;
            server = new ServerSocket(4443);
        } catch (IOException e) {
            System.out.println("Could not listen on port 4443"); System.exit(-1);
        }
    }

    public void listenSocket() {
        try {
            keyType=Integer.class;
            client = server.accept();
            System.out.println("k");
        } catch (IOException e) {
            System.out.println("Accept failed: 4443"); System.exit(-1);
        }
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            System.out.println("l");
        } catch (IOException e) {
            System.out.println("Accept failed: 4443"); System.exit(-1);
        }
        while(line != null) {
            try {
                System.out.println("m");
                line = in.readLine();
                command=line.split(" ");
                if(command.length!=1&&command.length!=2) {
                    System.out.println(line);
                } else{
                    System.out.println("n");
                    if(command.length==1){
                        System.out.println("o"+command[0]);
                        if(command[0].equals("draw")) {
                            System.out.println("p");
                            out.println(TreeSerialization.getSerializedTree(tree));
                        }else if (command[0].equals("getKeyTypes")){
                            System.out.println("r");
                            String[] names = new String[keyTypes.length];
                            for(int i=0;i<names.length;i++)
                                names[i]=keyTypes[i].getSimpleName();
                            System.out.println("s");
                            out.println(String.join(" ", names));
                        }
                    } else if(command[0].equals("changeType")){
                        try{
                            Class<?> tempClass=Class.forName("java.lang."+command[1]);
                            if(keyType==tempClass) {
                                System.out.println(line+"  works no update");
                            } else {
                                keyType=tempClass;
                                tree = new GenericBinaryTree(keyType);
                            }
                            out.println(TreeSerialization.getSerializedTree(tree));
                        }catch (ClassNotFoundException e){
                            System.out.println("ClassNotFoundException");
                        }
                    } else if(command[0].equals("insert")){
                        try {
                            tree.insert(myCast(command[1]));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        out.println(TreeSerialization.getSerializedTree(tree));
                    } else if(command[0].equals("delete")){
                        try {
                            tree.delete(myCast(command[1]));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        out.println(TreeSerialization.getSerializedTree(tree));
                    } else if(command[0].equals("search")){
                        try {
                            out.println(tree.search(myCast(command[1])));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            catch (IOException e) {
                System.out.println("Read failed"); System.exit(-1);
            }
        }
    }
    private Comparable<?> myCast (String keyToCast) throws Exception {
            if (keyType == Integer.class) {
                return Integer.parseInt(keyToCast);
            } else if(keyType == Double.class) {
                return Double.parseDouble(keyToCast);
            } else if (keyType == String.class) {
                return keyToCast;
            } else
                throw new Exception("ClassCastException: "+keyToCast+"can not be cast");
    }
    protected void finalize() {
        try {
            in.close();
            out.close();
            client.close();
            server.close();
        }
        catch (IOException e) {
            System.out.println("Could not close."); System.exit(-1);
        }
    }

    public static void main(String[] args) {
        MyServer server = new MyServer();
        server.listenSocket();
    }
}
