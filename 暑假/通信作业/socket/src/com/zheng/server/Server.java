package com.zheng.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端
 * Created by 郑俊铭 on 2017/7/16.
 */
public class Server {
    private static final int PORT = 30000; // 端口号
    public static MyMap<String, PrintStream> clients = new MyMap<>();

    /**
     *
     */
    public void init() {
        try (
                ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // 接收客户端请求
                Socket socket = serverSocket.accept();
                new ServerThread(socket).start(); // 启动线程
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.init();
    }
}
