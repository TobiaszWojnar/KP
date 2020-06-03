import java.io.*;
import java.net.*;

class SocketServer {
  ServerSocket server = null;
  Socket client = null;
  BufferedReader in = null;
  PrintWriter out = null;
  String line = "";

  SocketServer() { 
    try {
      server = new ServerSocket(4444); 
    } 
    catch (IOException e) {
      System.out.println("Could not listen on port 4444"); System.exit(-1);
    }
  }

  public void listenSocket() {
    try {
      client = server.accept();
    } 
    catch (IOException e) {
      System.out.println("Accept failed: 4444"); System.exit(-1);
    }
    try {
        // Odbieranie od socketa
      in = new BufferedReader(new InputStreamReader(client.getInputStream()));
      // Wysylanie do socketa
      out = new PrintWriter(client.getOutputStream(), true);
    } 
    catch (IOException e) {
      System.out.println("Accept failed: 4444"); System.exit(-1);
    }
    while(line != null) {
      try {
          // Odbieranie od socketa
        line = in.readLine();
        // Wypisywanie na serwerze
        System.out.println(line);       
        // Wysylanie do socketa
        out.println("-> ("+line+")");
      } 
      catch (IOException e) {
        System.out.println("Read failed"); System.exit(-1);
      } 
    }
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
    SocketServer server = new SocketServer();
    server.listenSocket();
  }
}
