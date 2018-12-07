package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import dao.Data;

/**
 * ��Ϸ������
 * 
 * @author whz
 */
public class GameConfig {

	public static FrameConfig FRAME_CONFIG = null;

	private static DataConfig DATA_CONFIG = null;

	private static SystemConfig SYSTEM_CONFIG = null;

	static {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentDuilder;
		try {
			documentDuilder = factory.newDocumentBuilder();
			InputStream is = new FileInputStream("config/cfg.xml");
			Document doc = documentDuilder.parse(is);
			// �����������ö���
			FRAME_CONFIG = new FrameConfig(doc.getElementsByTagName("frame"));
			// ����ϵͳ����
			SYSTEM_CONFIG = new SystemConfig(doc.getElementsByTagName("system"));
			// �������ݷ������ö���
			DATA_CONFIG = new DataConfig(doc.getElementsByTagName("data"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ������˽�л� 
	 */
	private GameConfig() {

	}

	/**
	 * ��ô�������
	 * 
	 * @return
	 */
	public static FrameConfig getFrameConfig() {
		return FRAME_CONFIG;
	}

	/**
	 * ���ϵͳ����
	 * 
	 * @return
	 */
	public static SystemConfig getSystemConfig() {
		return SYSTEM_CONFIG;
	}

	/**
	 * ��ȡ���ݷ�������
	 * 
	 * @return
	 */
	public static DataConfig getDataConfig() {
		return DATA_CONFIG;
	}
	
}
