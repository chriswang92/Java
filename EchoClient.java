/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.*;
import java.net.*;

public class EchoClient
{
	public static ServerSocket receiver;

	public static void main(String[] args) throws IOException
	{
		// the hostname and portnumber for the communication
		String hostName = "127.0.0.1";
		int portNumber = 28585;
		boolean run = true;
		Sender sender = new Sender();
		try
		{
			Socket echoSocket = new Socket(hostName, portNumber);
			/*
			 * Get localPort of echoSocket
			 */
			int localPort = echoSocket.getLocalPort();
			while (true)
			{
				/*
				 * Try setup the receiver for this client by using localport#,
				 * if the localport# is available then just use it, otherwise we
				 * know the port is occupied then try a new port# by increment
				 * localport by 1,the program will keep trying until an
				 * available localport# is used.
				 */
				try
				{
					/*
					 * Setup the receiver ServerSocket at this client node by
					 * using localPort.
					 */
					receiver = new ServerSocket(localPort);
					// Start a ServerThread for this client which keep listening
					// CHAT requests from the other peers.
					new Thread(new ServerThread(receiver, sender)).start();
					System.out.println("The port is available.");
					break;
				}
				catch (IOException e)
				{
					System.out.println("The port is occupied.");
					localPort++;
				}
			}
			/*
			 * setup a printwriter to write inputs to the outputstream of
			 * echoSocket, a bufferedreader to read inputstream from echoSocket,
			 * and a bufferedreader to read standard inputs
			 */
			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(),
					true);
			out.println(localPort);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					echoSocket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(
					System.in));
			String userInput;
			while (run)
			{
				userInput = stdIn.readLine();// read by each line of inputs
				if (userInput.equals("CHAT"))
				{
					// Get the target ip & port#, and set the sender socket s.
					System.out
							.println("Please enter target peer's ip & port#, split by ',' :");
					String inputs = stdIn.readLine();
					String[] split = inputs.split(",");
					String targetIp = split[0];
					int targetPort = Integer.parseInt(split[1]);
					Socket senderSocket = new Socket(hostName, targetPort);
					System.out.println("Target IP address: " + targetIp
							+ " , & receiver port#: " + targetPort
							+ ",local sender localPort#: "
							+ senderSocket.getLocalPort());
					if (targetIp.equals(senderSocket.getInetAddress()
							.toString())
							&& targetPort == senderSocket.getLocalPort())
					{
						System.out.println("Can not chat with yourself!");
					}
					else
					{
						System.out
								.println("P2P communication setup successfully, please enter messages:");
						/*
						 * start a new clientThread that set a sender with the
						 * given sender socket to process the standard inputs
						 * and send messages to the target peer.
						 */
						new Thread(new ClientThread(senderSocket, sender))
								.start();
						/*
						 * let the main thread(echoClient) wait until the chat
						 * finished, and then let the client continue to
						 * communicate with the server.
						 */
						try
						{
							Thread.sleep(200);
						}
						catch (InterruptedException e1)
						{
							e1.printStackTrace();
						}
						while (sender.isChatting == true)
						{
							try
							{
								Thread.sleep(100);
							}
							catch (InterruptedException e)
							{
								e.printStackTrace();
							}
						}
						System.out
								.println("Please press ENTER to return communication to the SERVER");
						userInput = stdIn.readLine();
					}
				}

				if (sender.isChatting == false)
				{
					/*
					 * Before the receiver receives any chat request(if
					 * isChatting is false means this client is not chatting
					 * with any peer),the sender is not created and the
					 * connectionStatus therefore still remain false(default
					 * set), then when the client inputs are still sent to the
					 * Server.
					 */if (userInput.equals("Q"))
					{
						echoSocket.close();
						System.out
								.println("Quit from the communication with the Server successfully, client socket closed.");
						break;
					}
					else
					{
						out.println(userInput);
						System.out.println("echo: " + in.readLine());
					}
				}
				else
				{
					/*
					 * While the receiver of this client received a chat request
					 * from some peer,it will setup a sender &set the
					 * connectionStatus to true which means it is connected with
					 * a peer and start use the sender to send messages to the
					 * peer instead of send inputs to the Server.
					 */
					if (userInput.equals("Q"))
					{
						// make the client's input send back to server
						sender.closeSocket();
						sender.setSocket(null, false);
						System.out
								.println("Quit from the communication with the peer successfully.");

					}
					else
					{
						sender.send(userInput);
					}
				}
			}
		}
		catch (UnknownHostException e)
		{// unknown hostname error
			System.err.println("Don't know about host " + hostName);
			System.exit(1);
		}
		catch (IOException e)
		{// IO error
			System.err.println("Couldn't get I/O for the connection to "
					+ hostName);
			System.exit(1);
		}

	}

}