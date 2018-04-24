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
import java.util.List;
import java.util.Objects;

/**
 * @author 郑俊铭
 * Date: 2017/11/19
 * Time: 18:08
 * No struggle, talent how to match the willfulness.
 * Description: 数据操作页面
 */
class OperationMenu extends MainMenu {
    /**
     * 表头
     */
    private final String[] columnHeaders = {"学号", "姓名", "班级", "性别", "语文成绩",
            "数学成绩", "英语成绩", "总成绩", "平均成绩"};

    /**
     * 表格
     */
    private JTable studentTable;

    /**
     * 记录数
     */
    private int recode = 0;
    /**
     * 下拉框
     */
    private String[] strings = {"学号", "姓名", "班级"};
    private JComboBox<String> jComboBox = new JComboBox<>(strings);
    /**
     * 文本框
     */
    private JTextField selectTextField = new JTextField(12);
    /**
     * 表格行列数
     */
    private String[][] content = new String[150][9];

    /**
     * 加载spring配置文件
     */
    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
    /**
     * 获得StudentDao的bean
     */
    private StudentDao studentDao = (StudentDao) applicationContext.getBean("student");

    /**
     * 一页加载的数量
     */
    private static int pageSize = 150;
    /**
     * 总页数
     */
    private static int totalPageNum;
    /**
     * 当前页数
     */
    private static int page = 1;

    /**
     * 排序下拉框
     */
    private String[] sortString = {"按总成绩", "按单科成绩", "按平均成绩", "按学号"};
    private JComboBox<String> sortComboBox = new JComboBox<>(sortString);

    OperationMenu() {
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
        JLabel sortTypeLabel = new JLabel("排序类型");
        selectPanel.add(sortTypeLabel);
        selectPanel.add(sortComboBox);
        JButton sortButton = new JButton("排序");
        selectPanel.add(sortButton);
        JLabel selectTypeLabel = new JLabel("    搜索类型：");
        selectPanel.add(selectTypeLabel);
        selectPanel.add(jComboBox);
        selectPanel.add(selectTextField);
        JButton selectButton = new JButton("查询");
        selectPanel.add(selectButton);
        JButton showTotalButton = new JButton("显示全部数据");
        selectPanel.add(showTotalButton);
        getContentPane().add(selectPanel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        recode = studentDao.getStudentCount();
        totalPageNum = recode / pageSize;
        if (recode % pageSize > 0) {
            totalPageNum++;
        }

        JButton firstButton = new JButton("第一页");
        panel.add(firstButton);
        JButton previousButton = new JButton("上一页");
        panel.add(previousButton);
        JButton nextButton = new JButton("下一页");
        panel.add(nextButton);
        JButton lastButton = new JButton("最后一页");
        panel.add(lastButton);
        JButton deleteButton = new JButton("删除");
        panel.add(deleteButton);
        JButton updateButton = new JButton("修改");
        panel.add(updateButton);
        add(panel, BorderLayout.SOUTH);
        List<Student> students = studentDao.selectStudent(pageSize, (page - 1) * pageSize);
        content = handleData(students);

        // 第一页
        firstButton.addActionListener(e -> {
            if (page == 1) {
                JOptionPane.showMessageDialog(null, "当前已经是第一页", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            } else {
                page = 1;
                List<Student> studentList = studentDao.selectStudent(pageSize, (page - 1) * pageSize);
                content = handleData(studentList);
                studentTable.updateUI();
            }
        });

        // 上一页
        previousButton.addActionListener(e -> {
            if (page == 1) {
                JOptionPane.showMessageDialog(null, "当前已经是第一页", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            } else {
                page--;
                List<Student> studentList = studentDao.selectStudent(pageSize, (page - 1) * pageSize);
                content = handleData(studentList);
                studentTable.updateUI();
            }
        });

        // 下一页
        nextButton.addActionListener(e -> {
            if (page == totalPageNum) {
                JOptionPane.showMessageDialog(null, "当前已经最后一页", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            } else {
                page++;
                List<Student> studentList = studentDao.selectStudent(pageSize, (page - 1) * pageSize);
                content = handleData(studentList);
                studentTable.updateUI();
            }
        });

        // 最后一页
        lastButton.addActionListener(e -> {
            if (page == totalPageNum) {
                JOptionPane.showMessageDialog(null, "当前已经最后一页", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            } else {
                page = totalPageNum;
                List<Student> studentList = studentDao.selectStudent(pageSize, (page - 1) * pageSize);
                content = handleData(studentList);
                studentTable.updateUI();
            }
        });

        // 删除信息
        deleteButton.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "请选择一行进行删除", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int result = JOptionPane.showConfirmDialog(null, "确定删除一个学生吗?", "提示信息", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    Integer studentId = Integer.parseInt(String.valueOf(studentTable.getValueAt(selectedRow, 0)));
                    studentDao.deleteStudentByStudentId(studentId);
                    recode = studentDao.getStudentCount();
                    if (recode / pageSize == 0) {
                        page--;
                    }
                    totalPageNum = recode / pageSize;
                    if (recode % pageSize > 0) {
                        totalPageNum++;
                    }
                    List<Student> studentList = studentDao.selectStudent(pageSize, (page - 1) * pageSize);
                    content = handleData(studentList);
                    studentTable.updateUI();
                }
            }
        });

        // 修改学生信息
        updateButton.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "请选择一行进行修改", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Integer studentId = Integer.parseInt(String.valueOf(studentTable.getValueAt(selectedRow, 0)));
                Student student = studentDao.findStudentByStudentId(studentId);
                dispose();
                new UpdateStudentInfo(student);
            }

        });

