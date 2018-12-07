package ui.window;

import javax.swing.JFrame;

import config.FrameConfig;
import config.GameConfig;
import dto.GameDto;

public class JFrameGame extends JFrame {
	
	private JPanelGame panel;
	
	private GameDto dto;
	
	public JFrameGame(JPanelGame panel) {
		this.panel = panel;
		//��ȡ��Ϸ����
		FrameConfig fCfg = GameConfig.FRAME_CONFIG;
		//���ñ���
		this.setTitle(fCfg.getTitle());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(fCfg.getWidth(),fCfg.getHeight());
		this.setLocationRelativeTo(null);
		this.setContentPane(panel);
		this.setVisible(true);
	}
}
