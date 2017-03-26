import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

//This is the receiver class which can be used to read messages in P2P communications.

public class Receiver extends Thread
{
	public Socket s;
	public Sender sender;
	public Receiver(Socket s,Sender sender){
		this.s = s;
		this.sender=sender;
	}
	public void run() {
		
		try{
			while(true){
				System.out.println("Start receiving messages");
				String content;
				BufferedReader in = new BufferedReader(new InputStreamReader(this.s.getInputStream()));
				//Continually read messages from a given socket's inputstream.
				while ((content = in.readLine()) != null)
				{
					if(content.equals("Q")){
						System.out.println("Sender quit");
						this.sender.setSocket(null,false);
						break;
					}
					System.out.println(content);
				}
				System.out.println("Stop receiving messages");
				this.sender.closeSocket();
				this.sender.setSocket(null, false);
				break;
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	 
	 }
}
