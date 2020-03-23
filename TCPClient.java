package tcpclient;
import java.net.*;
import java.io.*;


public class TCPClient {
    private static int BUFFERSIZE = 1024;

    public static String askServer(String hostname, int port, String ToServer) throws  IOException {
	if(ToServer == null)
	    return askServer(hostname, port);

	int i =0;

	byte[] fromUserBuffer  = new byte[BUFFERSIZE];
	byte[] fromServerBuffer  = new byte[BUFFERSIZE];
	Socket clientSocket  = new Socket(hostname, port);

	clientSocket .getOutputStream().write(ToServer.getBytes());
	clientSocket .getOutputStream().write('\n');

	int fromUserLength = clientSocket .getInputStream().read(fromUserBuffer );

	StringBuilder OUT = new StringBuilder("");

	while(i < fromUserLength)
	{
		OUT.append((char)fromUserBuffer [i]);
		i++;
	}

	clientSocket .close();
	return OUT.toString();
    }

    public static String askServer(String hostname, int port) throws  IOException {

    int i = 0;
	byte[] fromUserBuffer  = new byte[BUFFERSIZE];

	Socket clientSocket  = new Socket(hostname, port);
	int  fromUserLength  = clientSocket .getInputStream().read(fromUserBuffer );
	StringBuilder OUT = new StringBuilder("");

	while(i<fromUserLength)
	{
		OUT.append((char)fromUserBuffer [i]);
		i++;
	}


	clientSocket .close();
	return OUT.toString();
    }
}