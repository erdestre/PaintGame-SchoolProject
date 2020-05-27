import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client extends javax.swing.JFrame{
	static ServerSocket ss;
	static Socket s;
	static DataInputStream din;
	static DataOutputStream dout;
	static String Message;
	public Client() {
		;
	}
	public static void main() {
		String msgin = "";
		try {
			ss = new ServerSocket(1201);
			s = ss.accept();
			din = new DataInputStream(s.getInputStream());
			dout = new DataOutputStream(s.getOutputStream());
			while(!msgin.equals("exit")) {
				msgin = din.readUTF();
				gameScreen.chatScreen.append(Message.trim()+"\n"+ msgin);
			
		}}
			catch(Exception e) {
			
		}
	}
public static void msg_send(String Msg) {
		Message = Msg;
}
public static void msg_receive(String Msg) {
		Message = Msg;
}
}
