import java.util.Timer;
import java.util.TimerTask;



public class Countdown {
	public static boolean Start = false;
	public static boolean restart = false;
    public static int t;
    public static String time;
    public static void main() {
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        int i = 90;
        public void run(){
        	if (restart == true) {
        		i = 90;
        		restart =false;
        	}
        	else if (i >= 0 && Start == true) {
               t = i--;
              time = String.format("%02d:%02d", t / 60, t % 60);
              gameScreen.Timer(time);
              Server.sendTimer(time);
            }
            else if(i<0) {
            	gameScreen.AnotherRound();
            }
            
        }
    };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
    public void canceltimer()
    {

    }
    public void seti() {
    	
    }
}