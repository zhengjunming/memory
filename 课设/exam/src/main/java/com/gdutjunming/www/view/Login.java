package com.gdutjunming.www.view;

import com.gdutjunming.www.dao.LoginDao;
import com.gdutjunming.www.model.Admin;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 郑俊铭
 * Date: 2017/11/19
 * Time: 16:00
 * No struggle, talent how to match the willfulness.
 * Description: 管理员登录页面
 */
public class Login extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    /**
     * 登录按钮
     */
    private JButton loginButton = new JButton("登录");
    /**
     * 退出按钮
     */
    private JButton exitButton = new JButton("退出");
    /**
     * 用户名输入文本框
     */
    private JTextField usernameField = new JTextField(20);
    /**
     * 密码输入文本框
     */
    private JPasswordField passwordField = new JPasswordField(20);
    /**
     * 加载spring配置文件
     */
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    /**
     * 获得LoginDao的bean
     */
    private LoginDao loginDao = (LoginDao) applicationContext.getBean("loginDao");

    Login(){
        setTitle("学生成绩管理系统");
        // 创建一个四行一列的网格
        getContentPane().setLayout(new GridLayout(4,1));
        // 字符串面板
        JPanel stringPanel = new JPanel();
        getContentPane().add(stringPanel);
        JLabel stringLabel = new JLabel("管理员登录界面");
        stringPanel.add(stringLabel);
        // 用户名面板
        JPanel usernamePanel = new JPanel();
        getContentPane().add(usernamePanel);
        JLabel nameLabel = new JLabel("用户名：");
        usernamePanel.add(nameLabel);
        usernamePanel.add(usernameField);
        // 密码面板
        JPanel passwordPanel = new JPanel();
        getContentPane().add(passwordPanel);
        JLabel passwordLabel = new JLabel("密     码：");
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        // 按钮面板
        JPanel buttonPanel = new JPanel();
        getContentPane().add(buttonPanel);
        buttonPanel.add(loginButton);
        buttonPanel.add(exitButton);

        setBounds(500, 250, 100, 100);
        pack();
        setVisible(true);
        // 设置密码显示的样式
        passwordField.setEchoChar('*');
        // 为两个按钮注册监听器
        loginButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 如果事件源是loginButton
        if (e.getSource() == loginButton) {
            // 获得文本框的密码和用户名
            String username = usernameField.getText();
            String password = passwordField.getText();

            if ("".equals(username) || "".equals(password)) {
                JOptionPane.showMessageDialog(null, "用户名或密码不能为空", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Admin admin = new Admin(username, password);
                Admin dtoAdmin = loginDao.findAdminByUsername(admin.getUsername());
                // 没有找到该用户
                if (dtoAdmin == null) {
                    JOptionPane.showMessageDialog(null, "该用户名不存在", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // 密码正确
                    if (admin.equals(dtoAdmin)) {
                        dispose();
                        // 进入主页面
                        MainMenu mainMenu = new MainMenu();
                        mainMenu.setVisible(true);
                    }
                    // 密码错误
                    else {
                        JOptionPane.showMessageDialog(null, "密码错误", "ERROR", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        // 如果事件源是exitButton
        else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}
