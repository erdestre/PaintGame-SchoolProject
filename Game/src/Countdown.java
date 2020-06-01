import java.util.Timer;
import java.util.TimerTask;



public class Countdown {
	public static boolean Start = false;
    public static int t;
    public static String time;
    public static void main() {
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        int i = 90;
        public void run(){
            if (i >= 0 && Start == true) {
               t = i--;
              time = String.format("%02d:%02d", t / 60, t % 60);
              gameScreen.Timer(time);
              Server.sendTimer(time);
            }
        }
    };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
    public void canceltimer()
    {

    }
}