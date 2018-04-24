package com.gdutjunming.www.view;

import com.gdutjunming.www.model.Class;
import com.gdutjunming.www.dao.StudentDao;
import com.gdutjunming.www.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.awt.*;

/**
 * @author 郑俊铭
 * Date: 2017/11/19
 * Time: 23:28
 * No struggle, talent how to match the willfulness.
 * Description:
 */
class AddStudent extends MainMenu {

    /**
     * 学号输入文本框
     */
    private JTextField studentIdTextField = new JTextField(11);
    /**
     * 学生姓名输入文本框
     */
    private JTextField studentNameTextField = new JTextField(11);

    private String[] sexString = {"男", "女"};
    /**
     * 性别下拉框
     */
    private JComboBox<String> sexComboBox = new JComboBox<>(sexString);
    /**
     * 班级输入文本框
     */
    private JTextField classTextField = new JTextField(11);
    /**
     * 语文成绩输入文本框
     */
    private JTextField chineseScoreTextField = new JTextField(8);
    /**
     * 数学成绩输入文本框
     */
    private JTextField mathScoreTextField = new JTextField(8);
    /**
     * 英语成绩输入文本框
     */
    private JTextField englishScoreTextField = new JTextField(8);

    /**
     * 加载spring配置文件
     */
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    /**
     * 获得StudentDao的bean
     */
    private StudentDao studentDao = (StudentDao) applicationContext.getBean("student");

    AddStudent() {
        remove(super.authorPanel);
        remove(super.panel);
        getContentPane().setLayout(new GridLayout(10, 1));
        // 字符串面板
        JPanel stringPanel = new JPanel();
        JLabel stringLabel = new JLabel("添加学生页面");
        stringLabel.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        stringPanel.add(stringLabel);
        getContentPane().add(stringPanel);
        // 学号面板
        JPanel stuIdPanel = new JPanel();
        JLabel stuIdLabel = new JLabel("学号：");
        stuIdPanel.add(stuIdLabel);
        stuIdPanel.add(studentIdTextField);
        getContentPane().add(stuIdPanel);
        // 姓名面板
        JPanel studentNamePanel = new JPanel();
        JLabel studentNameLabel = new JLabel("姓名：");
        studentNamePanel.add(studentNameLabel);
        studentNamePanel.add(studentNameTextField);
        getContentPane().add(studentNamePanel);
        // 班级面板
        JPanel classPanel = new JPanel();
        JLabel classLabel = new JLabel("班级：");
        classPanel.add(classLabel);
        classPanel.add(classTextField);
        getContentPane().add(classPanel);
        // 性别面板
        JPanel sexPanel = new JPanel();
        JLabel sexLabel = new JLabel("性别：");
        sexPanel.add(sexLabel);
        sexPanel.add(sexComboBox);
        getContentPane().add(sexPanel);
        // 语文成绩面板
        JPanel chineseScorePanel = new JPanel();
        JLabel chineseScoreLabel = new JLabel("语文成绩：");
        chineseScorePanel.add(chineseScoreLabel);
        chineseScorePanel.add(chineseScoreTextField);
        getContentPane().add(chineseScorePanel);
        // 数学成绩面板
        JPanel mathScorePanel = new JPanel();
        JLabel mathScoreLabel = new JLabel("数学成绩：");
        mathScorePanel.add(mathScoreLabel);
        mathScorePanel.add(mathScoreTextField);
        getContentPane().add(mathScorePanel);
        // 英语成绩面板
        JPanel englishScorePanel = new JPanel();
        JLabel englishScoreLabel = new JLabel("英语成绩：");
        englishScorePanel.add(englishScoreLabel);
        englishScorePanel.add(englishScoreTextField);
        getContentPane().add(englishScorePanel);
        // 按钮面板
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("添加");
        buttonPanel.add(addButton);
        JButton cancelButton = new JButton("取消");
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel);

        // 添加按钮监听
        addButton.addActionListener(e -> {
            // 学号
            Integer studentId = null;
            // 姓名
            String studentName = studentNameTextField.getText();
            // 班级
            String className = classTextField.getText();
            // 性别
            String sex = (String) sexComboBox.getSelectedItem();
            Integer chineseScore = null;
            Integer mathScore = null;
            Integer englishScore = null;
            try {
                studentId = Integer.valueOf(studentIdTextField.getText());
                // 语文成绩
                chineseScore = Integer.valueOf(chineseScoreTextField.getText());
                // 数学成绩
                mathScore = Integer.valueOf(mathScoreTextField.getText());
                // 英语成绩
                englishScore = Integer.valueOf(englishScoreTextField.getText());
            } catch (NumberFormatException e1) {
                JOptionPane.showMessageDialog(null, "成绩必须为整数", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }
            // 进行信息校对
            if (null == studentId || "".equals(studentName) || "".equals(className)
                    || chineseScore == null || mathScore == null || englishScore == null) {
                JOptionPane.showMessageDialog(null, "信息不能为空", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            } else if (studentDao.findStudentByStudentId(studentId) != null) {
                JOptionPane.showMessageDialog(null, "该学号已存在", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            } else if (chineseScore < 0 || chineseScore > 100 || mathScore < 0 || mathScore > 100
                    || englishScore < 0 || englishScore > 100) {
                JOptionPane.showMessageDialog(null, "成绩必须为0-100的整数", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // 添加学生
                Class aClass = new Class();
                Class bClass;
                aClass.setClassName(className);
                if ((bClass = studentDao.selectClassByClassName(aClass.getClassName())) == null) {
                    studentDao.addClass(aClass);
                    bClass = aClass;
                }
                Student student = new Student();
                student.setaClass(bClass);
                student.setSex(sex);
                student.setId(studentId);
                student.setStudentName(studentName);
                student.setChineseScore(chineseScore);
                student.setMathScore(mathScore);
                student.setEnglishScore(englishScore);
                studentDao.addStudent(student);

                int result = JOptionPane.showConfirmDialog(null, "添加成功，继续添加?", "提示信息", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    dispose();
                    new AddStudent();
                } else {
                    dispose();
                    new MainMenu();
                }
            }
        });

        // 取消按钮监听
        cancelButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "确认取消添加吗?", "提示信息", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                dispose();
                new MainMenu();
            }
        });
    }
}
