import java.net.*;
import java.io.*;
import tcpclient.TCPClient;

public class MyRunnable implements Runnable {

  Socket connectionSocket;

  public MyRunnable(Socket connectionSocket) throws Exception {
    this.connectionSocket =  connectionSocket;
  }
  //data client
  String data = null;
  //name for domain
  String host = null;
  // number to the port
  String port = null;
  //send data server
  String string = null;

  public void run() {

    try{
    	//Socket input
      BufferedReader fcinput = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
       //D socket
      DataOutputStream toc = new DataOutputStream(connectionSocket.getOutputStream());

      // to the client
      StringBuilder out = new StringBuilder();
      data = fcinput.readLine();

      if(data != null){
    	//split tecken
        String[] spl = data.split("[/?=& ]");

        for(int i = 0; i < spl.length; i++){  // view all
          if(spl[i].equals("hostname")){      // view all
            host = spl[i+1];
            i++;
        }
        else if(spl[i].equals("port")){
          port = spl[i+1];
          i++;
        }
        else if(spl[i].equals("string")){
          string  = spl[i+1];
          i++;
        }
        }

        if(spl[2].equals("ask") && host != null && port != null){

        	// connect again to server
          try{
        	//server answer
            String answer = TCPClient.askServer(host,Integer.parseInt(port),string);
            String okk = "HTTP/1.1 200 OK\r\n\r\n";
            toc.writeBytes(okk + answer);

          } catch(IOException e){
            String fw  = "HTTP/1.1 404 Not Found\r\n";
            toc.writeBytes(fw);

          }
        } else {
          String httpbad = "HTTP/1.1 400 Bad Request\r\n";
          toc.writeBytes(httpbad);

        }
      }
      // Stop connection
      connectionSocket.close();
    } catch(IOException e){
      System.exit(1);
    }
  }
}

