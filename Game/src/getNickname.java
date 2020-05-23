import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class getNickname {
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
		JOptionPane.showConfirmDialog(null, fields, "THE GAME THAT SHOOK THE WORLD FROM STMP STUDIOS",JOptionPane.OK_CANCEL_OPTION);
		String Nickname = NicknameField.getText();
		
		return Nickname;
		}

}
