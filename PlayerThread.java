import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class PlayerThread extends Thread {
	/*this class is a subclass from Thread, by concurrency which let multiple clients 
	 * can talk to server (perform appropriate actions) simultaneously.
	 */
	 
	public Socket player;
	ListOp listOp;
	int key;
	//constructor of PlayerThread
	public PlayerThread(Socket client, ListOp listOp) throws IOException{
		this.player = client;
		this.listOp = listOp;
		this.key = 0;
	}
	
	
	public void run() {
		try {
			String inputLine; 
			PrintWriter out = new PrintWriter(this.player.getOutputStream(), true); // the outputs
			BufferedReader in = new BufferedReader(new InputStreamReader(this.player.getInputStream())); //the inputs 	
			inputLine = in.readLine();
			String localPort = inputLine;
			while(true){//Continually read the inputs from client, and process with appropriate actions.
				System.out.println("wait..");
				inputLine = in.readLine(); // read each line from the inputs
				System.out.println(inputLine);
				if(inputLine.equals("JOIN"))
				{//if the input is "JOIN",then let the client join in the list.
					this.key = this.listOp.join(localPort,this.player);
					out.println("JOIN SUCCEED"+" ,currently players#:"+this.listOp.map.size()+" list: " + this.listOp.map.entrySet());
					System.out.println("IP:"+player.getInetAddress()+" Port: "+player.getPort()+" player want to Join, and join list succeed");
				}
				else if(inputLine.equals("LEAVE"))
				{//if the input is "LEAVE",then let the client leave from the list.
					this.listOp.leave(this.key);
					out.println("LEAVE SUCCEED"+" ,currently players#:"+this.listOp.map.size()+" list: " + this.listOp.map.entrySet());
					System.out.println("IP:"+player.getInetAddress()+" Port: "+player.getPort()+" player want to LEAVE, and LEAVE list succeed");
				}
				else if(inputLine.equals("LIST"))
				{//if the input is "LIST",then show the list to the client.
					out.println("Show the list: "+" ,currently players#:"+this.listOp.map.size()+" list: " + this.listOp.map.entrySet());
					System.out.println("IP:"+player.getInetAddress()+" Port: "+player.getPort()+" player want to retrieve the list of online players. ");
				}
				else if(inputLine.equals("Q")){
					/*if the input is "Q" which means the client want quit from the communication,
					 * then break the loop and stop reading inputs from client.
					 */
					break;
				}
				else {
					out.println("Please enter JOIN, LEAVE or LIST, CHAT, or Q for quit");
					System.out.println("IP:"+player.getInetAddress()+" Port: "+player.getPort()+" Wrong message: "+inputLine);
				}
			}
			//after the client quit from the communication,  close the socket.
			this.player.close();
			System.out.println("player QUIT succeed, socket closed");
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
