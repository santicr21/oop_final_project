package model;

import java.util.ArrayList;

public class University {
    private ArrayList <Professor> professors = new ArrayList<Professor>();
    private ArrayList <Student> students = new ArrayList<Student>();
    private ArrayList <Class> classes = new ArrayList<Class>();

    private int studentsId = 0;
    private int professorsId = 0;
    private int classesId = 0;

    public Professor professorExists(int professorId) {
        Professor professorMatched = null;
        for (Professor professor: this.professors) {
            if (professor.getProfessorId() == professorId) {
                professorMatched = professor;
            }
        }
        return professorMatched;
    }

    public Student studentExists(int studentId) {
        Student studentMatched = null;
        for (Student student: this.students) {
            if (student.getId() == studentId){
                studentMatched = student;
            }
        }
        return studentMatched;
    }

    public Class classExists(int classId) {
        Class classMatched = null;
        for (Class cls: this.classes) {
            if (cls.getId() == classId){
                classMatched = cls;
            }
        }
        return classMatched;
    }

    public int getStudentsId() {
        return studentsId;
    }

    public void setStudentsId(int studentsId) {
        this.studentsId = studentsId;
    }

    public int getProfessorsId() {
        return professorsId;
    }

    public void setProfessorsId(int professorsId) {
        this.professorsId = professorsId;
    }

    public int getClassesId() {
        return classesId;
    }

    public void setClassesId(int classesId) {
        this.classesId = classesId;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(ArrayList<Professor> teachers) {
        this.professors = teachers;
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
