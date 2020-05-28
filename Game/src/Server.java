import java.net.*;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import java.io.*;


public class Server{
	
	private static String client_Name;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	private static ServerSocket server;
	private static Socket conn;
		public Server() {
			
		}
		public static void runServer() {
			try {
				server = new ServerSocket(6666,100);
				while(true) {
					try {
						waitConn();
						streams();
						processConn();
					}
					catch(EOFException e){
						dispMessage("\nServer Terminated Conn\n");
					}
					finally {
						closeConn();
					}
				}
			}
			catch(IOException e) {
				
			}
		}
		private static void waitConn() throws IOException{
			dispMessage("Please wait...\n");
			conn = server.accept();
			dispMessage("New Player Has Joined The Room!!");
			
		}
		private static void streams() throws IOException {
			oos = new ObjectOutputStream(conn.getOutputStream());
			oos.flush();
			
			ois = new ObjectInputStream(conn.getInputStream());
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
				conn.close();
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
				gameScreen.chatScreen.append("\nError"); //jt is text area
				
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
}