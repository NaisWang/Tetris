package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class LayerDatabase extends Layer{

	private static final int PADDING = 15;
	private static final Image IMG_LAY_DATA = new ImageIcon("graphics/string/Êý¾Ý¿â×ÖÌå.png").getImage();
	
	public LayerDatabase(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public void paint(Graphics g) {
		this.createWindow(g);
		g.drawImage(IMG_LAY_DATA, this.x+PADDING, this.y+PADDING, null);
	}
}
