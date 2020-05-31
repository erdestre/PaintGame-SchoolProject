import java.awt.*;
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
	private static byte flag;
		public Client(String info) {
			srv = info;
			
		}
		public static void runClient()  {
			try {
				while(true) {
					try {
						
						connToS();
						streams();
						Participants();
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
		private static void Participants() throws IOException{
			String Nickname =Source.Nickname;
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
					flag = ois.readByte();
					switch(flag) {
						case 1:
						msg = (String) ois.readObject();
						dispMessage(msg);
						break;
						case 2:
							int[] a = (int[])ois.readObject();

							gameScreen.d.cdraw(a[0],a[1],a[2],a[3],a[4]);

							break;
						case 3:
							Color z = (Color) ois.readObject();


							gameScreen.d.Dcolor= z;
							break;
						case 4:
							gameScreen.d.clear();
							break;
						
						case 6:
							gameScreen.setCounter();
							break;
						case 7:
							gameScreen.counter = -1;
							gameScreen.setCounter();
							gameScreen.d.clear();
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
				client.close();
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
