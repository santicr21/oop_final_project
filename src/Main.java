import model.Professor;
import model.Student;
import model.University;
import model.professors.FullTimeProfessor;
import model.professors.PartTimeProfessor;
import model.Class;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        //Classes, professors and students id
        int classesId = 0, professorsId = 0, studentsId = 0;

        // All university (professors, students and classes)
        University university = new University();
        university.setTeachers(new ArrayList <Professor> ());
        university.setStudents(new ArrayList <Student> ());
        university.setClasses(new ArrayList <Class> ());

        ArrayList <Professor> allProfessors = university.getTeachers();
        ArrayList <Student> allStudents = university.getStudents();
        ArrayList <Class> allClasses = university.getClasses();

        // Initializing professors (time-part and full time)
        PartTimeProfessor ptp1 = new PartTimeProfessor("Felipe", 2000000, 12, professorsId++), ptp2 = new PartTimeProfessor("Orlando", 1500000, 23, professorsId++);
        FullTimeProfessor ftp1 = new FullTimeProfessor("Roberto", 1000000, 30, professorsId++), ftp2 = new FullTimeProfessor("Oscar", 3500000, 12, professorsId++);

        // Adding professors to the db
        allProfessors.add(ftp1);
        allProfessors.add(ftp2);
        allProfessors.add(ptp1);
        allProfessors.add(ptp2);

        // Creating new students
        Student s1 = new Student(studentsId++, "Pedro", 18), s2 = new Student(studentsId++, "Daniel", 20), s3 = new Student(studentsId++, "Juan", 19);
        Student s4 = new Student(studentsId++, "Juana", 18), s5 = new Student(studentsId++, "Rodrigo", 20), s6 = new Student(studentsId++, "Camila", 19);

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

        //Adding classes to the university
        allClasses.add(c1);
        allClasses.add(c2);
        allClasses.add(c3);
        allClasses.add(c4);

        System.out.println("Welcome to the University tracking system!, type the option you want to use:");
        int option = 1;
        Scanner scan;

        while(option >= 1 && option < 6) {
            scan = new Scanner(System.in);
            System.out.println("1 to show all professors data.");
            System.out.println("2 to show the classes id.");
            System.out.println("3 to register a new student and add it to a class.");
            System.out.println("4 to register a new class.");
            System.out.println("5 to see the classes of a student by his id.");
            System.out.println("6 to exit the program.");

            option = scan.nextInt();

            switch (option) {
                case 1:
                    for (Professor professor: allProfessors) {
                        System.out.println(professor);
                        System.out.println();
                    }

                    break;
                case 2:
                    int classOption = 0;

                    // Printing all classes id's
                    for (Class cls: allClasses) {
                        System.out.println("Class with id: " + cls.getId());
                    }

                    // Submenu to select the class id that you may want to know all its info.
                    while(classOption >= 0) {
                        System.out.println("If you want to get the data of a class, write its id, otherwise write -1: ");
                        classOption = scan.nextInt();

                        // Printing all classes with its attributes
                        for (Class cls : allClasses) {
                            if(cls.getId() == classOption){
                                System.out.println("Class id: " + cls.getId());
                                System.out.println("Class name: " + cls.getName());
                                System.out.println("Class students: " + cls.getStudents());
                                System.out.println("Class teacher: \n" + cls.getTeacher());
                                System.out.println("Assigned classroom: " + cls.getAssignedClassroom());
                            }
                        }
                    }

                    break;
                case 3:
                    String newStudentName;
                    int newStudentAge, classId;
                    scan.nextLine();

                    // Asking for name, age and class id to be added the new student
                    System.out.println("Write the student name: ");
                    newStudentName = scan.nextLine();

                    System.out.println("Write the student age: ");
                    newStudentAge = scan.nextInt();

                    System.out.println("Write the class id to be added: ");
                    classId = scan.nextInt();

                    // Adding the new student to the db
                    Student newStudent = new Student(studentsId++, newStudentName, newStudentAge);
                    allStudents.add(newStudent);

                    // Getting the class that matches its id with the requested to add the student to that class
                    for (Class cls: allClasses) {
                        if(cls.getId() == classId) {
                            ArrayList <Student> currentClassStudents = cls.getStudents();
                            currentClassStudents.add(newStudent);
                            cls.setStudents(currentClassStudents);
                        }
                    }

                    System.out.println("Student added successfully");

                    break;
                case 4:
                    scan.nextLine();
                    String newClassName;
                    int professorId;
                    int numberOfStudents;
                    int studentId;
                    ArrayList <Student> classStudents = new ArrayList <Student>();

                    // Ask for the class name
                    System.out.println("Write the class name: ");
                    newClassName = scan.nextLine();

                    //Ask for the teacher id that will be assigned to this class
                    System.out.println("Write the teacher id that will be teaching: ");
                    professorId = scan.nextInt();

                    // Ask for the number of students to add in the class
                    System.out.println("Write the number of students that will be in that class, make sure that there are enough students! ");
                    numberOfStudents = scan.nextInt();

                    // Request the student id to be added to the class
                    for (int i = 0; i < numberOfStudents; i++) {
                        System.out.println("Write the student id: ");
                        studentId = scan.nextInt();
                        Student studentToBeInClass = new Student();

                        // Search for the student who matches with the requested id
                        for (Student student: allStudents) {
                            if (student.getId() == studentId) {
                                studentToBeInClass = student;
                            }
                        }

                        classStudents.add(studentToBeInClass);
                    }

                    scan.nextLine();

                    System.out.println("Write the assigned classroom id: ");
                    String classroomId = scan.nextLine();

                    // Search for the professor who matches the requested id
                    for (Professor professor: allProfessors) {
                        if (professor.getProfessorId() == professorId) {
                            // Create the new class and add it to the db
                            Class newClass = new Class(classesId++, newClassName, classStudents, professor, classroomId);
                            allClasses.add(newClass);
                        }
                    }

                    break;
                case 5:
                    // Ask for the student id
                    System.out.println("Write the student id: ");
                    studentId = scan.nextInt();
                    ArrayList <Student> studentsInCurrentClass = new ArrayList <Student>();

                    // Iterate over all classes to find the student
                    for (Class cls: allClasses) {
                        boolean isInClass = false;
                        studentsInCurrentClass = cls.getStudents();

                        // Iterate the students of that class to see if any of these match with the requested student id.
                        for (Student student: studentsInCurrentClass) {
                            if (student.getId() == studentId) {
                                isInClass = true;
                            }
                        }

                        if (isInClass) {
                            System.out.println("Class id: " + cls.getId());
                            System.out.println("Class name: " + cls.getName());
                            System.out.println("Class students: " + cls.getStudents());
                            System.out.println("Class teacher: " + cls.getTeacher());
                            System.out.println("Class teacher: " + cls.getAssignedClassroom());
                            System.out.println();
                        }

                    }
                    break;

                case 6:
                    System.out.println("Good bye :)!");
                    break;
            }
        }
    };
}
