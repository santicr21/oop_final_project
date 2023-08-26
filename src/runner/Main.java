package runner;
import model.Professor;
import model.Student;
import model.University;
import model.Class;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        // All university (professors, students and classes, students id, professors id, and classes id)
        Init initVars = new Init();
        University university = initVars.initVariables();

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
                    printProfessorsData (university);
                    break;
                case 2:
                    printClasses (university);
                    break;
                case 3:
                    createStudent (university);
                    break;
                case 4:
                    createClass (university);
                    break;
                case 5:
                    listStudentsClassesById (university);
                    break;
                case 6:
                    System.out.println("Good bye :)!");
                    break;
            }
        }
    };

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
    public static void printProfessorsData(University university) {
        // Iterate through all professors and print its data using toString method.
        for (Professor professor: university.getProfessors()) {
            System.out.println(professor);
            System.out.println();
        }
    }

    // Print classes indicating its id.
    public static void printClasses(University university) {
        int classIdOption = 0;
        Scanner scan = new Scanner(System.in);

        // Printing all classes id's
        for (Class cls: university.getClasses()) {
            System.out.println("Class with id: " + cls.getId());
        }

        // Submenu to select the class id that you may want to know all its info.
        while(classIdOption >= 0) {
            System.out.println("If you want to get the data of a class, write its id, otherwise write -1: ");
            classIdOption = catchException(scan);
            if(classIdOption == -1)
                break;

            // Printing all classes with its attributes
            Class cls = university.classExists(classIdOption);

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

    static void createStudent(University university) {
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
        int studentsId = university.getStudentsId();
        Student newStudent = new Student(studentsId, newStudentName, newStudentAge);
        university.setStudentsId(studentsId + 1);
        university.getStudents().add(newStudent);

        // Getting the class that matches its id with the requested to add the student to that class
        Class cls = university.classExists(classId);

        if(cls != null) {
            ArrayList <Student> currentClassStudents = cls.getStudents();
            currentClassStudents.add(newStudent);
            cls.setStudents(currentClassStudents);
            System.out.println("Student added successfully");
        }
        else {
            System.out.println("That class with that id does not exists, try again!");
            // studentsId--;
        }
    }

    public static void createClass(University university) {
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

        if (numberOfStudents == -1) // Verify if input is a valid number
            return;
        else if (numberOfStudents > university.getStudents().size()) { // verify if number of students to be added entered is valid
            System.out.println("There are more students than registered, try again");
            return;
        }
        else if(numberOfStudents <= 0) { // Verify if number of students to be added is not negative
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
            Student student = university.studentExists(studentId);
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

        Professor professor = university.professorExists(professorId); // Search the professor
        if (professor != null) {
            int classesId = university.getClassesId();
            Class newClass = new Class(classesId, newClassName, classStudents, professor, classroomId);
            university.setClassesId(classesId + 1);
            university.getClasses().add(newClass);
        }
        else {
            System.out.println("Professor does not exist, try again!");
            return;
        }

        System.out.println("Class registered!");
    }

    //This function list the classes of a student, given his id.
    public static void listStudentsClassesById (University university) {
        Scanner scan = new Scanner(System.in);
        int studentId;
        ArrayList <Student> studentsInCurrentClass;

        // Ask for the student id
        System.out.println("Write the student id: ");
        studentId = catchException(scan);
        if (studentId == -1)
            return;

        // Iterate over all classes to find the student
        for (Class cls: university.getClasses()) {
            studentsInCurrentClass = cls.getStudents();

            Student student = cls.studentInClass(studentId); // Search for the student given the student id
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
}
