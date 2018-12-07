package ui;

import java.awt.Graphics;

public class LayerBackground extends Layer{
	
	public LayerBackground(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public void paint(Graphics g) {
		g.drawImage(Img.BG_LIST.get(2),0, 0,1162,654,null);	
	}
}
