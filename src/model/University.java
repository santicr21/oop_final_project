package model;

import java.util.ArrayList;

public class University {
    private ArrayList <Professor> teachers;
    private ArrayList <Student> students;
    private ArrayList <Class> classes;

    public ArrayList<Professor> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Professor> teachers) {
        this.teachers = teachers;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }

    public void setClasses(ArrayList<Class> classes) {
        this.classes = classes;
    }
}
