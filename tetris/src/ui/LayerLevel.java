package ui;

import java.awt.Graphics;

public class LayerLevel extends Layer{

	
	public LayerLevel(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public void paint(Graphics g) {
		this.createWindow(g);
		//���ڱ���
		int centerX = this.w-IMG_LV_W>>1;
		g.drawImage(Img.LV, this.x +centerX, this.y+15, null);
		//��ʾ�ȼ�
		this.drawNunberLeftPad(centerX, 64, this.dto.getNowLevel(), 2, g);
	}
	
		
}











