package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import config.FrameConfig;
import config.GameConfig;
import dto.GameDto;

/**
 * 窗口类
 * @author whz
 *
 */
public class Layer {
	
	/**
	 *图片的宽度 
	 */
	private static final int WINDOW_W= Img.WINDOW.getWidth(null);
	
	/**
	 * 图片的高度
	 */
	private static final int WINDOW_H = Img.WINDOW.getWidth(null);
	
	/**
	 * 内边距 
	 */
	protected static final int PADDING;
	
	/**
	 * 方块宽度
	 */
	protected static final int BORDER;
	
	/**
	 * 窗口四角的大小
	 */
	private static final int LAY_SIZE = 7;
	
	/**
	 * 窗口左上角的x坐标
	 */
	protected int x;
	
	/**
	 * 窗口左上角的y坐标
	 */
	protected int y ;
	
	/**
	 * 窗口的宽度
	 */
	protected int w;
	
	/**
	 * 窗口的高度
	 */
	protected int h;
	
	/**
	 * 游戏数据
	 */
	public GameDto dto = null;
	
	/**
	 * 标题图片的宽度
	 */
	protected static final int IMG_LV_W = Img.LV.getWidth(null);
	
	/**
	 * 数字切片的宽度
	 */
	protected static final int IMG_NUMBER_W = Img.NUMBER.getWidth(null)/10;
	
	/**
	 * 数字切片的高度
	 */
	protected static final int IMG_NUMBER_H = Img.NUMBER.getHeight(null);
	
	/**
	 * 矩形增值（高度）
	 */
	protected static final int IMG_RECT_H = Img.RECT.getHeight(null);

	/**
	 * 矩形增值（宽度）
	 */
	private static final int IMG_RECT_W = Img.RECT.getWidth(null);
	
	/**
	 * 初始化值槽的宽度
	 */
	private final int rectW;

	/**
	 * 默认字体
	 */
	private static final Font DEF_FONT = new Font("黑体",Font.BOLD,20);
	
	static{
		//获得游戏配置
		FrameConfig fCfg = GameConfig.FRAME_CONFIG;
		PADDING = fCfg.getPadding();
		BORDER = fCfg.getBorder();
	}
	
	public Layer(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w= w;
		this.h = h;
		this.rectW = this.w- (PADDING<<1);
	}
	
	public void setDto(GameDto dto) {
		this.dto = dto;
	}

	/**
	 * 显示数字
	 * 
	 * @param x 左上角x坐标
	 * @param y 左上角y坐标
	 * @param num 要显示的数字
	 * @param maxBit 数字位数
	 * @param g 画笔对象
	 */
	protected void drawNunberLeftPad(int x, int y, int num, int maxBit, Graphics g) {
		//把要打印的数字转换成字符串
		String strNum = Integer.toString(num);
		//循环绘制数字右对齐
		for(int i=0;i<maxBit;i++) {
			//判断是否满足绘制条件
			if(maxBit - i<=strNum.length()) {
				//获得数字再字符串中的下标
				int idx = i-maxBit + strNum.length();
				//把数字number中的每一位取出来
				int bit = strNum.charAt(idx)-'0';
				//绘制数字
				g.drawImage(Img.NUMBER, 
						this.x+x+IMG_NUMBER_W*i,
						this.y+y,
						this.x+x+IMG_NUMBER_W*(i+1),
						this.y+y+IMG_NUMBER_H, 
						bit*IMG_NUMBER_W, 
						0, (bit+1)*IMG_NUMBER_W, IMG_NUMBER_H, null);
			}
		}
	}
	