        // 搜索
        selectButton.addActionListener(e -> {
            // 搜索类型
            String selectType = (String) jComboBox.getSelectedItem();
            // 搜索内容
            String selectContent = selectTextField.getText();
            if (!Objects.equals(selectContent, "")) {
                if ("学号".equals(selectType)) {
                    recode = studentDao.getStudentCountByStudentId(Integer.valueOf(selectContent));
                    if (recode == 0) {
                        JOptionPane.showMessageDialog(null, "没有找到该学生", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        totalPageNum = recode / pageSize;
                        if (recode % pageSize > 0) {
                            totalPageNum++;
                        }
                        page = 1;
                        List<Student> studentList = studentDao.selectStudentByStudentId(Integer.valueOf(selectContent), pageSize, (page - 1) * pageSize);
                        content = handleData(studentList);
                        studentTable.updateUI();
                    }
                } else if ("姓名".equals(selectType)) {
                    recode = studentDao.getStudentCountByStudentName(selectContent);
                    if (recode == 0) {
                        JOptionPane.showMessageDialog(null, "没有找到该学生", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        totalPageNum = recode / pageSize;
                        if (recode % pageSize > 0) {
                            totalPageNum++;
                        }
                        page = 1;
                        List<Student> studentList = studentDao.selectStudentByStudentName(selectContent, pageSize, (page - 1) * pageSize);
                        content = handleData(studentList);
                        studentTable.updateUI();
                    }
                } else if ("班级".equals(selectType)) {
                    Class aClass = studentDao.selectClassByClassName(selectContent);
                    if (aClass == null) {
                        JOptionPane.showMessageDialog(null, "没有找到该班级", "提示信息", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        recode = studentDao.getStudentCountByClassId(aClass.getId());
                        totalPageNum = recode / pageSize;
                        if (recode % pageSize > 0) {
                            totalPageNum++;
                        }
                        page = 1;
                        List<Student> studentList = studentDao.selectStudentByClassId(aClass.getId(), pageSize, (page - 1) * pageSize);
                        for (Student student : studentList) {
                            student.setaClass(aClass);
                        }
                        content = handleData(studentList);
                        studentTable.updateUI();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "请输入搜索内容", "ERROR", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // 显示全部内容
        showTotalButton.addActionListener(e -> {
            recode = studentDao.getStudentCount();
            totalPageNum = recode / pageSize;
            if (recode % pageSize > 0) {
                totalPageNum++;
            }
            selectTextField.setText(null);
            page = 1;
            List<Student> studentList = studentDao.selectStudent(pageSize, (page - 1) * pageSize);
            content = handleData(studentList);
            studentTable.updateUI();
        });

        final String[] course = new String[1];
        sortComboBox.addActionListener(e -> {
            String select = (String) sortComboBox.getSelectedItem();
            if ("按单科成绩".equals(select)) {
                Object[] possibleValues = {"语文", "数学", "英语"};
                course[0] = (String) JOptionPane.showInputDialog(null, null, "科目选择", JOptionPane.INFORMATION_MESSAGE, null, possibleValues, possibleValues[0]);
            }
        });
        // 按要求排序
        sortButton.addActionListener(e -> {
            String sortType = (String) sortComboBox.getSelectedItem();
            recode = studentDao.getStudentCount();
            totalPageNum = recode / pageSize;
            if (recode % pageSize > 0) {
                totalPageNum++;
            }
            selectTextField.setText(null);
            page = 1;
            if ("按单科成绩".equals(sortType)) {
                if ("语文".equals(course[0])) {
                    List<Student> studentList = studentDao.sortByChineseScore(pageSize, (page - 1) * pageSize);
                    content = handleData(studentList);
                    studentTable.updateUI();
                } else if ("数学".equals(course[0])) {
                    List<Student> studentList = studentDao.sortByMathScore(pageSize, (page - 1) * pageSize);
                    content = handleData(studentList);
                    studentTable.updateUI();
                } else if ("英语".equals(course[0])) {
                    List<Student> studentList = studentDao.sortByEnglishScore(pageSize, (page - 1) * pageSize);
                    content = handleData(studentList);
                    studentTable.updateUI();
                }
            } else if ("按总成绩".equals(sortType)) {
                List<Student> studentList = studentDao.sortByTotalScore(pageSize, (page - 1) * pageSize);
                content = handleData(studentList);
                studentTable.updateUI();
            } else if ("按平均成绩".equals(sortType)) {
                List<Student> studentList = studentDao.sortByAvgScore(pageSize, (page - 1) * pageSize);
                content = handleData(studentList);
                studentTable.updateUI();
            } else if ("按学号".equals(sortType)) {
                List<Student> studentList = studentDao.selectStudent(pageSize, (page - 1) * pageSize);
                content = handleData(studentList);
                studentTable.updateUI();
            }
        });

    }

    /**
     * 建立表格模型
     */
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

    /**
     * 生成表格数据
     *
     * @param students 学生集合
     * @return 表格二维数组
     */
    static String[][] handleData(List<Student> students) {
        String[][] content = new String[150][9];
        int i = 0;
        for (Student student : students) {
            content[i][0] = String.valueOf(student.getId());
            content[i][1] = student.getStudentName();
            content[i][2] = student.getaClass().getClassName();
            content[i][3] = student.getSex();
            content[i][4] = String.valueOf(student.getChineseScore());
            content[i][5] = String.valueOf(student.getMathScore());
            content[i][6] = String.valueOf(student.getEnglishScore());
            content[i][7] = String.valueOf(student.getChineseScore() + student.getMathScore() + student.getEnglishScore());
            content[i][8] = String.valueOf((student.getChineseScore() + student.getMathScore() + student.getEnglishScore()) / 3);
            i++;
        }
        return content;
    }
}
