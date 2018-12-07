package ui.window;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import config.FrameConfig;
import config.GameConfig;
import config.LayerConfig;
import control.GameControl;
import control.PlayerControl;
import dto.GameDto;
import ui.Img;
import ui.Layer;

public class JPanelGame extends JPanel {
	
	private static final int BIN_SIZE_W = GameConfig.getFrameConfig().getButtonConfig().getButtonW();
	
	private static final int BIN_SIZE_H = GameConfig.getFrameConfig().getButtonConfig().getButtonH();

	private LinkedList<Layer> layers = null;
	
	private JButton btnStart;
	
	private JButton btnConfig;
	
	private GameControl gameControl = null;

	public JPanelGame(GameControl gameControl,  GameDto dto) {
		//连接游戏控制器
		this.gameControl = gameControl;
		//设置布局管理器为自由布局
		this.setLayout(null);
		//初始化组件
		this.initComponent();
		//初始化层
		this.initLayer(dto);
		//安装键盘监听器
		this.addKeyListener(new PlayerControl(gameControl));
	}
	
	/**
	 * 安装游戏控制器
	 * @param control
	 */
	public void setGameControl(PlayerControl control) {
		this.addKeyListener(control);
	}
	
	/**
	  * 初始化组件 
	 */
	public void initComponent() {
		//初始化开始按钮
		this.btnStart = new JButton(Img.BTN_START);
		//设置开始按钮位置
		btnStart.setBounds(
				GameConfig.getFrameConfig().getButtonConfig().getStartX(), 
				GameConfig.getFrameConfig().getButtonConfig().getStartY(),
				BIN_SIZE_W, BIN_SIZE_H);
		//给开始按钮添加事件
		this.btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.start();
			}
		});
		//添加开始按钮到面板
		this.add(btnStart);
		//初始化设置按钮
		this.btnConfig = new JButton(Img.BTN_CONFIG);
		//设置设置按钮位置
		btnConfig.setBounds(
				GameConfig.getFrameConfig().getButtonConfig().getUserConfigX(), 
				GameConfig.getFrameConfig().getButtonConfig().getUserConfigY(),
				BIN_SIZE_W, BIN_SIZE_H);
		//给设置按钮添加事件
		this.btnConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.showUserConfig();
			}
		});
		//添加设置按钮到面板
		this.add(btnConfig);
	}
	
	/**
	  * 初始化层
	 */
	public void initLayer(GameDto dto) {
		// 获得游戏配置
		FrameConfig fCfg = GameConfig.getFrameConfig();
		// 获得层配置
		List<LayerConfig> layersCfg = fCfg.getLayerConfig();
		// 创建游戏层数组
		layers = new LinkedList<Layer>();
		// 创建所有层对象
		for (LayerConfig layerCfg : layersCfg) {
			try {
				// 获得类对象
				System.out.println(layerCfg.getClassName());
				Class<?> cls = Class.forName(layerCfg.getClassName());
				System.out.println("fsdf");
				// 获得构造函数
				Constructor<?> ctr = cls.getConstructor(int.class, int.class, int.class, int.class);
				// 调用构造函数创建对象
				Layer layer = (Layer) ctr.newInstance(layerCfg.getX(), layerCfg.getY(), layerCfg.getW(), layerCfg.getH());
				layer.setDto(dto);
				// 把创建好的Layer对象放入集合
				layers.add(layer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < layers.size(); i++) {
			layers.get(i).paint(g);
		}
		this.requestFocus();
	}

	/**
	 * 控制按钮是否可点击
	 * 
	 * @param onOff
	 */
	public void buttonSwith(boolean onOff) {
		this.btnConfig.setEnabled(onOff);
		this.btnStart.setEnabled(onOff);
	}
	
	public void setGameControl(GameControl gameControl) {
		this.gameControl = gameControl;
	}
	
}