	/**
	 * 绘制框架
	 * 
	 * @param g
	 */
	public void createWindow(Graphics g) {
		//up-left
		g.drawImage(Img.WINDOW, x, y, x+LAY_SIZE, y+LAY_SIZE, 0, 0, LAY_SIZE, LAY_SIZE, null);
		//up-center
		g.drawImage(Img.WINDOW, x+LAY_SIZE, y, w-LAY_SIZE+x, y+LAY_SIZE, LAY_SIZE, 0, WINDOW_W-LAY_SIZE, LAY_SIZE, null);
		//up-right
		g.drawImage(Img.WINDOW,w-LAY_SIZE+x, y, w+x, y+LAY_SIZE, WINDOW_W-LAY_SIZE, 0, WINDOW_W, LAY_SIZE, null);
		//center-left
		g.drawImage(Img.WINDOW, x, y+LAY_SIZE, x+LAY_SIZE, h-LAY_SIZE+y, 0, LAY_SIZE, LAY_SIZE, WINDOW_H-LAY_SIZE, null);
		//center-center
		g.drawImage(Img.WINDOW, x+LAY_SIZE, y+LAY_SIZE, x+w-LAY_SIZE, y+h-LAY_SIZE, LAY_SIZE, LAY_SIZE, WINDOW_W-LAY_SIZE, WINDOW_H-LAY_SIZE, null);
		//center-right
		g.drawImage(Img.WINDOW, x+w-LAY_SIZE, y+LAY_SIZE, x+w, h-LAY_SIZE+y, WINDOW_W-LAY_SIZE, LAY_SIZE, WINDOW_W, WINDOW_H-LAY_SIZE, null);
		//down-left
		g.drawImage(Img.WINDOW, x, y+h-LAY_SIZE, x+LAY_SIZE, h+y, 0, WINDOW_H-LAY_SIZE, LAY_SIZE, WINDOW_H, null);
		//down-down
		g.drawImage(Img.WINDOW, x+LAY_SIZE, y+h-LAY_SIZE, x+w-LAY_SIZE, h+y, LAY_SIZE, WINDOW_H-LAY_SIZE, WINDOW_W-LAY_SIZE, WINDOW_H, null);
		//down-right
		g.drawImage(Img.WINDOW, x+w-LAY_SIZE, y+h-LAY_SIZE, x+w, h+y, WINDOW_W-LAY_SIZE, WINDOW_H-LAY_SIZE, WINDOW_W, WINDOW_H, null);
	}
	
	public void paint(Graphics g) {}
	
	/**
	 * 绘制值槽
	 * 
	 * @param title
	 * @param number
	 * @param value
	 * @param maxValue
	 * @param g
	 */
	protected void drawRect(int y, String title, String number, double percent, Graphics g) {
		// 各种值的初始化
		int rect_x = this.x + PADDING;
		int rect_y = this.y + y;
		// 绘制背景
		g.setColor(Color.BLACK);
		g.fillRect(rect_x, rect_y, this.rectW, IMG_RECT_H + 4);
		g.setColor(Color.WHITE);
		g.fillRect(rect_x + 1, rect_y + 1, this.rectW- 2, IMG_RECT_H + 2);
		g.setColor(Color.BLACK);
		g.fillRect(rect_x + 2, rect_y + 2, this.rectW - 4, IMG_RECT_H);
		// 求出宽度
		int w = (int) (percent * (this.rectW- 4));
		// 求出颜色
		int subIdx = (int) (percent * IMG_RECT_W);
		//绘制值槽
		g.drawImage(Img.RECT, rect_x + 2, rect_y + 2, rect_x + 2 + w, rect_y + 2 + IMG_RECT_H, subIdx, 0, subIdx + 1,
				IMG_RECT_H, null);
		g.setColor(Color.WHITE);
		g.setFont(DEF_FONT);
		g.drawString(title, rect_x+4, rect_y+ 23);
		if(number != null) {
			g.drawString(number, rect_x+100, rect_y+22);
		}
	}
	
	/** 
	   * 正中绘图
	   * 
	   * @Title: drawImageAtCenter
	   * @Description:
	   * @param image
	   * @param g void
	   */
	  protected void drawImageAtCenter(Image image, Graphics g) {
	    int imgW = image.getWidth(null);
	    int imgH = image.getHeight(null);
	    g.drawImage(image, this.x + (this.w - imgW >> 1), this.y + (this.h - imgH >> 1), null);
	  }
}




















