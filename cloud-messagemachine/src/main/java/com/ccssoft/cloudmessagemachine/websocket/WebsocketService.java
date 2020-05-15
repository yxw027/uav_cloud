package com.ccssoft.cloudmessagemachine.websocket;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author moriarty
 * @date 2020/5/15 16:26
 */
@ServerEndpoint(value="/websocket/{username}")
@Service
public class WebsocketService {

    private static int onlineCount = 0;
    private static Map<String, WebsocketService> clients = new ConcurrentHashMap<String, WebsocketService>();
    private Session session;
    private String username;

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {
        this.username = username;
        this.session = session;
        addOnlineCount();
        clients.put(username, this);
        System.out.println("已连接");

    }

    @OnClose
    public void onClose() throws IOException {

        clients.remove(username);

        subOnlineCount();

    }

    @OnMessage
    public void onMessage(String message) throws IOException {
        System.out.println(message);
        sendMessageAll(message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }


    public void sendMessageTo(String message, String To) throws IOException {
        // session.getBasicRemote().sendText(message);
        //session.getAsyncRemote().sendText(message);
        for (WebsocketService item : clients.values()) {
            if (item.username.equals(To) ) {
                item.session.getAsyncRemote().sendText(message);
            }
        }
    }

    public void sendMessageAll(String message) throws IOException {
        for (WebsocketService item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebsocketService.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebsocketService.onlineCount--;
    }

    public static synchronized Map<String, WebsocketService> getClients() {
        return clients;
    }
}
