import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// This is the sender class which can be used to send messages in P2P communications.
 
public class Sender
{
	public static boolean isChatting = false;
	public Socket s;
	public Sender(){
	}
	public void setSocket(Socket s,boolean isChatting){
		this.s = s;
		this.isChatting = isChatting;
	}
	public void closeSocket(){
		try
		{
			this.s.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		};
	}
	
	
	public void send(String message) {
		try{
			//Setup a PrintWriter object and use it to write messages to a given socket's outputstream.
			PrintWriter out = new PrintWriter(this.s.getOutputStream(), true);
			out.println("Peer "+s.getInetAddress().toString() + " , "+ s.getLocalPort() + " said: " + message);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	 }
}
