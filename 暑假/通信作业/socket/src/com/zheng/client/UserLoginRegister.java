package com.zheng.client;

import com.zheng.dao.impl.UserDaoImpl;
import com.zheng.model.User;

import java.io.IOException;
import java.util.Scanner;

/**
 * 用户登录注册类
 * Created by 郑俊铭 on 2017/7/16.
 */
public class UserLoginRegister {

    /**
     * 用户登陆注册初始化方法
     */
    public void init() throws IOException {
        System.out.println("欢迎来到小铭聊天室，选择1可进行登录，没有账号请选择2进行注册。");
        Scanner scanner = new Scanner(System.in);
        // 判断用户的输入
        switch (scanner.next()) {
            case "1": {
                login();
                break;
            }
            case "2": {
                register();
                break;
            }
            default: {
                System.out.println("输入错误，请重新输入");
                init();
                break;
            }
        }
    }

    /**
     * 用户登录方法
     *
     * @return 成功返回true，失败返回false
     */
    public boolean login() throws IOException {
        User user = new User();
        User user1 = new User();
        UserDaoImpl userDao = new UserDaoImpl();
        System.out.println("==================登录界面================");
        System.out.print("请输入你的用户名：");
        Scanner scanner = new Scanner(System.in);
        String content = null;
        while (true) {
            content = scanner.nextLine();
            if (content.trim().isEmpty()) {
                System.out.println("输入内容不能为空，请重新输入！");
                System.out.print("请输入你的用户名：");
            } else {
                break;
            }
        }
        user.setUsername(content.trim());
        System.out.print("请输入你的密码：");
        while (true) {
            content = scanner.nextLine();
            if (content.trim().isEmpty()) {
                System.out.println("输入内容不能为空，请重新输入！");
                System.out.print("请输入你的密码：");
            } else {
                break;
            }
        }
        user.setPassword(content);
        if ((user1 = userDao.login(user)) != null) {
            System.out.println("登录成功");
            Client client = new Client();
            client.init(user1.getUsername());
            client.readAndSend();
            return true;
        } else {
            System.out.println("登录失败，请检查账号密码的准确性");
            init();
        }
        return false;
    }

    /**
     * 用户注册方法
     *
     * @return 成功返回true，失败返回false
     */
    public boolean register() throws IOException {
        System.out.println("=================注册页面=================");
        User user = new User();
        UserDaoImpl userDao = new UserDaoImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String content = null;
        while (true) {
            content = scanner.nextLine();
            if (userDao.usernameIsExist(content)) {
                System.out.print("该用户名已经存在，请输入其他用户名：");
            } else if (content.trim().isEmpty()) {
                System.out.println("输入内容不能为空，请重新输入！");
                System.out.print("请输入用户名：");
            } else {
                break;
            }
        }
        user.setUsername(content.trim());
        System.out.print("请输入你的密码：");
        while (true) {
            content = scanner.nextLine();
            if (content.trim().isEmpty()) {
                System.out.println("输入的内容不能为空，请重新输入！");
                System.out.print("请输入你的密码：");
            } else {
                break;
            }
        }
        user.setPassword(content);
        if (userDao.addUser(user)) {
            System.out.println("注册成功");
            init();
        } else {
            System.out.println("注册失败");
            init();
        }
        return false;
    }
}
