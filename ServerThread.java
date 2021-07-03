import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable
{
	/* A ServerThread for a client is used to keep listening the chat 
	 * request from the other peers, and if any, accepts it, and set a 
	 * sender with the given senderSocket and start to send messages to 
	 * the peer, and then it will set a receiver thread to receive messages from the peer.
	 */
	
	public ServerSocket receiver;
	public Sender sender;
	
	public ServerThread(ServerSocket receiver,Sender sender) throws IOException
	{
		this.receiver = receiver;
		this.sender = sender;
	}

	public void run()
	{
		try
		{
			while(true){
				String content;
				//keep listening the chat request from the other peers.
				System.out.println("Receiver setup success, waiting for request： "+this.receiver.getLocalPort());
				// if there is any chat request, accepts it.
				Socket senderSocket = this.receiver.accept();
				System.out.println(senderSocket.getInetAddress().toString()+","+senderSocket.getPort()+" connected.");
				//set a sender with the given senderSocket and start to send messages to the peer.
				this.sender.setSocket(senderSocket, true);
				// set a receiver thread to receive messages from the peer.
				new Thread(new Receiver(senderSocket,sender)).start();
				while(this.sender.isChatting){
					Thread.sleep(5000);
				}
			}
		}
		catch (Exception e)
		{
		}
	}
}