package com.gdutjunming.www.dao;

import com.gdutjunming.www.model.Class;
import com.gdutjunming.www.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 郑俊铭
 * Date: 2017/11/19
 * Time: 23:30
 * No struggle, talent how to match the willfulness.
 * Description: 学生成绩管理DAO层
 */
public interface StudentDao {

    /**
     * 根据学生ID查找学生
     *
     * @param id 学生ID
     * @return Student, 学生实体类
     */
    Student findStudentByStudentId(@Param("id") Integer id);

    /**
     * 添加一个学生
     *
     * @param student 学生实体类
     */
    void addStudent(Student student);

    /**
     * 添加一个班级
     *
     * @param aClass 班级实体类
     */
    void addClass(Class aClass);

    /**
     * 获得学生总数
     *
     * @return 学生总数
     */
    int getStudentCount();

    /**
     * 根据学号获取学生数量
     *
     * @param studentId 学号
     * @return 学生数量
     */
    int getStudentCountByStudentId(@Param("studentId") Integer studentId);

    /**
     * 根据学生名字获取学生数量
     *
     * @param studentName 学生名字
     * @return 学生数量
     */
    int getStudentCountByStudentName(@Param("studentName") String studentName);

    /**
     * 根据班级ID获取学生数量
     *
     * @param classId 班级ID
     * @return 学生数量
     */
    int getStudentCountByClassId(@Param("classId") Integer classId);

    /**
     * 分页查询学生
     *
     * @param pageSize 一页显示的数量
     * @param page     页数
     * @return List<Student>, 学生集合
     */
    List<Student> selectStudent(@Param("pageSize") Integer pageSize, @Param("page") Integer page);

    /**
     * 根据学生ID删除学生
     *
     * @param studentId 学生ID
     */
    void deleteStudentByStudentId(@Param("studentId") Integer studentId);

    /**
     * 更新学生信息
     *
     * @param student 学生实体类
     */
    void updateStudentInfo(Student student);

    /**
     * 根据学生ID分页查询学生
     *
     * @param studentId 学生ID
     * @param pageSize  一页显示的数量
     * @param page      页数
     * @return List<Student>, 学生集合
     */
    List<Student> selectStudentByStudentId(@Param("studentId") Integer studentId, @Param("pageSize") Integer pageSize, @Param("page") Integer page);

    /**
     * 根据学生名字分页查询学生
     *
     * @param studentName 学生名字
     * @param pageSize    一页显示的数量
     * @param page        页数
     * @return List<Student>, 学生集合
     */
    List<Student> selectStudentByStudentName(@Param("studentName") String studentName, @Param("pageSize") Integer pageSize, @Param("page") Integer page);

    /**
     * 根据班级ID分页查询学生
     *
     * @param classId  班级ID
     * @param pageSize 一页显示的数量
     * @param page     页数
     * @return List<Student>, 学生集合
     */
    List<Student> selectStudentByClassId(@Param("classId") Integer classId, @Param("pageSize") Integer pageSize, @Param("page") Integer page);

    /**
     * 根据班级名称查找班级
     *
     * @param className 班级名称
     * @return Class, 班级实体类
     */
    Class selectClassByClassName(@Param("className") String className);

    /**
     * 根据语文成绩进行排序
     *
     * @param pageSize 一页显示的数量
     * @param page     页数
     * @return 排序好的学生集合
     */
    List<Student> sortByChineseScore(@Param("pageSize") Integer pageSize, @Param("page") Integer page);

    /**
     * 根据数学成绩进行排序
     *
     * @param pageSize 一页显示的数量
     * @param page     页数
     * @return 排序好的学生集合
     */
    List<Student> sortByMathScore(@Param("pageSize") Integer pageSize, @Param("page") Integer page);

    /**
     * 根据英语成绩进行排序
     *
     * @param pageSize 一页显示的数量
     * @param page     页数
     * @return 排序好的学生集合
     */
    List<Student> sortByEnglishScore(@Param("pageSize") Integer pageSize, @Param("page") Integer page);

    /**
     * 根据总成绩进行排序
     *
     * @param pageSize 一页显示的数量
     * @param page     页数
     * @return 排序好的学生集合
     */
    List<Student> sortByTotalScore(@Param("pageSize") Integer pageSize, @Param("page") Integer page);

    /**
     * 根据平均成绩进行排序
     *
     * @param pageSize 一页显示的数量
     * @param page     页数
     * @return 排序好的学生集合
     */
    List<Student> sortByAvgScore(@Param("pageSize") Integer pageSize, @Param("page") Integer page);

    /**
     * 查找语文成绩最高分的学生
     *
     * @return 按学号排序的最高分学生集合
     */
    List<Student> selectChineseHighestScore();

    /**
     * 查找数学成绩最高分的学生
     *
     * @return 按学号排序的最高分学生集合
     */
    List<Student> selectMathHighestScore();

    /**
     * 查找英语成绩最高分的学生
     *
     * @return 按学号排序的最高分学生集合
     */
    List<Student> selectEnglishHighestScore();

    /**
     * 查找语文成绩最低分的学生
     *
     * @return 按学号排序的最高分学生集合
     */
    List<Student> selectChineseMinimumScore();

    /**
     * 查找数学成绩最低分的学生
     *
     * @return 按学号排序的最高分学生集合
     */
    List<Student> selectMathMinimumScore();

    /**
     * 查找英语成绩最低分的学生
     *
     * @return 按学号排序的最高分学生集合
     */
    List<Student> selectEnglishMinimumScore();

    /**
     * 查找所有的班级
     *
     * @return List<Class>, 班级集合
     */
    List<Class> selectAllClass();

    /**
     * 根据班级ID和语文成绩排序查找学生
     *
     * @param classId 班级ID
     * @return List<Student>, 已经排好语文成绩的学生集合
     */
    List<Student> selectStudentByClassIdAndChinese(@Param("classId") Integer classId);

    /**
     * 根据班级ID和数学成绩排序查找学生
     *
     * @param classId 班级ID
     * @return List<Student>, 已经排好数学成绩的学生集合
     */
    List<Student> selectStudentByClassIdAndMath(@Param("classId") Integer classId);

    /**
     * 根据班级ID和英语成绩排序查找学生
     *
     * @param classId 班级ID
     * @return List<Student>, 已经排好英语成绩的学生集合
     */
    List<Student> selectStudentByClassIdAndEnglish(@Param("classId") Integer classId);
}
