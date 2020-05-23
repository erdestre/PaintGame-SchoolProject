import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class getAnswer {

	public static String getAnswer() {
		
		UIManager.put("OptionPane.cancelButtonText", "Cancel");
		UIManager.put("OptionPane.okButtonText", "Ok");
		JFrame f = new JFrame();
		String Answer = JOptionPane.showInputDialog(f, 
				"Select Your Word: ",
				"THE GAME THAT SHOOK THE WORLD FROM STMP STUDIOS",
				JOptionPane.INFORMATION_MESSAGE);
		
		if (Answer == null) {
			getAnswer();
		}
		return Answer;
		}
	}

