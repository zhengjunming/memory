package com.gdutjunming.www.model;

/**
 * @author 郑俊铭
 * Date: 2017/11/19
 * Time: 23:09
 * No struggle, talent how to match the willfulness.
 * Description: 学生类
 */
public class Student {
    /**
     * 学生ID，学号
     */
    private Integer id;
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 语文成绩
     */
    private Integer chineseScore;
    /**
     * 数学成绩
     */
    private Integer mathScore;
    /**
     * 英语成绩
     */
    private Integer englishScore;
    /**
     * 所在班级
     */
    private Class aClass;

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getChineseScore() {
        return chineseScore;
    }

    public void setChineseScore(Integer chineseScore) {
        this.chineseScore = chineseScore;
    }

    public Integer getMathScore() {
        return mathScore;
    }

    public void setMathScore(Integer mathScore) {
        this.mathScore = mathScore;
    }

    public Integer getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(Integer englishScore) {
        this.englishScore = englishScore;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", sex='" + sex + '\'' +
                ", chineseScore=" + chineseScore +
                ", mathScore=" + mathScore +
                ", englishScore=" + englishScore +
                ", aClass=" + aClass +
                '}';
    }
}
