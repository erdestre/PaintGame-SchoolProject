import java.awt.*;
import java.awt.event.*;
import java.sql.Time;

import javax.swing.*;
import javax.swing.border.Border;

import java.time.chrono.Era;
import java.util.concurrent.TimeUnit;

public class gameScreen extends JFrame implements KeyListener, MouseListener, ActionListener {
	public static Drawing d = new Drawing();
	static int counter = 0;
	static boolean permission;
	static String Answer;

	JFrame jf;

	JMenuBar jpToolbar; 
	JPanel jpWhiteBoard;
	JPanel jpMenu;
	JPanel jpParticipant;
	JPanel jpChat;
	static JLabel jlAnswer;
	JLabel jlTimer;
	JPanel JpTopmenu;

	BorderLayout bljf;
	BorderLayout blWhiteBoard;
	BorderLayout blMainScreen;
	BorderLayout blMenu;
	BorderLayout blButton;
	GridLayout glToolbar;
	JPanel jpButton;
	GridLayout glButton;

	static JLabel jlCounter;

	static JTextArea participantScreen;
	static JTextArea chatScreen;
	JTextField textField;

	JButton Line, Square, Circle, Pen, selectColor, Eraser, Pass, Hand;


	public void MainScreen() {
		jf = new JFrame("THE GAME THAT SHOOK THE WORLD FROM STMP STUDIOS");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		permission = Account.permission();
		jf.setLayout(bljf);
		ScreenPanels();
		if (permission == true) {

			int length = Answer.length();
			jf.setSize(1050+length*5,600);
			jf.setVisible(true);
			Server.runServer();

			
		}
		else {
			
			jf.setSize(1050,600);
			jf.setVisible(true);
			Client.runClient();
		}
		/*jf.addComponentListener(new ComponentAdapter( ) {
			public void componentResized(ComponentEvent ev) {
			}
		});
*/

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
			d.mode =1;
			Toolbar();
			jlAnswer = new JLabel("YOUR WORD IS:  ");
			jpWhiteBoard.add(jlAnswer);
			setAnswer();
		}
		
		CounterLabel();
	}
	public static void setAnswer () {
		Answer = getAnswer.getAnswer();
		jlAnswer.setText("Your Word Is: " + Answer.toLowerCase() + " |");
	}
	public void CounterLabel() {
		jlCounter = new JLabel("Counter: " + counter);
		jpWhiteBoard.add(jlCounter);
	}
	public static void setCounter() {
		counter++;
		jlCounter.setText("Counter: " + counter);
		if (permission == true) {
			Server.setCounter();
		}
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
		Eraser = new JButton("Clear");
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
		participantScreen= new JTextArea("Players:\t\t\n",4,15);
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
		chatScreen.setWrapStyleWord(true);
		chatScreen.setLineWrap(true);
		add(new JScrollPane(chatScreen),BorderLayout.CENTER); //why it isn't working ???
		jpChat.setBackground(Color.lightGray);
		jpChat.add(chatScreen,BorderLayout.CENTER);
		textField();
		
		jpButton = new JPanel();
		if (permission == false) {
			Button();
		}
		else {
			glButton = new GridLayout(1,1);
			jpButton.setLayout(glButton);
			jpChat.add(jpButton,BorderLayout.NORTH);
			Time();
		}
	}
	public void Button() {
		glButton = new GridLayout(1,2);
		jpButton.setLayout(glButton);
		jpChat.add(jpButton,BorderLayout.NORTH);
		Hand= new JButton();
		Image handIcon = new ImageIcon(this.getClass().getResource("HandIcon.png")).getImage();
		Hand.setIcon(new ImageIcon(handIcon));
		jpButton.add(Hand);
		Hand.addActionListener(this);
		Time();
	}
	public void Time() {
		jlTimer = new JLabel("0 : 0");
		jpButton.add(jlTimer);
		
		
		
	}
	
	public void textField() {
		textField = new JTextField();
		jpMenu.add(textField,BorderLayout.SOUTH);
		textField.addKeyListener(this);
	}
	public static void AnotherRound() {
		// Set Word
		setAnswer();
		// Reset Counter
		counter = -1;
		setCounter();
		// Reset Screen
		d.clear();
		Server.passButton();
					
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Pen) {
			d.mode = 1;

			Eraser.setBackground(null);
			Pen.setBackground(Color.LIGHT_GRAY);
			Square.setBackground(null);
			Circle.setBackground(null);
			selectColor.setBackground(null);
			Line.setBackground(null);
		}
		else if (e.getSource() == Line) {
			d.mode = 2;
			Eraser.setBackground(null);
			Pen.setBackground(null);
			Square.setBackground(null);
			Circle.setBackground(null);
			selectColor.setBackground(null);
			Line.setBackground(Color.LIGHT_GRAY);

		}
		else if (e.getSource() == Square) {
		d.mode = 3;
			Eraser.setBackground(null);
			Pen.setBackground(null);
			Square.setBackground(Color.LIGHT_GRAY);
			Circle.setBackground(null);
			selectColor.setBackground(null);
			Line.setBackground(null);
		}
		else if (e.getSource() == Circle) {
		d.mode = 4;
			Eraser.setBackground(null);
			Pen.setBackground(null);
			Square.setBackground(null);
			Circle.setBackground(Color.LIGHT_GRAY);
			selectColor.setBackground(null);
			Line.setBackground(null);
		}
		else if (e.getSource() == selectColor) {
			Eraser.setBackground(null);
			Pen.setBackground(null);
			Square.setBackground(null);
			Circle.setBackground(null);
			selectColor.setBackground(Color.LIGHT_GRAY);
			Line.setBackground(null);
			Color temp;
			temp = JColorChooser.showDialog(this,"Select Color", Color.blue);
			d.Dcolor = temp;
			Server.sendpaintcolor(temp);
			selectColor.setBackground(null);

		}
		else if (e.getSource() == Pass) {
			AnotherRound();
		}
		else if (e.getSource() == Eraser)
		{
			Pen.setBackground(null);
			Square.setBackground(null);
			Circle.setBackground(null);
			selectColor.setBackground(null);
			Line.setBackground(null);

		int memory = d.mode;
		d.mode = 5;
		d.clear();
		d.mode = memory;
		Server.clearscreen();

		}
		else if (e.getSource() == Hand) {
			Client.Hand();
			String Answer = JOptionPane.showInputDialog("Write Your Answer: ");
			Client.Answer(Answer);
			
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
				if (permission==true){
					Server.send(Source.Nickname, textField.getText());
				}
				else {
					Client.send(Source.Nickname, textField.getText());
				}
				textField.setText("");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
