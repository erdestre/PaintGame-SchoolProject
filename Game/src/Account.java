import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Account {
	static int aaa;
	public static String Account () {
		JTextField NicknameField = new JTextField();
		
		Object[] fields = {
				"Please Enter Your Nickname: ", NicknameField
		};
		UIManager.put("OptionPane.cancelButtonText", "Teacher");
		UIManager.put("OptionPane.okButtonText", "Student");
		aaa = JOptionPane.showConfirmDialog(null, fields, "THE GAME THAT SHOOK THE WORLD FROM STMP STUDIOS",
				JOptionPane.OK_CANCEL_OPTION);
		UIManager.put("OptionPane.cancelButtonText", "Cancel");
		UIManager.put("OptionPane.okButtonText", "Ok");
		String Nickname = NicknameField.getText();
		
		if (Nickname.isEmpty()) {
			Nickname = "Player";
		}
		return Nickname;
		
		}
	public static boolean permission () {
		if(Account.aaa == JOptionPane.CANCEL_OPTION) {
			return true; //Teacher
		}
		else return false;
	}
	}
		

