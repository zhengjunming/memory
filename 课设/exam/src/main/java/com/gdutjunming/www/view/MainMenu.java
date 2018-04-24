package com.gdutjunming.www.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author 郑俊铭
 * Date: 2017/11/19
 * Time: 19:09
 * No struggle, talent how to match the willfulness.
 * Description: 成绩管理系统主菜单
 */
public class MainMenu extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    public JPanel panel = new JPanel();
    public JPanel authorPanel = new JPanel();
    /**
     * 系统管理菜单项
     */
    private JMenuItem backMainMenu = new JMenuItem("返回主界面");
    private JMenuItem exitMenu = new JMenuItem("退出");
    /**
     * 数据操作菜单项
     */
    private JMenuItem insertStudentInfoMenuItem = new JMenuItem("插入学生信息");
    private JMenuItem studentInfoMenuItem = new JMenuItem("学生信息主页");
    /**
     * 数据查询菜单项
     */
    private JMenuItem highestAndMinScoreMenuItem = new JMenuItem("最高分/低分查询");
    private JMenuItem rateMenuItem = new JMenuItem("课程优秀率/不及格率查询");
    /**
     * 帮助菜单项
     */
    private JMenuItem jMenuItem = new JMenuItem("关于");

    MainMenu() {
        setTitle("学生成绩管理系统   16级软件工程（卓越工程师班） 郑俊铭  3116005120");
        setSize(900, 500);

        // 系统管理
        JMenu systemMenu = new JMenu("系统管理");
        systemMenu.add(backMainMenu);
        // 设置分割线
        systemMenu.addSeparator();
        systemMenu.add(exitMenu);
        // 设置快捷键
        exitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));

        // 数据操作菜单
        JMenu operationMenu = new JMenu("数据操作");
        operationMenu.add(insertStudentInfoMenuItem);
        operationMenu.addSeparator();
        operationMenu.add(studentInfoMenuItem);

        //数据查询菜单
        JMenu queryMenu = new JMenu("数据查询");
        queryMenu.addSeparator();
        queryMenu.add(highestAndMinScoreMenuItem);
        queryMenu.addSeparator();
        queryMenu.add(rateMenuItem);

        // 帮助菜单
        JMenu helpMenu = new JMenu("帮助");
        helpMenu.add(jMenuItem);
        jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));

        // 菜单栏
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(systemMenu);
        jMenuBar.add(operationMenu);
        jMenuBar.add(queryMenu);
        jMenuBar.add(helpMenu);
        // 设置菜单栏
        setJMenuBar(jMenuBar);

        // 主页信息
        getContentPane().add(panel, BorderLayout.NORTH);
        JLabel label = new JLabel("学生成绩管理系统");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 40));
        panel.add(label);
        getContentPane().add(authorPanel, BorderLayout.SOUTH);
        JLabel authorLabel = new JLabel("制作人：16级软件工程（卓越工程师班） 郑俊铭  3116005120");
        authorLabel.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        authorPanel.add(authorLabel);

        // 添加监听器
        systemMenu.addActionListener(this);
        operationMenu.addActionListener(this);
        queryMenu.addActionListener(this);
        helpMenu.addActionListener(this);
        insertStudentInfoMenuItem.addActionListener(this);
        studentInfoMenuItem.addActionListener(this);
        highestAndMinScoreMenuItem.addActionListener(this);
        rateMenuItem.addActionListener(this);
        backMainMenu.addActionListener(this);
        exitMenu.addActionListener(this);
        jMenuItem.addActionListener(this);
        // 居中显示
        setLocationRelativeTo(null);
        // 设为可见
        setVisible(true);

        // 关闭窗口时退出程序
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 返回主界面
        if (e.getSource() == backMainMenu) {
            dispose();
            new MainMenu();
        }
        // 退出系统
        else if (e.getSource() == exitMenu) {
            int result = JOptionPane.showConfirmDialog(null, "确认关闭吗?", "提示信息", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
        // 插入学生信息
        else if (e.getSource() == insertStudentInfoMenuItem) {
            dispose();
            new AddStudent();
        } else if (e.getSource() == studentInfoMenuItem) {
            dispose();
            new OperationMenu();
        }
        // 查询最高分
        else if (e.getSource() == highestAndMinScoreMenuItem) {
            dispose();
            new QueryMenu();
        }
        // 课程优秀率/不及格率查询
        else if (e.getSource() == rateMenuItem) {
            dispose();
            new RateMenu();
        }
        // 关于界面
        else if (e.getSource() == jMenuItem) {
            dispose();
            new HelpMenu();
        }
    }

}
