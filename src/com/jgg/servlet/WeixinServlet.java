package com.jgg.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jgg.po.TextMessage;
import com.jgg.util.CheckUtil;
import com.jgg.util.MessageUtil;

public class WeixinServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		
		PrintWriter out = resp.getWriter();
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			Map<String, String> map = MessageUtil.xmlTomap(req);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			
			String message = null;
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
				if("1".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstText());
				}else if("2".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.secondText());
				}else if("0".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}else if("sb".equals(content)||"Éµ±Æ".equals(content)||"É·±Ê".equals(content)||"ÉµŒÅ".equals(content)||"ÄãÃÃ".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.fantanText());
				}else if("Å£±Æ".equals(content)||"Å£±È".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.kuazanText());
				}
				
//				TextMessage text = new TextMessage();
//				text.setFromUserName(toUserName);
//				text.setToUserName(fromUserName);
//				text.setMsgType("text");
//				text.setCreateTime(new Date().getTime());
//				text.setContent("Äú·¢ËÍµÄÏûÏ¢ÊÇ£º"+content);
//				message = MessageUtil.textMessageToXml(text);
			}else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){
				String eventType = map.get("Event");
				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}
			}
			out.print(message);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}

}
