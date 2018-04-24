package com.zheng.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by 郑俊铭 on 2017/7/16.
 */
public class ServerThread extends Thread {
    private Socket socket;
    BufferedReader br = null;
    PrintStream ps = null;

    // 构造器创建线程
    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            // 获取Socket的输入流和输出流
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ps = new PrintStream(socket.getOutputStream());
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(MyProtocol.USER_ROUND) && line.endsWith(MyProtocol.USER_ROUND)) {
                    String userName = getRealMsg(line);
                    Server.clients.put(userName, ps);
                    for (PrintStream client : Server.clients.valueSet()) {
                        client.println(userName + "上线了，" + "现在在线的用户有：" + Server.clients.map.keySet());
                    }
                } else if (line.startsWith(MyProtocol.PRIVATE_ROUND) && line.endsWith(MyProtocol.PRIVATE_ROUND)) {
                    String userAndMsg = getRealMsg(line);
                    String user = userAndMsg.split(MyProtocol.SPLIT_SIGN)[0];
                    String msg = userAndMsg.split(MyProtocol.SPLIT_SIGN)[1];
                    if (Server.clients.map.containsKey(user)) { // 没有找到此用户
                        Server.clients.map.get(user).println(Server.clients.getKeyByValue(ps) + "私聊你：" + msg);
                    } else {
                        Server.clients.map.get(Server.clients.getKeyByValue(ps)).println("没有此用户，请重新输入！");
                    }
                } else {
                    String msg = getRealMsg(line);
                    for (PrintStream client : Server.clients.valueSet()) {
                        client.println(Server.clients.getKeyByValue(ps) + "说：" + msg);
                    }
                }
            }
        } catch (IOException e) {
            for (PrintStream client : Server.clients.valueSet()) {
                client.println(Server.clients.getKeyByValue(ps) + "已下线。");
            }
            Server.clients.removeByValue(ps);
            for (PrintStream client : Server.clients.valueSet()) {
                client.println("现在在线的用户有" + Server.clients.map.size() +"个，分别为：" + Server.clients.map.keySet());
            }
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 截取客户发送的信息
     * @param line 信息
     * @return 真正的信息
     */
    private String getRealMsg(String line) {
        return line.substring(MyProtocol.PROTOCOL, line.length() - MyProtocol.PROTOCOL);
    }
}
