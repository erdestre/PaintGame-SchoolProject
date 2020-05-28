import java.net.*;
import java.io.*;


public class Server{
	private ServerSocket Ssocket;
	private Socket Csocket;
	private PrintWriter out;
	private BufferedReader in;

	public void start() {

		try {
			Ssocket = new ServerSocket(6666);
			Csocket = Ssocket.accept();
			out = new PrintWriter(Csocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(Csocket.getInputStream()));
			System.out.println("Server Started!");
		}
		catch (IOException ex)
		{
			System.out.println("error at starting:"+ex);
		}
	}
	public void stop()
	{
		try {
			in.close();
			out.close();
			Csocket.close();
			Ssocket.close();
		}
		catch (IOException ex)
		{
			System.out.println("error at stopping:"+ex);
		}

	}


}