package com.gdutjunming.www.view;

import com.gdutjunming.www.dao.StudentDao;
import com.gdutjunming.www.model.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;

/**
 * @author 郑俊铭
 * Date: 2017/11/21
 * Time: 16:57
 * No struggle, talent how to match the willfulness.
 * Description: 查询菜单页面
 */
class QueryMenu extends MainMenu {
    /**
     * 表头
     */
    private final String[] columnHeaders = {"学号", "姓名", "班级", "性别", "成绩"};

    private JTable studentTable;

    /**
     * 查询类别下拉框
     */
    private String[] strings = {"最高分查询", "最低分查询"};
    private JComboBox<String> jComboBox = new JComboBox<>(strings);

    /**
     * 科目下拉框
     */
    private String[] courseStrings = {"语文", "数学", "英语"};
    private JComboBox<String> courseComboBox = new JComboBox<>(courseStrings);

    private String[][] content = new String[150][5];

    /**
     * 加载spring配置文件
     */
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    /**
     * 获得StudentDao的bean
     */
    private StudentDao studentDao = (StudentDao) applicationContext.getBean("student");

    /**
     * 科目选择结果
     */
    private static String courseType = null;

    QueryMenu() {
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

        JPanel selectPanel = new JPanel();
        selectPanel.add(jComboBox);
        JLabel courseLabel = new JLabel("    查询科目");
        selectPanel.add(courseLabel);
        selectPanel.add(courseComboBox);
        JButton selectButton = new JButton("查询");
        selectPanel.add(selectButton);
        getContentPane().add(selectPanel, BorderLayout.NORTH);

        // 查询
        selectButton.addActionListener(e -> {
            String string = (String) jComboBox.getSelectedItem();
            courseType = (String) courseComboBox.getSelectedItem();
            if ("选择查询方式".equals(string)) {
                JOptionPane.showMessageDialog(null, "请选择查询方式", "提示信息", JOptionPane.INFORMATION_MESSAGE);
            } else if ("最高分查询".equals(string)) {
                List<Student> studentList = null;
                if ("语文".equals(courseType)) {
                    studentList = studentDao.selectChineseHighestScore();
                } else if ("数学".equals(courseType)) {
                    studentList = studentDao.selectMathHighestScore();
                } else if ("英语".equals(courseType)) {
                    studentList = studentDao.selectEnglishHighestScore();
                }
                content = handleData(studentList);
                studentTable.updateUI();
            } else if ("最低分查询".equals(string)) {
                List<Student> studentList = null;
                if ("语文".equals(courseType)) {
                    studentList = studentDao.selectChineseMinimumScore();
                } else if ("数学".equals(courseType)) {
                    studentList = studentDao.selectMathMinimumScore();
                } else if ("英语".equals(courseType)) {
                    studentList = studentDao.selectEnglishMinimumScore();
                }
                content = handleData(studentList);
                studentTable.updateUI();
            }
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

    private static String[][] handleData(List<Student> students) {
        String[][] content = new String[150][5];
        int i = 0;
        for (Student student : students) {
            content[i][0] = String.valueOf(student.getId());
            content[i][1] = student.getStudentName();
            content[i][2] = student.getaClass().getClassName();
            content[i][3] = student.getSex();
            if ("语文".equals(courseType)) {
                content[i][4] = String.valueOf(student.getChineseScore());
            } else if ("数学".equals(courseType)) {
                content[i][4] = String.valueOf(student.getMathScore());
            } else if ("英语".equals(courseType)) {
                content[i][4] = String.valueOf(student.getEnglishScore());
            }
            i++;
        }
        return content;
    }
}
