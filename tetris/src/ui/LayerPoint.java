package ui;

import java.awt.Graphics;

import config.GameConfig;

public class LayerPoint extends Layer {

	/**
	 * ����y����
	 */
	private final int pointY;

	/**
	 * ��������
	 */
	private static final int LEVEL_UP = GameConfig.getSystemConfig().getLevelUp();

	/**
	 * ����y����
	 */
	private final int rmlineY;

	/**
	 * �������λ��
	 */
	private static final int POINT_BIT = 5;

	/**
	 * ����x����
	 */
	private int comX = 0;

	/**
	 * ����ֵy����
	 */
	private final int expY;

	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		// ��ʼ����ͨx����
		this.comX = this.w - IMG_NUMBER_W * POINT_BIT - PADDING;
		// ��ʼ��������ʾ��y����
		this.pointY = PADDING;
		// ��ʼ��������ʾ��y����
		this.rmlineY = this.pointY + Img.POINT.getHeight(null) + PADDING;
		// ��ʼ������ֵ��ʾ��y����
		this.expY = this.rmlineY + Img.RMLINE.getHeight(null) + PADDING;
	}

	public void paint(Graphics g) {
		this.createWindow(g);
		// ���ڱ��⣨������
		g.drawImage(Img.POINT, this.x + PADDING, this.y + pointY, null);
		this.drawNunberLeftPad(158, pointY, this.dto.getNowPoint(), 5, g);
		// ���ڱ��⣨���У�
		g.drawImage(Img.RMLINE, this.x + PADDING, this.y + rmlineY, null);
		this.drawNunberLeftPad(158, rmlineY, this.dto.getNowRemoveLine(), 5, g);
		// ����ֵ�ۣ�����ֵ��
		int rmLine = this.dto.getNowRemoveLine();
		this.drawRect(expY, "��һ��", null, (double)(rmLine&LEVEL_UP)/(double)LEVEL_UP, g);

	}
	
}
