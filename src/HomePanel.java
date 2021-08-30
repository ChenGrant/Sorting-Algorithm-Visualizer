import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class HomePanel extends JPanel{
	private int w;
	private int h;
	Image background = new ImageIcon("src\\resources\\images\\home.png").getImage();
	
	public HomePanel(int w, int h){
		this.w = w;
		this.h = h;
		this.setSize(w,h);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, w, h, this);
		repaint();
	}
}
