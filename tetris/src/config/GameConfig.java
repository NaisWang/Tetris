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
 * ��Ϸ������
 * 
 * @author whz
 */
public class GameConfig {

	/**
	 * ���ڿ��
	 */
	private int width;

	/**
	 * ���ڸ߶�
	 */
	private int height;

	/**
	 * �߿�ߴ�
	 */
	private int windowSize;

	/**
	 * �߿��ڱ߾�
	 */
	private int padding;

	/**
	 * ͼ������
	 */
	private List<LayerConfig> layerConfig;

	/**
	 * ���캯�� ��ȡXML�ļ�����ȡȫ����Ϸ����
	 * 
	 * @throws Exception
	 */
	public GameConfig() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentDuilder = factory.newDocumentBuilder();
		InputStream is = new FileInputStream("config/cfg.xml");
		Document doc = documentDuilder.parse(is);
		// ���ô��ڲ���
		NodeList frameList = doc.getElementsByTagName("frame");
		this.setUiConfig(frameList);
		// ���ϵͳ����
		NodeList systemList = doc.getElementsByTagName("system");
		this.setUiConfig(systemList);
		// ������ݷ��ʲ���
		NodeList dataList = doc.getElementsByTagName("data");
		this.setUiConfig(dataList);
	}

	/**
	 * @param frameList �����ļ�cfg.xml��frameԪ�صļ���
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
	 * �������ϵͳ����
	 * 
	 * @param systemList �����ļ�cfg.XML��systemԪ�صļ���
	 */
	private void setSystemConfig(NodeList systemList) {
		// TODO ����ϵͳ����
	}

	/**
	 * ����������ݷ��ʲ���
	 * 
	 * @param dataList �����ļ�cfg.XML��dataԪ�صļ���
	 */
	private void setDataConfig(NodeList dataList) {
		// TODO �������ݷ��ʲ���
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
