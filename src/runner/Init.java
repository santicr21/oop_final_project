package runner;
import model.Class;
import model.Professor;
import model.Student;
import model.University;
import model.professors.FullTimeProfessor;
import model.professors.PartTimeProfessor;

import java.util.ArrayList;

public class Init {
    public University initVariables(){
        University university = new University();
        university.setProfessors(new ArrayList<Professor>());
        university.setStudents(new ArrayList <Student> ());
        university.setClasses(new ArrayList <Class> ());
        ArrayList <Professor> allProfessors = university.getProfessors();
        ArrayList <Student> allStudents = university.getStudents();
        ArrayList <Class> allClasses = university.getClasses();
        int studentsId = university.getStudentsId();
        int professorsId = university.getProfessorsId();
        int classesId = university.getClassesId();

        // Initializing professors (time-part and full time)
        PartTimeProfessor ptp1 = new PartTimeProfessor("Felipe", 2000000, 12, professorsId++), ptp2 = new PartTimeProfessor("Orlando", 1500000, 23, professorsId++);
        FullTimeProfessor ftp1 = new FullTimeProfessor("Roberto", 1000000, 30, professorsId++), ftp2 = new FullTimeProfessor("Oscar", 3500000, 12, professorsId++);
        university.setProfessorsId(professorsId);
        // Adding professors to the db
        allProfessors.add(ftp1);
        allProfessors.add(ftp2);
        allProfessors.add(ptp1);
        allProfessors.add(ptp2);

        // Creating new students
        Student s1 = new Student(studentsId++, "Pedro", 18), s2 = new Student(studentsId++, "Daniel", 20), s3 = new Student(studentsId++, "Juan", 19);
        Student s4 = new Student(studentsId++, "Juana", 18), s5 = new Student(studentsId++, "Rodrigo", 20), s6 = new Student(studentsId++, "Camila", 19);
        university.setStudentsId(studentsId);
        // Adding students to the university
        allStudents.add(s1);
        allStudents.add(s2);
        allStudents.add(s3);
        allStudents.add(s4);
        allStudents.add(s5);
        allStudents.add(s6);

        ArrayList <Student> studentsInClass1 = new ArrayList<>();
        studentsInClass1.add(s1);
        studentsInClass1.add(s2);
        studentsInClass1.add(s3);

        ArrayList <Student> studentsInClass2 = new ArrayList<>();
        studentsInClass2.add(s1);
        studentsInClass2.add(s2);
        studentsInClass2.add(s3);

        ArrayList <Student> studentsInClass3 = new ArrayList<>();
        studentsInClass3.add(s1);
        studentsInClass3.add(s2);
        studentsInClass3.add(s3);

        ArrayList <Student> studentsInClass4 = new ArrayList<>();
        studentsInClass4.add(s1);
        studentsInClass4.add(s2);
        studentsInClass4.add(s3);

        // Creating classes where all students registered are in all classes
        Class c1 = new Class(classesId++, "Spanish", studentsInClass1, ptp1, "classroom01");
        Class c2 = new Class(classesId++, "English", studentsInClass2, ptp2, "classroom02");
        Class c3 = new Class(classesId++, "Introduction to programming", studentsInClass3, ftp1, "classroom03");
        Class c4 = new Class(classesId++, "Introduction to system modeling", studentsInClass4, ptp1, "classroom04");
        university.setClassesId(classesId);

        //Adding classes to the university
        allClasses.add(c1);
        allClasses.add(c2);
        allClasses.add(c3);
        allClasses.add(c4);

        return university;
    }
}
