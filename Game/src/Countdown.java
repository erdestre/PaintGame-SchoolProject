import java.util.Timer;
import java.util.TimerTask;



public class Countdown {
    public static int t;
    public static String time;
    public static void main() {
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        int i = 90;
        public void run(){
            if (i >= 0) {
               t = i--;
              time = String.format("%02d:%02d", t / 60, t % 60);
              System.out.println(time);
            }
        }
    };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }
    public void canceltimer()
    {

    }
}
