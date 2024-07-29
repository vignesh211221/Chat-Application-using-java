
import java.io.*; 
import java.net.*; 
import java.util.Scanner; 

public class Client 
{ 
	final static int ServerPort = 8080; 

	public static void main(String args[]) throws UnknownHostException, IOException 
	{ 
		Scanner scn = new Scanner(System.in); 
		
		
		InetAddress ip = InetAddress.getByName("LocalHost"); 
	 
		Socket s = new Socket(ip, ServerPort);
		
		
		DataInputStream dis = new DataInputStream(s.getInputStream()); 
		DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 

		 
		Thread sendMessage = new Thread(new Runnable() 
		{ 
			@Override
			public void run() { 
				while (true) { 

					// read the message to deliver. 
					String msg = scn.nextLine(); 
					
					try { 
						// write on the output stream 
						dos.writeUTF(msg); 
					} catch (IOException e) { 
						e.printStackTrace(); 
					} 
				} 
			} 
		}); 
		
		
		Thread readMessage = new Thread(new Runnable() 
		{ 
			@Override
			public void run() { 

				while (true) { 
					try { 
						// read the message sent to this client 
						String msg = dis.readUTF(); 
						System.out.println(msg); 
					} catch (IOException e) { 

						e.printStackTrace(); 
					} 
				} 
			} 
		}); 

		sendMessage.start(); 
		readMessage.start(); 

	} 
} 
