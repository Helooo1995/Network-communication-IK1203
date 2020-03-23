import java.net.*;
import java.io.*;

public class ConcHTTPAsk {
  public static void main( String[] args) throws IOException {

    try{
    	//make welcomw port input

      ServerSocket welcomeSocket = new ServerSocket(Integer.parseInt(args[0]));

      while (true) {
    	//create new socket wait then to contact then return
        Socket connectionSocket = welcomeSocket.accept();
        //make new thread
        new Thread(new MyRunnable(connectionSocket)).start();
            }

    } catch(Exception e){
      System.out.print("wrong");
    }
  }
}
