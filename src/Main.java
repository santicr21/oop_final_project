import model.Professor;
import model.Student;
import model.University;
import model.professors.FullTimeProfessor;
import model.professors.PartTimeProfessor;
import model.Class;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    int classesId = 0, professorsId = 0, studentsId = 0;

    // External functions
    public static Class classExists(int classId, ArrayList <Class> allClasses) {
        Class classMatched = null;

        for (Class cls: allClasses) {
            if (cls.getId() == classId){
                classMatched = cls;
            }
        }

        return classMatched;
    }

    public static Student studentExists(int studentId, ArrayList <Student> allStudents) {
        Student studentMatched = null;

        for (Student student: allStudents) {
            if (student.getId() == studentId){
                studentMatched = student;
            }
        }

        return studentMatched;
    }

    public static int catchException (Scanner scan) {
        int number = -1;
        try {
            number = scan.nextInt();
        }
        catch (InputMismatchException ex) {
            System.out.println("You must write an integer number, try again");
        }
        return number;
    }

    // Print all professors data
    public static void printProfessorsData(ArrayList<Professor> allProfessors) {
        // Iterate through all professors and print its data using toString method.
        for (Professor professor: allProfessors) {
            System.out.println(professor);
            System.out.println();
        }
    }

    // Print classes indicating its id.
    public static void printClasses(ArrayList <Class> allClasses) {
        int classIdOption = 0;
        Scanner scan = new Scanner(System.in);

        // Printing all classes id's
        for (Class cls: allClasses) {
            System.out.println("Class with id: " + cls.getId());
        }

        // Submenu to select the class id that you may want to know all its info.
        while(classIdOption >= 0) {
            System.out.println("If you want to get the data of a class, write its id, otherwise write -1: ");
            classIdOption = catchException(scan);
            if(classIdOption == -1)
                break;

            // Printing all classes with its attributes
            Class cls = classExists(classIdOption, allClasses);

            if (cls != null) {
                System.out.println("Class id: " + cls.getId());
                System.out.println("Class name: " + cls.getName());
                System.out.println("Class students: " + cls.getStudents());
                System.out.println("Class teacher: \n" + cls.getTeacher());
                System.out.println("Assigned classroom: " + cls.getAssignedClassroom());
            }
            else {
                System.out.println("Class with that id does not exists, try again");
                break;
            }
        }
    }

    static void createStudent(ArrayList<Student> allStudents, ArrayList<Class> allClasses, Main m) {
        String newStudentName;
        int newStudentAge, classId;
        Scanner scan = new Scanner(System.in);

        // Asking for name, age and class id to be added the new student
        System.out.println("Write the student name: ");
        newStudentName = scan.nextLine();

        System.out.println("Write the student age: ");
        newStudentAge = catchException(scan);
        if (newStudentAge == -1)
            return;

        System.out.println("Write the class id to be added: ");
        classId = catchException(scan);
        if (classId == -1)
            return;

        // Adding the new student to the db
        Student newStudent = new Student(m.studentsId++, newStudentName, newStudentAge);
        allStudents.add(newStudent);

        // Getting the class that matches its id with the requested to add the student to that class
        Class cls = classExists(classId, allClasses);

        if(cls != null) {
            ArrayList <Student> currentClassStudents = cls.getStudents();
            currentClassStudents.add(newStudent);
            cls.setStudents(currentClassStudents);
            System.out.println("Student added successfully");
        }
        else {
            System.out.println("That class with that id does not exists, try again!");
            m.studentsId--;
        }
    }

    public static void createClass(ArrayList <Student> allStudents, ArrayList <Professor> allProfessors, ArrayList <Class> allClasses, Main m) {
        Scanner scan = new Scanner(System.in); // Scanner
        String newClassName; // The name of the new class
        int professorId; // The professor id that will be teaching in that class
        int numberOfStudents; // The number of students that the class will have
        int studentId; // The student id that will be in the class (this will be asked for each number of student)
        boolean professorExists = false; // Auxiliary variable to see if a professor exists
        ArrayList <Student> classStudents = new ArrayList <Student>(); // Students list of the new class

        // Ask for the class name
        System.out.println("Write the class name: ");
        newClassName = scan.nextLine();

        // Ask for the number of students to add in the class
        System.out.println("Write the number of students that will be in that class, make sure that there are enough students! and at least 1 ");
        numberOfStudents = catchException(scan);
        if (numberOfStudents == -1)
            return;
        else if (numberOfStudents > allStudents.size()) {
            System.out.println("There are more students than registered, try again");
            return;
        }
        else if(numberOfStudents <= 0) {
            System.out.println("The integer number must be greater or equal than 1!");
            return;
        }

        // Request the student id to be added to the class
        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println((1 + i) + " Write the student id: ");
            studentId = catchException(scan);
            if(studentId == -1)
                return;

            // Search for the student who matches with the requested id
            Student student = studentExists(studentId, allStudents);
            if (student != null){
                classStudents.add(student);
            }
            else {
                System.out.println("Student with that id does not exist, try again!");
                return;
            }
        }

        scan.nextLine();

        System.out.println("Write the assigned classroom id: ");
        String classroomId = scan.nextLine();

        //Ask for the teacher id that will be assigned to this class
        System.out.println("Write the teacher id that will be teaching: ");
        professorId = catchException(scan);
        if (professorId == -1)
            return;

        // Search for the professor who matches the requested id
        for (Professor professor: allProfessors) {
            if (professor.getProfessorId() == professorId) {
                professorExists = true;
                // Create the new class and add it to the db
                Class newClass = new Class(m.classesId++, newClassName, classStudents, professor, classroomId);
                allClasses.add(newClass);
            }
        }

        if (!professorExists) {
            System.out.println("Professor does not exist, try again!");
            return;
        }

        System.out.println("Class registered!");
    }

    public static void listStudentsClassesById (ArrayList <Class> allClasses) {
        Scanner scan = new Scanner(System.in);
        int studentId;
        ArrayList <Student> studentsInCurrentClass;

        // Ask for the student id
        System.out.println("Write the student id: ");
        studentId = catchException(scan);
        if (studentId == -1)
            return;

        // Iterate over all classes to find the student

        for (Class cls: allClasses) {
            studentsInCurrentClass = cls.getStudents();

            // Iterate the students of that class to see if any of these match with the requested student id.
            Student student = studentExists(studentId, studentsInCurrentClass);
            if (student != null) {
                System.out.println("Class id: " + cls.getId());
                System.out.println("Class name: " + cls.getName());
                System.out.println("Class students: " + cls.getStudents());
                System.out.println("Class teacher: " + cls.getTeacher());
                System.out.println("Class assigned: " + cls.getAssignedClassroom());
                System.out.println();
            }
        }

    }

    public static void main(String args[]) {
        //Classes, professors and students id
        Main m = new Main();

        // All university (professors, students and classes)
        University university = new University();
        university.setTeachers(new ArrayList <Professor> ());
        university.setStudents(new ArrayList <Student> ());
        university.setClasses(new ArrayList <Class> ());
        ArrayList <Professor> allProfessors = university.getTeachers();
        ArrayList <Student> allStudents = university.getStudents();
        ArrayList <Class> allClasses = university.getClasses();

        // Initializing professors (time-part and full time)
        PartTimeProfessor ptp1 = new PartTimeProfessor("Felipe", 2000000, 12, m.professorsId++), ptp2 = new PartTimeProfessor("Orlando", 1500000, 23, m.professorsId++);
        FullTimeProfessor ftp1 = new FullTimeProfessor("Roberto", 1000000, 30, m.professorsId++), ftp2 = new FullTimeProfessor("Oscar", 3500000, 12, m.professorsId++);

        // Adding professors to the db
        allProfessors.add(ftp1);
        allProfessors.add(ftp2);
        allProfessors.add(ptp1);
        allProfessors.add(ptp2);

        // Creating new students
        Student s1 = new Student(m.studentsId++, "Pedro", 18), s2 = new Student(m.studentsId++, "Daniel", 20), s3 = new Student(m.studentsId++, "Juan", 19);
        Student s4 = new Student(m.studentsId++, "Juana", 18), s5 = new Student(m.studentsId++, "Rodrigo", 20), s6 = new Student(m.studentsId++, "Camila", 19);

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
        Class c1 = new Class(m.classesId++, "Spanish", studentsInClass1, ptp1, "classroom01");
        Class c2 = new Class(m.classesId++, "English", studentsInClass2, ptp2, "classroom02");
        Class c3 = new Class(m.classesId++, "Introduction to programming", studentsInClass3, ftp1, "classroom03");
        Class c4 = new Class(m.classesId++, "Introduction to system modeling", studentsInClass4, ptp1, "classroom04");

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

            option = catchException(scan);
            if(option == -1)
                break;

            switch (option) {
                case 1:
                    printProfessorsData (allProfessors);
                    break;
                case 2:
                    printClasses (allClasses);
                    break;
                case 3:
                    createStudent (allStudents, allClasses, m);
                    break;
                case 4:
                    createClass (allStudents, allProfessors, allClasses, m);
                    break;
                case 5:
                    listStudentsClassesById (allClasses);
                    break;
                case 6:
                    System.out.println("Good bye :)!");
                    break;
            }
        }
    };
}
