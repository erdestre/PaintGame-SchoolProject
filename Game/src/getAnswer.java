import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class getAnswer {

	public static String getAnswer() {
		
		UIManager.put("OptionPane.cancelButtonText", "Cancel");
		UIManager.put("OptionPane.okButtonText", "Ok");
		JFrame f = new JFrame();
		String Answer = JOptionPane.showInputDialog(f, 
				"Select Your Word: (Min 2, Max 15 characters)",
				"THE GAME THAT SHOOK THE WORLD FROM STMP STUDIOS",
				JOptionPane.INFORMATION_MESSAGE);
		if (Answer == null || Answer.length() >= 15 || Answer.length() < 2) {
			return getAnswer();
		}
		else return Answer;
		 
		}
	}
