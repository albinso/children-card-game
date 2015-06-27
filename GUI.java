import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class GUI extends JPanel implements KeyListener {
	public static void main(String[] args) {
		TheGame game = new TheGame();
		GUI g = new GUI(game);
		g.repaint();
		game.start();
	}

	VisualCard selected1, selected2;
	TheGame game;


	public GUI(TheGame game) {

		this.game = game;
		JFrame frame = new JFrame("Brooklyn Rage");
		GridLayout layout = new GridLayout(4, 5);
		this.setLayout(layout);
		setVisible(true);
		frame.setSize(500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(this);
		frame.setVisible(true);
		addKeyListener(this);
		requestFocus();

		for(int i=0; i < 4; i++) {
			for(int n=0; n < 5; n++) {
				this.add(new VisualCard(this, n, i));
			}
		}
		
	}


	public void paintComponent(Graphics g) {
		System.out.println("Drawing GUI");
		super.paintComponent(g);
	}

	public void execute() {
		String comm = toCommand();
		game.handleCommand(comm);
		this.selected1 = null;
		this.selected2 = null;
		update();
	}

	public void update() {
		for(int i=0; i < 4*5; i++) {
			((VisualCard)(this.getComponent(i))).setCard(null);
		}
		for(int i = 0; i < game.cardsInHand2.size(); i++) {
			((VisualCard)(this.getComponent(i))).setCard(game.cardsInHand2.get(i));
		}
		for(int i = 0; i < game.minionsOnBoard2.size(); i++) {
			((VisualCard)(this.getComponent(i+5))).setCard(game.minionsOnBoard2.get(i));
		}
		for(int i = 0; i < game.minionsOnBoard1.size(); i++) {
			((VisualCard)(this.getComponent(i+10))).setCard(game.minionsOnBoard1.get(i));
		}
		for(int i = 0; i < game.cardsInHand1.size(); i++) {
			((VisualCard)(this.getComponent(i+15))).setCard(game.cardsInHand1.get(i));
		}
		repaint();
	}

	public String toCommand() {
		if(selected1.getCard() instanceof MonsterCard) {
			return "play #" + selected1.getCol();
		}
		if(selected1.getCard() instanceof SpellCard) {
			return "cast #" + selected1.getCol();
		}
		if(selected1.getCard() instanceof Minion) {
			return "let #" + selected1.getCol() + " attack #" + selected2.getCol();
		}
		return "Eeeerror";
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			game.handleCommand("draw");
		}
		update();
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	
}