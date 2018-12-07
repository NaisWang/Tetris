package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import config.FrameConfig;
import config.GameConfig;
import dto.GameDto;

/**
 * ������
 * @author whz
 *
 */
public class Layer {
	
	/**
	 *ͼƬ�Ŀ�� 
	 */
	private static final int WINDOW_W= Img.WINDOW.getWidth(null);
	
	/**
	 * ͼƬ�ĸ߶�
	 */
	private static final int WINDOW_H = Img.WINDOW.getWidth(null);
	
	/**
	 * �ڱ߾� 
	 */
	protected static final int PADDING;
	
	/**
	 * ������
	 */
	protected static final int BORDER;
	
	/**
	 * �����ĽǵĴ�С
	 */
	private static final int LAY_SIZE = 7;
	
	/**
	 * �������Ͻǵ�x����
	 */
	protected int x;
	
	/**
	 * �������Ͻǵ�y����
	 */
	protected int y ;
	
	/**
	 * ���ڵĿ��
	 */
	protected int w;
	
	/**
	 * ���ڵĸ߶�
	 */
	protected int h;
	
	/**
	 * ��Ϸ����
	 */
	public GameDto dto = null;
	
	/**
	 * ����ͼƬ�Ŀ��
	 */
	protected static final int IMG_LV_W = Img.LV.getWidth(null);
	
	/**
	 * ������Ƭ�Ŀ��
	 */
	protected static final int IMG_NUMBER_W = Img.NUMBER.getWidth(null)/10;
	
	/**
	 * ������Ƭ�ĸ߶�
	 */
	protected static final int IMG_NUMBER_H = Img.NUMBER.getHeight(null);
	
	/**
	 * ������ֵ���߶ȣ�
	 */
	protected static final int IMG_RECT_H = Img.RECT.getHeight(null);

	/**
	 * ������ֵ����ȣ�
	 */
	private static final int IMG_RECT_W = Img.RECT.getWidth(null);
	
	/**
	 * ��ʼ��ֵ�۵Ŀ��
	 */
	private final int rectW;

	/**
	 * Ĭ������
	 */
	private static final Font DEF_FONT = new Font("����",Font.BOLD,20);
	
	static{
		//�����Ϸ����
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
	 * ��ʾ����
	 * 
	 * @param x ���Ͻ�x����
	 * @param y ���Ͻ�y����
	 * @param num Ҫ��ʾ������
	 * @param maxBit ����λ��
	 * @param g ���ʶ���
	 */
	protected void drawNunberLeftPad(int x, int y, int num, int maxBit, Graphics g) {
		//��Ҫ��ӡ������ת�����ַ���
		String strNum = Integer.toString(num);
		//ѭ�����������Ҷ���
		for(int i=0;i<maxBit;i++) {
			//�ж��Ƿ������������
			if(maxBit - i<=strNum.length()) {
				//����������ַ����е��±�
				int idx = i-maxBit + strNum.length();
				//������number�е�ÿһλȡ����
				int bit = strNum.charAt(idx)-'0';
				//��������
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
	 * ���ƿ��
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
	 * ����ֵ��
	 * 
	 * @param title
	 * @param number
	 * @param value
	 * @param maxValue
	 * @param g
	 */
	protected void drawRect(int y, String title, String number, double percent, Graphics g) {
		// ����ֵ�ĳ�ʼ��
		int rect_x = this.x + PADDING;
		int rect_y = this.y + y;
		// ���Ʊ���
		g.setColor(Color.BLACK);
		g.fillRect(rect_x, rect_y, this.rectW, IMG_RECT_H + 4);
		g.setColor(Color.WHITE);
		g.fillRect(rect_x + 1, rect_y + 1, this.rectW- 2, IMG_RECT_H + 2);
		g.setColor(Color.BLACK);
		g.fillRect(rect_x + 2, rect_y + 2, this.rectW - 4, IMG_RECT_H);
		// ������
		int w = (int) (percent * (this.rectW- 4));
		// �����ɫ
		int subIdx = (int) (percent * IMG_RECT_W);
		//����ֵ��
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
	   * ���л�ͼ
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




















