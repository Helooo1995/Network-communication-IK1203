import java.net.*;
import java.io.*;

public class HTTPEcho {
    public static void main( String[] args)throws IOException {


		 int input = Integer.parseInt(args[0]); // inptuten for port

                //skapa server socket, och det ska use i server
		        ServerSocket ss = new ServerSocket(input);

		        while(true) {
                         try {


		        	Socket CS = ss.accept(); //skapa socket for client

                    //skapa input for socket
		        	BufferedReader info = new BufferedReader(new InputStreamReader(CS.getInputStream()));

                    //skapa reserive to socket
		        	DataOutputStream res = new DataOutputStream(CS.getOutputStream());

		        	StringBuilder sb = new StringBuilder();

		    		String ord = null;

		    		String URL = "HTTP/1.1 200 OK\r\n\r\n"; // server ta http massage

		    		sb.append(URL);

					while(!(ord = info.readLine()).isEmpty()) {

						sb.append(ord + "\n");

					}

					//skriva data och sen sluta

					res.writeBytes(sb.toString());

					CS.close();   //close

					info.close();  //close

					res.close(); //close

					System.out.println("Close_Done");

		        	}

		        	catch (IOException exc) {

		        	System.out.println("fail happend");

		        }

      }
    }
}
