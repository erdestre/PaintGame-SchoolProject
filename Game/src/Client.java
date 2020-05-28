import java.net.*;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import java.io.*;


public class Client{
	
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	private ServerSocket server;
	private static Socket client;
	private static String srv;
		public Client(String info) {
			srv = info;
		}
		public static void runClient() {
			try {
				while(true) {
					try {
						connToS();
						streams();
						processConn();
					}
					catch(EOFException e){
						dispMessage("\nClient Terminated Conn\n");
					}
					finally {
						closeConn();
					}
				}
			}
			catch(IOException e) {
				
			}
		}
		private static void connToS() throws IOException{
			dispMessage("Attempting");
			client = new Socket(InetAddress.getByName(srv),6666);
		}
		private static void streams() throws IOException {
			oos = new ObjectOutputStream(client.getOutputStream());
			oos.flush();
			
			ois = new ObjectInputStream(client.getInputStream());
			dispMessage("\nStreams\n");
		}
		private static void processConn() throws IOException{
			String msg = "";
			do {
				try {
					msg = (String) ois.readObject();
					dispMessage("\n" + msg);
				}catch(ClassNotFoundException e){
					dispMessage("Unknown");
					
				}
				
			} while(!msg.equals("C:ExitTheSystem"));
		}
		private static void closeConn() {
			dispMessage("\nTerminating Conn\n");
			
			try {
				oos.close();
				ois.close();
				client.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		public static void send(String Nickname, String text) {
			try {
				oos.writeObject(Nickname+": "+text+"\n");
				oos.flush();
				dispMessage(Nickname + ": " +text+"\n");
			}
			catch(IOException e){
				gameScreen.chatScreen.append("\nError"); //jts is text area
				
			}
		}
		private static void dispMessage(final String string) {
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					gameScreen.chatScreen.append(string);
					
				}
			});
			
		}
		public static String Nickname() {
			String Nickname = Source.Nickname;
			return Nickname;
			
		}
		}
