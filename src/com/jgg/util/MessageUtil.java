package com.jgg.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.jgg.po.TextMessage;
import com.thoughtworks.xstream.XStream;



/*
 * ��Ϣת��
 */
public class MessageUtil {
	
	/*
	 * ��Ϣ����
	 */
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_EVENT = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	
	/**
	 * xmlת��map����
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String,String> xmlTomap(HttpServletRequest request) throws IOException, DocumentException{
		Map<String,String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		
		Element root = doc.getRootElement();
		
		List<Element> list = root.elements();
		
		for(Element e : list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	
	
	/**
	 * ���ı���Ϣ����תΪxml
	 * @param textMessages
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	
	public static String initText(String toUserName,String fromUserName,String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return textMessageToXml(text);
	}
	
	
	/**
	 * ���˵�
	 * @return
	 */
	public static String menuText(){
		StringBuffer sb = new StringBuffer();
		sb.append("��ӭ���Ĺ�ע���Ѿ��Թ��ںŽ������ģ�\n\n");
		sb.append("�ظ�1�����ڱ����ںŵĽ���\n");
		sb.append("�ظ�2�����ڹ��ںſ����Ľ���\n");
		sb.append("�ظ�0�������˲˵���");
		return sb.toString();
	}
	
	public static String firstText(){
		StringBuffer sb = new StringBuffer();
		sb.append("�����ں�������2017��11��15�տ�ͨ�������˽�΢�Ź��ںź�˽��뿪�����ܸ������ܹ�ע�����������������¶�������Ϊ����΢�ź�̨���������Ի�Ƚ�����");
		return sb.toString();
	}
	public static String secondText(){
		StringBuffer sb = new StringBuffer();
		sb.append("�������и��Լ��Ĺ��ںŵ������ǣ���ʵ����ܼ򵥣��ٶ�΢�Ź���ƽ̨���ݲ���һ��һ�����,�������ﲻ��׸��������Ҫ��������ô���뿪����������Ҫ����ӳ�乤�ߣ����õ���natapp��Ȼ�󿪷�ide�õ���eclipse���Ʒ���׼��ʹ��sae(SinaAppEngine,�������ðٶȵ�bae,��ô�ٶȵ�ˢ����֤̫�ӣ������˺ܶ�ζ�˵���Ǳ��ˡ�����)�������Ͳ�����һֱ���ط������ˡ���Ŀ��������GitHub�ϡ���ʵ����Ҳ����ȥ�˽�İɣ�������΢�ź�̨�Ϳ��������ܶ���Ϣ�����ˣ�ӵ��һ���Լ��Ĺ��ںŻ��Ǻܺ����Ŷ��");
		return sb.toString();
	}
	public static String fantanText(){
		StringBuffer sb = new StringBuffer();
		sb.append("����������");
		return sb.toString();
	}public static String kuazanText(){
		StringBuffer sb = new StringBuffer();
		sb.append("лл������");
		return sb.toString();
	}
}
