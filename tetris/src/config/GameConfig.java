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
 * 游戏配置器
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
			// 创建界面配置对象
			FRAME_CONFIG = new FrameConfig(doc.getElementsByTagName("frame"));
			// 创建系统对象
			SYSTEM_CONFIG = new SystemConfig(doc.getElementsByTagName("system"));
			// 创建数据访问配置对象
			DATA_CONFIG = new DataConfig(doc.getElementsByTagName("data"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 构造器私有化 
	 */
	private GameConfig() {

	}

	/**
	 * 获得窗口配置
	 * 
	 * @return
	 */
	public static FrameConfig getFrameConfig() {
		return FRAME_CONFIG;
	}

	/**
	 * 获得系统配置
	 * 
	 * @return
	 */
	public static SystemConfig getSystemConfig() {
		return SYSTEM_CONFIG;
	}

	/**
	 * 获取数据访问配置
	 * 
	 * @return
	 */
	public static DataConfig getDataConfig() {
		return DATA_CONFIG;
	}
	
}
