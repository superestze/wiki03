package com.zhengqi.wiki03.websocket;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.ranges.Range;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;

@Component
@ServerEndpoint("/ws/{token}")
public class WebSocketServer {
    private final static Logger LOG = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * 每个客户端都有一个token
     */
    private String token = "";

    private static HashMap<String, Session> map = new HashMap<String, Session>();

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        map.put(token, session);
        this.token = token;
        LOG.info("有新的连接： token:{}, session id： {}, 当前连接数：{}", token, session.getId(), map.size());
    }

    @OnClose
    public void onClose(Session session, @PathParam("token") String token) {
        map.remove(token);
        LOG.info("连接关闭，token:{}, session id:{}, 当前连接数：{}", this.token, session.getId(), map.size());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        LOG.info("收到消息：{}, 内容:{}", token, message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        LOG.info("发生错误", error);
    }

    public void sendInfo(String message) {
        for (String token: map.keySet()) {
            Session session = map.get(token);
            try {
                session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                LOG.error("消息推送失败：{}, 内容：{}", token, message);
            }
            LOG.info("推送消息:{}, 内容:{}", token , message);
        }
    }


}
