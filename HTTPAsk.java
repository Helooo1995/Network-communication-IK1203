import java.net.*;

import java.io.*;

import tcpclient.TCPClient;


public class HTTPAsk {

  public static void main( String[] args) throws IOException{


     ServerSocket soc = new ServerSocket(Integer.parseInt(args[0]));


  // make four string to use in client,domain,port,send to server
     String st;

     String doname;

     String p;

     String send;


     while(true){

       try{

    	 doname = null;
         p = null;             //reset al value to null
         send = null;
         st = null;

         Socket cs = soc.accept();

         //create an input
         BufferedReader fc = new BufferedReader(new InputStreamReader(cs.getInputStream()));

         DataOutputStream oc = new DataOutputStream(cs.getOutputStream());

         StringBuilder out = new StringBuilder();

         st = fc.readLine();

         if(st != null){

           //split at this tecken
           String[] splitted = st.split("[/?=& ]");


           for(int i = 0; i < splitted.length; i++){

             if(splitted[i].equals("hostname"))  //print host
             {

            	 doname = splitted[i+1];

               i++;
             }

             else if(splitted[i].equals("port")) //print port
             {
               p = splitted[i+1];

               i++;

             }

             else if(splitted[i].equals("string")) //print string
             {

               send  = splitted[i+1];

               i++;

             }

           }


           if(splitted[2].equals("ask") && doname != null && p != null){


             try{

               //connect to server
               String response = TCPClient.askServer(doname,Integer.parseInt(p),send);
               //ansewer from server
               String HTTPOK = "HTTP/1.1 200 OK\r\n\r\n";

               oc.writeBytes(HTTPOK + response);


             } catch(IOException e){

               String HTTPNotFound  = "HTTP/1.1 404 Not Found\r\n";

               oc.writeBytes(HTTPNotFound);
               //fail with connection

             }

           } else {

             String fail = "HTTP/1.1 400 Bad Request\r\n";

             // is null or no ask

             oc.writeBytes(fail);

           }
         }
         //close
         cs.close();

       } catch (IOException e){

         System.exit(1);

       }

     }

   }

 }
