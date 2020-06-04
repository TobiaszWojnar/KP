import java.io.*;
import java.net.*;

class MyServer implements AutoCloseable{
    ServerSocket server = null;
    Socket client = null;
    BufferedReader in = null;
    PrintWriter out = null;
    String line = "";//TODO why not in constructor
    String[] command;
    Class<?>[] keyTypes = new Class<?>[] {Integer.class, Double.class, String.class};
    Class<?> keyType;
    GenericBinaryTree tree;
    MyServer() {
        try {
            server = new ServerSocket(4444);
            tree=new GenericBinaryTree<Integer>();
            keyType=Integer.class;
        } catch (IOException e) {
            System.out.println("Could not listen on port 4444"); System.exit(-1);
        }
    }

    public void listenSocket() {
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
                    if(command.length==1){
                        if(command[0].equals("draw")) {
                            out.println(treeSerializable());
                        }else if (command[0].equals("getTypes")){//TODO test
                            String[] names = new String[keyTypes.length];
                            for(int i=0;i<names.length;i++)
                                names[i]=keyTypes[i].getSimpleName();
                            out.println(String.join(" ", names));
                        }
                    } else if(command[0].equals("changeType")){
                        try{
                            Class<?> tempClass=Class.forName("java.lang."+command[1]);
                            if(keyType==tempClass) {
                                System.out.println(line+"  works no update");
                            } else {
                                keyType=tempClass;
                                tree = new GenericBinaryTree(keyType);//TODO tu moze sie jebac
                            }
                            out.println(treeSerializable());
                        }catch (Exception e){//TODO what kind of Exception
                            System.out.println("bad data type");
                        }
                    } else if(command[0].equals("insert")){//TODO make tests
                        try {
                            tree.insert(myCast(command[1]));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        out.println(treeSerializable());
                    } else if(command[0].equals("delete")){
                        try {
                            tree.delete(myCast(command[1]));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        out.println(treeSerializable());
                    } else if(command[0].equals("search")){
                        try {
                            out.println(tree.search(myCast(command[1])));//TODO think how to
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
            } else if (keyType == String.class) {
                return keyToCast;
            }else
                throw new Exception("sth make");//TODO  what type
    }
    public void close() {//not finalize because deprecated
        try {
            in.close();
            out.close();
            client.close();
            server.close();
        } catch (IOException e) {
            System.out.println("Could not close."); //System.exit(-1);
        }
    }

    public static void main(String[] args) {
        try(MyServer server = new MyServer()){//because implements autoclosable //Chceck if clase if exit by exit
            server.listenSocket();
        }
    }
    public String treeSerializable (){
        String treeString="";
        return treeString.concat(tree.draw());
    }
}
