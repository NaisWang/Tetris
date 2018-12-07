package ui;

import java.awt.Graphics;

import config.GameConfig;

public class LayerPoint extends Layer {

	/**
	 * 分数y坐标
	 */
	private final int pointY;

	/**
	 * 升级行数
	 */
	private static final int LEVEL_UP = GameConfig.getSystemConfig().getLevelUp();

	/**
	 * 消行y坐标
	 */
	private final int rmlineY;

	/**
	 * 分数最大位数
	 */
	private static final int POINT_BIT = 5;

	/**
	 * 消行x坐标
	 */
	private int comX = 0;

	/**
	 * 经验值y坐标
	 */
	private final int expY;

	public LayerPoint(int x, int y, int w, int h) {
		super(x, y, w, h);
		// 初始化共通x坐标
		this.comX = this.w - IMG_NUMBER_W * POINT_BIT - PADDING;
		// 初始化分数显示的y坐标
		this.pointY = PADDING;
		// 初始化消行显示的y坐标
		this.rmlineY = this.pointY + Img.POINT.getHeight(null) + PADDING;
		// 初始化经验值显示的y坐标
		this.expY = this.rmlineY + Img.RMLINE.getHeight(null) + PADDING;
	}

	public void paint(Graphics g) {
		this.createWindow(g);
		// 窗口标题（分数）
		g.drawImage(Img.POINT, this.x + PADDING, this.y + pointY, null);
		this.drawNunberLeftPad(158, pointY, this.dto.getNowPoint(), 5, g);
		// 窗口标题（消行）
		g.drawImage(Img.RMLINE, this.x + PADDING, this.y + rmlineY, null);
		this.drawNunberLeftPad(158, rmlineY, this.dto.getNowRemoveLine(), 5, g);
		// 绘制值槽（经验值）
		int rmLine = this.dto.getNowRemoveLine();
		this.drawRect(expY, "下一级", null, (double)(rmLine&LEVEL_UP)/(double)LEVEL_UP, g);

	}
	
}
