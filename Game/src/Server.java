import java.awt.*;
import java.net.*;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import java.io.*;


public class Server{
	
	static String Nickname;
	private static ObjectOutputStream oos;
	private static ObjectInputStream ois;
	private static ServerSocket server;
	private static Socket conn;
	public static boolean playerjoined =false;
	private static int flag;
		public Server() {
			
		}
		public static void runServer() {
			try {
				server = new ServerSocket(6666,100);
				while(true) {
					try {
						waitConn();
						streams();
						Participants();
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
		public static void drawdata() throws IOException{
			oos.writeByte(2);
			int info[] = new int[5];



		}
		private static void Participants() throws IOException{
			Nickname = Source.Nickname;
			oos.writeObject(Nickname);
			try {
				Nickname = (String) ois.readObject();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			gameScreen.participantScreen.append(Nickname);
		}
		private static void waitConn() throws IOException{
			dispMessage("Please wait...\n");
			conn = server.accept();
			dispMessage("New Player Has Joined The Room!!\n");
			playerjoined =true;
			
			
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
					
					flag = ois.readByte();
					switch(flag) {
						case 1:
							msg = (String) ois.readObject();
							dispMessage(msg);
							break;
						case 2:
							dispMessage(Nickname+" is answering...");
							sendAnswer(gameScreen.Answer);
							break;
						case 3:
							dispMessage(Nickname+" Has won");
							gameScreen.AnotherRound();
							break;
						case 4:
							dispMessage(Nickname+" Başaramadı");
							gameScreen.AnotherRound();
							break;
					}
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
				oos.writeByte(1);
				oos.writeObject(Nickname+": "+text+"\n");
				oos.flush();
				dispMessage(Nickname + ": " +text+"\n");
			}
			catch(IOException e){
				gameScreen.chatScreen.append("\nError"); 
				
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
		public static void sendpaintinfo(int m,int x,int y,int ox,int oy){
			int[] a = new int[5];
			try {

				a[0]=m;
				a[1]=x;
				a[2]=y;
				a[3]=ox;
				a[4]=oy;
				oos.writeByte(2);
				oos.writeObject(a);
				oos.flush();



			}
			catch (IOException e)
			{
				System.out.println("error at sending mouse cords: "+e);
			}
		}
		public static void sendpaintcolor(Color c)
		{
			try {
				oos.writeByte(3);
				oos.writeObject(c);
				oos.flush();
			}
			catch (IOException e)
			{
				System.out.println("error at sending mouse cords: "+e);
			}
		}
	public static void setCounter() {
		try {
			oos.writeByte(6);
			oos.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	public static void clearscreen()
	{
		try {
			oos.writeByte(4);

			oos.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public static void passButton() {
		try {
			oos.writeByte(7);
			oos.flush();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void sendAnswer(String Answer) {
		try {
			oos.writeByte(8);
			oos.writeObject(Answer);
			oos.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}