package com.oc.websocket;

import org.apache.log4j.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * javaee 的WebSocket api
 */
@ServerEndpoint("/ws")
public class MessageHandler {

    private Logger logger = Logger.getLogger(this.getClass());

    @OnOpen
    public void onOpen(Session session) {
        logger.info("open");
    }

    @OnMessage
    public void onMessage(String msg, Session session) throws Exception {
        logger.info(msg);
        Thread.sleep(2000);
        session.getBasicRemote().sendText("success");
    }

    @OnClose
    public void onClose() {
        logger.info("close");
    }

}
