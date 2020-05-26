import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.chrono.Era;

public class gameScreen extends JFrame implements KeyListener, MouseListener, ActionListener {
	Drawing d = new Drawing();
	static int counter = 0;
	boolean permission;
	String Answer;

	JFrame jf;

	JMenuBar jpToolbar;
	JPanel jpWhiteBoard;
	JPanel jpMenu;
	JPanel jpParticipant;
	JPanel jpChat;
	JLabel jlAnswer;
	JPanel JpTopmenu;


	BorderLayout blWhiteBoard;
	BorderLayout blMainScreen;
	BorderLayout blMenu;
	BorderLayout blButton;
	GridLayout glToolbar;

	static JLabel jlCounter;

	JTextArea participantScreen;
	JTextArea chatScreen;
	JTextField textField;

	JButton Line, Square, Circle, Pen, selectColor, Eraser, Pass;


	public void MainScreen() {
		jf = new JFrame("THE GAME THAT SHOOK THE WORLD FROM STMP STUDIOS");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1050,600);
		permission = Account.permission();
		ScreenPanels();
		jf.setVisible(true);


	}
	public void ScreenPanels() {
		blMainScreen = new BorderLayout();
		jf.setLayout(blMainScreen);
		WhiteBoard();
		Menu();

	}
	public void WhiteBoard() {

		jpWhiteBoard = new JPanel();
		jpWhiteBoard.setBackground(Color.white);
		Container content = jf.getRootPane();
		content.add(d);
		jf.add(jpWhiteBoard,BorderLayout.NORTH);
		jf.add(d);
		if (permission == true) {
			Toolbar();
			jlAnswer = new JLabel("YOUR WORD IS:  ");
			jpWhiteBoard.add(jlAnswer);
			setAnswer();
		}
		CounterLabel();
	}
	public void setAnswer () {
		Answer = getAnswer.getAnswer();
		jlAnswer.setText("Your Word Is: " + Answer);
	}
	public void CounterLabel() {
		jlCounter = new JLabel("Counter:  " + counter);
		jpWhiteBoard.add(jlCounter);
	}
	public static void setCounter() {
		counter++;
		jlCounter.setText("Counter:  " + counter);
	}
	public void Toolbar() {
		jpToolbar = new JMenuBar();
		glToolbar = new GridLayout(1,5);
		jpToolbar.setLayout(glToolbar);
		jpWhiteBoard.add(jpToolbar);

		Line = new JButton("Line");
		Line.addActionListener(this);
		Square = new JButton("Square");
		Square.addActionListener(this);
		Circle = new JButton("Circle");
		Circle.addActionListener(this);
		Pen = new JButton("Pen");
		Pen.addActionListener(this);
		Eraser = new JButton("Eraser");
		Eraser.addActionListener(this);
		selectColor = new JButton("Select Color");
		selectColor.addActionListener(this);
		Pass = new JButton("Pass");
		Pass.addActionListener(this);

		jpToolbar.add(Pen);
		jpToolbar.add(Line);
		jpToolbar.add(Square);
		jpToolbar.add(Circle);
		jpToolbar.add(Eraser);
		jpToolbar.add(selectColor);
		jpToolbar.add(Pass);
	}
	public void Menu() {
		jpMenu = new JPanel();
		JpTopmenu = new JPanel();
		blMenu= new BorderLayout();
		jpMenu.setLayout(blMenu);
		jf.add(jpMenu,BorderLayout.EAST);
		Participants();
		ChatPanel();
	}
	public void Participants() {
		jpParticipant = new JPanel();
		jpMenu.add(jpParticipant,BorderLayout.NORTH);
		jpParticipant.setBackground(Color.lightGray);
		participantScreen= new JTextArea("Players:\t\t\n");
		participantScreen.append(Source.Nickname+"\n");
		participantScreen.setEditable(false);
		jpParticipant.add(participantScreen);

	}

	public void ChatPanel() {
		jpChat = new JPanel();
		jpMenu.add(jpChat,BorderLayout.CENTER);
		blButton = new BorderLayout();
		jpChat.setLayout(blButton);
		chatScreen = new JTextArea();
		chatScreen.setEditable(false);
		jpChat.setBackground(Color.lightGray);
		jpChat.add(chatScreen);
		textField();
		Button();

	}
	public void Button() {
		JButton Hand= new JButton("");
		Image handIcon = new ImageIcon(this.getClass().getResource("HandIcon.png")).getImage();
		Hand.setIcon(new ImageIcon(handIcon));
		jpChat.add(Hand,BorderLayout.NORTH);
	}
	public void textField() {
		textField = new JTextField();
		jpMenu.add(textField,BorderLayout.SOUTH);
		textField.addKeyListener(this);
	}
	public void AnotherRound () {
		// Set Word
		setAnswer();
		// Reset Counter
		counter = -1;
		setCounter();
		// Reset Screen
		//
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Pen) {
			d.candraw = true;
		}
		else if (e.getSource() == Line) {
			d.candraw = false;

		}
		else if (e.getSource() == Square) {

		}
		else if (e.getSource() == Circle) {

		}
		else if (e.getSource() == selectColor) {
			d.Dcolor = JColorChooser.showDialog(this,"Select Color", Color.blue);
			repaint();
		}
		else if (e.getSource() == Pass) {
			AnotherRound();
		}
		else if (e.getSource() == Eraser)
		{

		}
		else {
			JOptionPane.showMessageDialog(null, "Something Went Wrong");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {


	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if (!textField.getText().isEmpty()) {
				chatScreen.append(Source.Nickname+": "+textField.getText()+"\n");
				textField.setText("");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}