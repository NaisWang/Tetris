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

/**
 * 游戏配置器
 * 
 * @author whz
 */
public class GameConfig {

	/**
	 * 窗口宽度
	 */
	private int width;

	/**
	 * 窗口高度
	 */
	private int height;

	/**
	 * 边框尺寸
	 */
	private int windowSize;

	/**
	 * 边框内边距
	 */
	private int padding;

	/**
	 * 图层属性
	 */
	private List<LayerConfig> layerConfig;

	/**
	 * 构造函数 读取XML文件，获取全部游戏配置
	 * 
	 * @throws Exception
	 */
	public GameConfig() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentDuilder = factory.newDocumentBuilder();
		InputStream is = new FileInputStream("config/cfg.xml");
		Document doc = documentDuilder.parse(is);
		// 配置窗口参数
		NodeList frameList = doc.getElementsByTagName("frame");
		this.setUiConfig(frameList);
		// 获得系统参数
		NodeList systemList = doc.getElementsByTagName("system");
		this.setUiConfig(systemList);
		// 获得数据访问参数
		NodeList dataList = doc.getElementsByTagName("data");
		this.setUiConfig(dataList);
	}

	/**
	 * @param frameList 配置文件cfg.xml中frame元素的集合
	 */
	private void setUiConfig(NodeList frameList) {
		Node frame = frameList.item(1);
		NamedNodeMap frameAttributesMap = frame.getAttributes();
		this.width = Integer.parseInt(frameAttributesMap.getNamedItem("width").getNodeValue());
		this.height = Integer.parseInt(frameAttributesMap.getNamedItem("height").getNodeValue());
		this.windowSize = Integer.parseInt(frameAttributesMap.getNamedItem("windowSize").getNodeValue());
		this.padding = Integer.parseInt(frameAttributesMap.getNamedItem("padding").getNodeValue());
		NodeList childOfFrame = frame.getChildNodes();
		Node layer;
		layerConfig = new LinkedList();
		LayerConfig lc = null;
		for (int i = 0; i < childOfFrame.getLength(); i++) {
			layer = childOfFrame.item(i);
			NamedNodeMap layerAttributes = layer.getAttributes();
			lc = new LayerConfig(layerAttributes.getNamedItem("className").getNodeValue(),
					Integer.parseInt(layerAttributes.getNamedItem("x").getNodeValue()),
					Integer.parseInt(layerAttributes.getNamedItem("y").getNodeValue()),
					Integer.parseInt(layerAttributes.getNamedItem("w").getNodeValue()),
					Integer.parseInt(layerAttributes.getNamedItem("h").getNodeValue()));
			layerConfig.add(lc);
		}
	}

	/**
	 * 获得配置系统参数
	 * 
	 * @param systemList 配置文件cfg.XML中system元素的集合
	 */
	private void setSystemConfig(NodeList systemList) {
		// TODO 配置系统参数
	}

	/**
	 * 获得配置数据访问参数
	 * 
	 * @param dataList 配置文件cfg.XML中data元素的集合
	 */
	private void setDataConfig(NodeList dataList) {
		// TODO 配置数据访问参数
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getWindowSize() {
		return windowSize;
	}

	public int getPadding() {
		return padding;
	}

	public List<LayerConfig> getLayerConfig() {
		return layerConfig;
	}
}
