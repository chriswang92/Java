/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
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

import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        //portnumber the server socket links to
        int portNumber = 28585;
		ListOp listOp = new ListOp();//setup a new Object of list Operator class
		
		try {
			/* Setup the server socket that links to the given portnumber 
			 * which welcome(listening) the request of connections from clients.
			 */
			ServerSocket serverSocket = new ServerSocket(portNumber);
			System.out.println("The server socket created, waiting for connections...");
			while (true) 
			{	
				/* Continually accept requests from clients.
				 * By calling accept() which returns a recently created Socket object,
				 *  which is binded with client's ip address & port number.
				 */
				Socket clientSocket = serverSocket.accept();

				//When contacted by client,server TCP creates new Thread(socket) for server process to communicate(serve) with client.
				new Thread(new PlayerThread(clientSocket,listOp)).start();//setup a new thread to serve the client
				
				System.out.println("Client Connected. ip: "+clientSocket.getInetAddress()+", port#: "+clientSocket.getPort());   
				
			}
		} catch (IOException e) {
			System.out.println(
					"Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
			System.out.println(e.getMessage());
		}
		
    }
}
