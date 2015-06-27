import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

class VisualCard extends JPanel implements MouseListener {

	private Displayable card;
	private GUI gui;
	private int x, y;
	public VisualCard(GUI gui, int x, int y) {
		this.gui = gui;
		this.x = x;
		this.y = y;
		this.addMouseListener(this);
	}

	public Displayable getCard() {
		return card;
	}
	public void setCard(Displayable card) {
		this.card = card;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		if(gui.selected1 == this) {
			g.setColor(Color.RED);
		} else if(gui.selected1 == this) {
			g.setColor(Color.GREEN);
		}

		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		if(this.card != null) {
			//System.out.println("Painting card");
			Font f = g.getFont();
			f = scaleFontToFit(this.card.getVisRep(), this.getWidth()-4, g, f);
			g.setFont(f);
			drawString(g, this.card.getVisRep(), 3, 20);
		}
	}

	public int getRow() {
		return y;
	}

	public int getCol() {
		return x;
	}

	/*
	 * Scales the font to fit within a single line with the given width.
	 * Typ Newton-Raphson #numme
	 */
	public static Font scaleFontToFit(String text, int width, Graphics g, Font pFont)
	{
	    float fontSize = pFont.getSize();
	    float fWidth = g.getFontMetrics(pFont).stringWidth(text);
	    if(fWidth <= width)
	        return pFont;
	    fontSize = ((float)width / fWidth) * fontSize;
	    return pFont.deriveFont(fontSize);
	}

	private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }

	public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
       
    }

    public void mouseExited(MouseEvent e) {
       
    }

    public void mouseClicked(MouseEvent e) {
    	if(gui.selected1 == null) {
    		gui.selected1 = this;
    	} else if(gui.selected2 == null) {
    		gui.selected2 = this;
    		gui.execute();
    	}
    	repaint();
    }
}
