package com.gdutjunming.www.model;

import java.util.List;

/**
 * @author 郑俊铭
 * Date: 2017/11/19
 * Time: 23:17
 * No struggle, talent how to match the willfulness.
 * Description:
 */
public class Class {
    /**
     * 班级ID
     */
    private Integer id;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 班级学生
     */
    private List<Student> students;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Class{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", students=" + students +
                '}';
    }
}
