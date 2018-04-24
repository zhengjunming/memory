package com.gdutjunming.www.view;

import com.gdutjunming.www.dao.StudentDao;
import com.gdutjunming.www.model.Class;
import com.gdutjunming.www.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 郑俊铭
 * Date: 2017/11/21
 * Time: 18:51
 * No struggle, talent how to match the willfulness.
 * Description: 显示优秀率以及不及格率页面
 */
class RateMenu extends MainMenu {
    /**
     * 表头
     */
    private final String[] columnHeaders = {"学号", "姓名", "班级", "性别", "语文成绩",
            "数学成绩", "英语成绩", "总成绩", "平均成绩"};

    private JTable studentTable;
    private String[][] content = new String[150][9];
    /**
     * 加载spring配置文件
     */
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    /**
     * 获得StudentDao的bean
     */
    private StudentDao studentDao = (StudentDao) applicationContext.getBean("student");

    private String[] courseStrings = {"语文", "数学", "英语"};
    /**
     * 优秀率显示文本框
     */
    private JTextField excellentRateText = new JTextField(6);
    /**
     * 不及格率显示文本框
     */
    private JTextField failureRateText = new JTextField(6);
    /**
     * 班级下拉框
     */
    private JComboBox<String> classComboBox = new JComboBox<>();
    /**
     * 课程下拉框
     */
    private JComboBox<String> courseComboBox = new JComboBox<>(courseStrings);

    RateMenu() {
        remove(super.authorPanel);
        remove(super.panel);
        StuTable stuTable = new StuTable();
        studentTable = new JTable(stuTable);
        JScrollPane jScrollPane = new JScrollPane(studentTable);
        TitledBorder titledBorder = BorderFactory.createTitledBorder("学生信息");
        titledBorder.setTitleColor(Color.BLACK);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        jScrollPane.setBorder(titledBorder);
        studentTable.setSelectionForeground(Color.RED);
        studentTable.setSelectionBackground(Color.YELLOW);
        studentTable.setRowHeight(20);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        studentTable.setDefaultRenderer(Object.class, renderer);
        add(jScrollPane, BorderLayout.CENTER);

        List<Class> classes = studentDao.selectAllClass();
        List<String> list = new ArrayList<>();
        for (Class aClass : classes) {
            list.add(aClass.getClassName());
        }
        int size = list.size();
        String[] classStrings = list.toArray(new String[size]);
        classComboBox = new JComboBox<>(classStrings);
        JLabel classLabel = new JLabel("    班级：");
        JPanel selectPanel = new JPanel();
        selectPanel.add(classLabel);
        selectPanel.add(classComboBox);
        JLabel courseLabel = new JLabel("   课程：");
        selectPanel.add(courseLabel);
        selectPanel.add(courseComboBox);
        JButton selectButton = new JButton("查询");
        selectPanel.add(selectButton);
        JLabel excellentRateLabel = new JLabel("               优秀率： ");
        selectPanel.add(excellentRateLabel);
        selectPanel.add(excellentRateText);
        JLabel failureRateLabel = new JLabel("不及格率： ");
        selectPanel.add(failureRateLabel);
        selectPanel.add(failureRateText);

        excellentRateText.setEditable(false);
        failureRateText.setEditable(false);
        getContentPane().add(selectPanel, BorderLayout.NORTH);

        selectButton.addActionListener(e -> {
            int excellentCount = 0;
            int failureCount = 0;
            String className = (String) classComboBox.getSelectedItem();
            String courseName = (String) courseComboBox.getSelectedItem();
            Class aClass = studentDao.selectClassByClassName(className);
            List<Student> studentList = null;
            if ("语文".equals(courseName)) {
                studentList = studentDao.selectStudentByClassIdAndChinese(aClass.getId());
                for (Student student : studentList) {
                    if (student.getChineseScore() >= 90) {
                        excellentCount++;
                    } else if (student.getChineseScore() < 60) {
                        failureCount++;
                    }
                }
            } else if ("数学".equals(courseName)) {
                studentList = studentDao.selectStudentByClassIdAndMath(aClass.getId());
                for (Student student : studentList) {
                    if (student.getMathScore() >= 90) {
                        excellentCount++;
                    } else if (student.getMathScore() < 60) {
                        failureCount++;
                    }
                }
            } else if ("英语".equals(courseName)) {
                studentList = studentDao.selectStudentByClassIdAndEnglish(aClass.getId());
                for (Student student : studentList) {
                    if (student.getEnglishScore() >= 90) {
                        excellentCount++;
                    } else if (student.getEnglishScore() < 60) {
                        failureCount++;
                    }
                }
            }
            assert studentList != null;
            for (Student student : studentList) {
                student.setaClass(aClass);
            }
            DecimalFormat format   =new DecimalFormat("########.00");
            int studentCount = studentList.size();
            double excellentRate = (double) excellentCount / (double) studentCount;
            double failureRate = (double) failureCount / (double) studentCount;
            excellentRate = Double.parseDouble(format.format(excellentRate));
            failureRate = Double.parseDouble(format.format(failureRate));
            System.out.println(excellentRate);
            System.out.println(failureRate);
            excellentRateText.setText(String.valueOf(excellentRate * 100) + "%");
            failureRateText.setText(String.valueOf(failureRate * 100) + "%");
            content = OperationMenu.handleData(studentList);
            studentTable.updateUI();
        });
    }

    private class StuTable extends AbstractTableModel {
        @Override
        public String getColumnName(int col) {
            return columnHeaders[col];
        }

        @Override
        public int getColumnCount() {
            return content[0].length;
        }

        @Override
        public int getRowCount() {
            return content.length;
        }

        @Override
        public Object getValueAt(int row, int column) {
            return content[row][column];
        }
    }
}
