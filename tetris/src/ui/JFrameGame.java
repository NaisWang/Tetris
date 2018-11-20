package ui;

import javax.swing.JFrame;

public class JFrameGame extends JFrame {
	public JFrameGame() {
		setTitle("java¶íÂÞË¹·½¿é");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1172, 675);
		this.setLocationRelativeTo(null);
		this.setContentPane(new JPanelGame());
		this.setVisible(true);
	}
}
