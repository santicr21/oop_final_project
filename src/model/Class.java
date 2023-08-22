package model;

import java.util.ArrayList;

public class Class {
    private int id;
    private String name;
    private ArrayList<Student> students;
    private Professor teacher;
    private String assignedClassroom;

    public Class(int id, String name, ArrayList<Student> students, Professor teacher, String assignedClassroom) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.teacher = teacher;
        this.assignedClassroom = assignedClassroom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Professor getTeacher() {
        return teacher;
    }

    public void setTeacher(Professor teacher) {
        this.teacher = teacher;
    }

    public String getAssignedClassroom() {
        return assignedClassroom;
    }

    public void setAssignedClassroom(String assignedClassroom) {
        this.assignedClassroom = assignedClassroom;
    }
}
