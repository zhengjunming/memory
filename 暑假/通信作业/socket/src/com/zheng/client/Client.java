package com.zheng.client;

import com.zheng.server.MyProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端
 * Created by 郑俊铭 on 2017/7/16.
 */
public class Client {
    private static final int PORT = 30000; // 端口号
    private Socket socket;
    private PrintStream ps;
    private BufferedReader brServer;
    private BufferedReader keyIn;

    public static void main(String[] args) throws IOException {
        UserLoginRegister loginRegister = new UserLoginRegister();
        loginRegister.init();
    }

    /**
     * 连接服务器初始化类
     * @param username 用户名
     */
    public void init(String username) {
        try {
            // 初始化键盘输入流
            keyIn = new BufferedReader(new InputStreamReader(System.in));
            // 连接到服务器
            socket = new Socket("127.0.0.1", PORT);
            // 获取Socket对应的输入输出流
            ps = new PrintStream(socket.getOutputStream());
            brServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // 将用户信息传给服务器
            ps.println(MyProtocol.USER_ROUND + username + MyProtocol.USER_ROUND);
            // 输出在线用户列表
            System.out.println(brServer.readLine());
            System.out.println("可以使用“//用户名：“的方式来发送私聊信息。");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            close();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("请确保服务器已开启");
            close();
            System.exit(1); // 异常退出
        }
        new ClientThread(brServer).start(); // 启动线程
    }

    /**
     * 用户发送和接收信息类
     */
    public void readAndSend() {
        try {
            String line = null;
            String content = null;
            while ((content = (line = keyIn.readLine())) != null) {
                if (!content.trim().isEmpty()) { // 输入内容不为空
                    if (line.indexOf("：") > 0 && line.startsWith("//")) { // 规定私聊格式
                        if (line.endsWith("：")) {
                            System.out.println("信息不能为空，请重新输入");
                        } else {
                            line = line.substring(2);
                            ps.println(MyProtocol.PRIVATE_ROUND + line.split("：")[0]
                                    + MyProtocol.SPLIT_SIGN +
                                    line.split("：")[1] + MyProtocol.PRIVATE_ROUND);
                        }
                    } else {
                        ps.println(MyProtocol.MSG_ROUND + line + MyProtocol.MSG_ROUND);
                    }
                } else {
                    System.out.println("信息不能为空，请重新输入");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            close();
            System.exit(1); // 异常退出
        }
    }

    /**
     * 关闭资源
     */
    private void close() {
        try {
            if (keyIn != null) {
                keyIn.close();
            }
            if (brServer != null) {
                brServer.close();
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