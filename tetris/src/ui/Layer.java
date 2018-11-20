package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 窗口类
 * @author whz
 *
 */
public class Layer {
	//窗口图片
	private static final Image LAY_IMG = new ImageIcon("graphics/window/window.png").getImage();
	//图片的宽度
	private static final int LAY_IMG_WIDTH = LAY_IMG.getWidth(null);
	//图片的高度
	private static final int LAY_IMG_HEIGHT = LAY_IMG.getWidth(null);
	//窗口四角的大小
	private static final int LAY_SIZE = 7;
	//窗口左上角的x坐标
	protected int x;
	//窗口左上角的y坐标
	protected int y ;
	//窗口的宽度
	protected int w;
	//窗口的高度
	protected int h;
	
	public Layer() {
	
	}
	
	public Layer(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w= w;
		this.h = h;
	}
	
	public void createWindow(Graphics g) {
		//up-left
		g.drawImage(LAY_IMG, x, y, x+LAY_SIZE, y+LAY_SIZE, 0, 0, LAY_SIZE, LAY_SIZE, null);
		//up-center
		g.drawImage(LAY_IMG, x+LAY_SIZE, y, w-LAY_SIZE+x, y+LAY_SIZE, LAY_SIZE, 0, LAY_IMG_WIDTH-LAY_SIZE, LAY_SIZE, null);
		//up-right
		g.drawImage(LAY_IMG,w-LAY_SIZE+x, y, w+x, y+LAY_SIZE, LAY_IMG_WIDTH-LAY_SIZE, 0, LAY_IMG_WIDTH, LAY_SIZE, null);
		//center-left
		g.drawImage(LAY_IMG, x, y+LAY_SIZE, x+LAY_SIZE, h-LAY_SIZE+y, 0, LAY_SIZE, LAY_SIZE, LAY_IMG_HEIGHT-LAY_SIZE, null);
		//center-center
		g.drawImage(LAY_IMG, x+LAY_SIZE, y+LAY_SIZE, x+w-LAY_SIZE, y+h-LAY_SIZE, LAY_SIZE, LAY_SIZE, LAY_IMG_WIDTH-LAY_SIZE, LAY_IMG_HEIGHT-LAY_SIZE, null);
		//center-right
		g.drawImage(LAY_IMG, x+w-LAY_SIZE, y+LAY_SIZE, x+w, h-LAY_SIZE+y, LAY_IMG_WIDTH-LAY_SIZE, LAY_SIZE, LAY_IMG_WIDTH, LAY_IMG_HEIGHT-LAY_SIZE, null);
		//down-left
		g.drawImage(LAY_IMG, x, y+h-LAY_SIZE, x+LAY_SIZE, h+y, 0, LAY_IMG_HEIGHT-LAY_SIZE, LAY_SIZE, LAY_IMG_HEIGHT, null);
		//down-down
		g.drawImage(LAY_IMG, x+LAY_SIZE, y+h-LAY_SIZE, x+w-LAY_SIZE, h+y, LAY_SIZE, LAY_IMG_HEIGHT-LAY_SIZE, LAY_IMG_WIDTH-LAY_SIZE, LAY_IMG_HEIGHT, null);
		//down-right
		g.drawImage(LAY_IMG, x+w-LAY_SIZE, y+h-LAY_SIZE, x+w, h+y, LAY_IMG_WIDTH-LAY_SIZE, LAY_IMG_HEIGHT-LAY_SIZE, LAY_IMG_WIDTH, LAY_IMG_HEIGHT, null);
	}
	
	public void paint(Graphics g) {}
}




















