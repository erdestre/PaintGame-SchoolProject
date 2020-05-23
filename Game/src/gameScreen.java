import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class gameScreen extends JFrame implements KeyListener, MouseListener, ActionListener {
	JFrame jf;
	BorderLayout blMainScreen;
	GridLayout blScreenMenu;
	BorderLayout blChatPanel;
	BorderLayout blButton;
	JLabel jlCounter; 
	JTextField textField;
	JPanel jpWhitePanel;
	JPanel jpScreenMenu;
	JPanel jpMenuPanel;
	JPanel jpParticipantPanel;
	JPanel jpChatPanel;
	
	
	
	public void gameScreen() {
		jf = new JFrame("IT IS THE GAME THAT SHOOK THE WORLD FROM STMP STUDIOS");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(850,500);
		Screen();
		jf.setVisible(true);
		
		
	}
	
	public void Screen() {
		blMainScreen = new BorderLayout();
		jf.setLayout(blMainScreen);
		whiteScreen();
		Menu();
		
	}
	public void whiteScreen() {
		jpWhitePanel = new JPanel();
		jpWhitePanel.setBackground(Color.white);
		ScreenMenu();
		jf.add(jpWhitePanel);
	}
	public void ScreenMenu() {
		jpScreenMenu = new JPanel();
		blScreenMenu = new GridLayout();
		jpScreenMenu.setLayout(blScreenMenu);
		jpWhitePanel.add(jpScreenMenu);
		JButton Square = new JButton("Square");
		JButton Circle = new JButton("Circle");
		JButton Pen = new JButton("Pen");
		JButton selectColor = new JButton("Select Color");
		jpScreenMenu.add(Pen);
		jpScreenMenu.add(Square);
		jpScreenMenu.add(Circle);
		jpScreenMenu.add(selectColor);
	}
	public void Menu() {
		jpMenuPanel = new JPanel();
		blChatPanel = new BorderLayout();
		jpMenuPanel.setLayout(blChatPanel);
		jf.add(jpMenuPanel,BorderLayout.EAST);
		Participants();
		Chat();
	}
	public void Participants() {
		jpParticipantPanel = new JPanel();
		jpMenuPanel.add(jpParticipantPanel,BorderLayout.NORTH);
		jpParticipantPanel.setBackground(Color.LIGHT_GRAY);
		JButton triv = new JButton("Participants");
		jpParticipantPanel.add(triv);
		 
	}
	public void Chat() {
		jpChatPanel = new JPanel();
		jpChatPanel.setBackground(Color.LIGHT_GRAY);
		jpMenuPanel.add(jpChatPanel);
		blButton = new BorderLayout();
		jpChatPanel.setLayout(blButton);
		JButton hand= new JButton("");
		Image handIcon = new ImageIcon(this.getClass().getResource("HandIcon.png")).getImage();
		hand.setIcon(new ImageIcon(handIcon));
		JLabel chatScreen = new JLabel("Chat");
		jpChatPanel.setBackground(Color.lightGray);
		jpChatPanel.add(hand,BorderLayout.NORTH);
		jpChatPanel.add(chatScreen);
		textField();
		
	}
	public void textField() {
		textField = new JTextField();
		jpMenuPanel.add(textField,BorderLayout.SOUTH);
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
