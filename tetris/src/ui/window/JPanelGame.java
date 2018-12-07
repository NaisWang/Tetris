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
		//������Ϸ������
		this.gameControl = gameControl;
		//���ò��ֹ�����Ϊ���ɲ���
		this.setLayout(null);
		//��ʼ�����
		this.initComponent();
		//��ʼ����
		this.initLayer(dto);
		//��װ���̼�����
		this.addKeyListener(new PlayerControl(gameControl));
	}
	
	/**
	 * ��װ��Ϸ������
	 * @param control
	 */
	public void setGameControl(PlayerControl control) {
		this.addKeyListener(control);
	}
	
	/**
	  * ��ʼ����� 
	 */
	public void initComponent() {
		//��ʼ����ʼ��ť
		this.btnStart = new JButton(Img.BTN_START);
		//���ÿ�ʼ��ťλ��
		btnStart.setBounds(
				GameConfig.getFrameConfig().getButtonConfig().getStartX(), 
				GameConfig.getFrameConfig().getButtonConfig().getStartY(),
				BIN_SIZE_W, BIN_SIZE_H);
		//����ʼ��ť����¼�
		this.btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.start();
			}
		});
		//��ӿ�ʼ��ť�����
		this.add(btnStart);
		//��ʼ�����ð�ť
		this.btnConfig = new JButton(Img.BTN_CONFIG);
		//�������ð�ťλ��
		btnConfig.setBounds(
				GameConfig.getFrameConfig().getButtonConfig().getUserConfigX(), 
				GameConfig.getFrameConfig().getButtonConfig().getUserConfigY(),
				BIN_SIZE_W, BIN_SIZE_H);
		//�����ð�ť����¼�
		this.btnConfig.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameControl.showUserConfig();
			}
		});
		//������ð�ť�����
		this.add(btnConfig);
	}
	
	/**
	  * ��ʼ����
	 */
	public void initLayer(GameDto dto) {
		// �����Ϸ����
		FrameConfig fCfg = GameConfig.getFrameConfig();
		// ��ò�����
		List<LayerConfig> layersCfg = fCfg.getLayerConfig();
		// ������Ϸ������
		layers = new LinkedList<Layer>();
		// �������в����
		for (LayerConfig layerCfg : layersCfg) {
			try {
				// ��������
				System.out.println(layerCfg.getClassName());
				Class<?> cls = Class.forName(layerCfg.getClassName());
				System.out.println("fsdf");
				// ��ù��캯��
				Constructor<?> ctr = cls.getConstructor(int.class, int.class, int.class, int.class);
				// ���ù��캯����������
				Layer layer = (Layer) ctr.newInstance(layerCfg.getX(), layerCfg.getY(), layerCfg.getW(), layerCfg.getH());
				layer.setDto(dto);
				// �Ѵ����õ�Layer������뼯��
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
	 * ���ư�ť�Ƿ�ɵ��
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
