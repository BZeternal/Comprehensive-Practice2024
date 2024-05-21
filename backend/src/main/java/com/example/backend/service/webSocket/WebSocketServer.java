package com.example.backend.service.webSocket;

import com.alibaba.fastjson.JSON;
import com.example.backend.mapper.UserMapper;
import com.example.backend.utils.MessageUtils;
import com.example.backend.pojo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


@Component
@ServerEndpoint("/websocket/{userId}")
public class WebSocketServer {
    private static ApplicationContext applicationContext;

    private UserMapper userMapper;
    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    //存客服人员信息
    private static ConcurrentHashMap<Integer, Session> serverMap = new ConcurrentHashMap<>();
    //Map用来存储已连接的客户信息
    private static ConcurrentHashMap<Integer, Session> userMap = new ConcurrentHashMap<>();
    //当前连接客户端的Session信息
    private Session session;
    //当前客户端名称
    private Integer userId;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Integer userId) throws IOException {
        this.session = session;
        this.userId = userId;
        userMapper = applicationContext.getBean(UserMapper.class);
        int auth = userMapper.selectById(userId).getAuth();
        if(auth == 2){
            userMap.put(userId, session);
            //推送
            String message1 = MessageUtils.getMessage(true,null,getFriends(serverMap));
            session.getBasicRemote().sendText(message1);
            String message = MessageUtils.getMessage(true,null,getFriends(userMap));
            broadcastServer(message);
        }
        else{
            serverMap.put(userId, session);
            String message1 = MessageUtils.getMessage(true,null,getFriends(userMap));
            session.getBasicRemote().sendText(message1);
            String message = MessageUtils.getMessage(true,null,getFriends(serverMap));
            broadcastUser(message);
        }
        logger.info("uId: "+userId+"加入,"+"当前人数: 客服："+serverMap.size()+" 用户："+userMap.size());
    }
    private List<Map<String,Object>> getFriends(ConcurrentHashMap<Integer, Session> map){
        userMapper = applicationContext.getBean(UserMapper.class);
        Set<Integer> set = map.keySet();
        List<Map<String,Object>> list = new ArrayList<>();
        for (int id:set) {
            Map<String,Object> map1 = new HashMap<>();
            map1.put("uId", id);
            map1.put("img", userMapper.selectById(id).getImage());
            list.add(map1);
        }
        return list;
    }
    private void broadcastUser(String message){
        for (Map.Entry<Integer, Session> entry : userMap.entrySet()) {
            Session session = entry.getValue();
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                //记录日志
            }
        }
    }
    private void broadcastServer(String message){
        for (Map.Entry<Integer, Session> entry : serverMap.entrySet()) {
            Session session = entry.getValue();
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                //记录日志
            }
        }
    }
    /*
    * 断开websocket连接时被调用*/
    @OnClose
    public void onClose(Session session){
        userMapper = applicationContext.getBean(UserMapper.class);
        int auth = userMapper.selectById(userId).getAuth();
        if(auth == 2){
            userMap.remove(userId);
            String message = MessageUtils.getMessage(true,null,getFriends(userMap));
            broadcastServer(message);
        }
        else {
            serverMap.remove(userId);
            String message = MessageUtils.getMessage(true,null,getFriends(serverMap));
            broadcastUser(message);
        }
        logger.info("uId: "+userId+"离开,"+"当前人数: 客服："+serverMap.size()+" 用户："+userMap.size());
    }
    /*
    * 浏览器发送到服务端，该方法被调用*/
    @OnMessage
    public void onMessage(String message) {
        userMapper = applicationContext.getBean(UserMapper.class);
        Message mes = JSON.parseObject(message, Message.class);
        //获取收消息的用户、
        Integer toName = mes.getToName();
        String mess = mes.getMessage();
        //获取接收方对象的session
        int auth = userMapper.selectById(toName).getAuth();
        Session session;
        if (auth == 2) {
            session = userMap.get(toName);
        } else {
            session = serverMap.get(toName);
        }
        String message1 = MessageUtils.getMessage(false, userId, mess);
        try {
            session.getBasicRemote().sendText(message1);
        } catch (IOException e) {
            //记录日志
        }
    }
}
