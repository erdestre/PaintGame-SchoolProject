import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class getNickname {
	static int aaa;
	public static String getNickname () {
		JTextField NicknameField = new JTextField();
		
		Object[] fields = {
				"Please Enter Your Nickname: ", NicknameField
		};
		Object[] selectType = {
				"Teacher",
				"Student"
		};
		UIManager.put("OptionPane.cancelButtonText", "Teacher");
		UIManager.put("OptionPane.okButtonText", "Student");
		aaa = JOptionPane.showConfirmDialog(null, fields, "THE GAME THAT SHOOK THE WORLD FROM STMP STUDIOS",
				JOptionPane.OK_CANCEL_OPTION);
		
		String Nickname = NicknameField.getText();
		
		if (Nickname.isEmpty()) {
			Nickname = "Player";
		}
		return Nickname;
		}
	public static boolean permission () {
		if(getNickname.aaa == JOptionPane.CANCEL_OPTION) {
			return true; //Teacher
		}
		else return false;
	}
	}
		

