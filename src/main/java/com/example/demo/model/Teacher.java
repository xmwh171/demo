package com.example.demo.model;

import java.util.List;

/**
 * @Description
 * @Author xiaohu
 * @Date 2019/12/11 11:14
 */
public class Teacher {

    private String teacherName;

    private Integer teacherAge;

    private Course course;

    List<Student> students;

    public Teacher(String teacherName, Integer teacherAge, Course course, List<Student> students) {
        this.teacherName = teacherName;
        this.teacherAge = teacherAge;
        this.course = course;
        this.students = students;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getTeacherAge() {
        return teacherAge;
    }

    public void setTeacherAge(Integer teacherAge) {
        this.teacherAge = teacherAge;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherName='" + teacherName + '\'' +
                ", teacherAge=" + teacherAge +
                ", course=" + course +
                ", students=" + students +
                '}';
    }
}
