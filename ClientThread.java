
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread implements Runnable
{
	Socket s;
	Sender sender;
	public ClientThread(Socket s, Sender sender ) throws IOException
	{
		this.s = s;
		this.sender = sender;
	}

	@Override
	public void run()
	{
		try
		{
			/*setup a receiver thread which can be used to read messages 
			from the target peer in this P2P communications.
			*/
			new Thread(new Receiver(s,sender)).start();
			/*set a sender with the given sender socket, and set the connection status 
			of this socket to true(which means the given client is chatting with a peer).
			*/
			this.sender.setSocket(s, true);
			//Continually read the standard input and send them as messages.
			String content;
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			while((content = stdIn.readLine())!=null){
				/*when the client input Q means he want leave the chat, then set the connection status 
				of this socket to false(which means the given client is not chatting with any peer),and
				stop reading standard inputs.
				*/
				if(this.sender.isChatting == false){
					break;
				}
				
				if(content.equals("Q")){
					// When the client quit chat, close the sender socket of the client.
					this.sender.closeSocket();
					System.out.println("Sender socket closed successfully...");
					this.sender.setSocket(null, false);
					break;
				}
				this.sender.send(content);	
			}
			System.out.println("Quit chat successfully...");
		}
		catch (Exception e)
		{
		}
	}
	
	
}