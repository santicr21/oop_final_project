import model.Professor;
import model.Student;
import model.professors.FullTimeProfessor;
import model.professors.PartTimeProfessor;
import model.Class;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        ArrayList <Professor> allProfessors = new ArrayList <Professor> ();
        ArrayList <Student> allStudents = new ArrayList <Student> ();
        ArrayList <Class> allClasses = new ArrayList <Class> ();

        PartTimeProfessor ptp1 = new PartTimeProfessor("Felipe", 2000000), ptp2 = new PartTimeProfessor("Orlando", 1500000);
        FullTimeProfessor ftp1 = new FullTimeProfessor("Roberto", 1000000), ftp2 = new FullTimeProfessor("Oscar", 3500000);

        Student s1 = new Student(0, "Pedro", 18), s2 = new Student(1, "Daniel", 20), s3 = new Student(2, "Juan", 19);
        Student s4 = new Student(3, "Juana", 18), s5 = new Student(4, "Rodrigo", 20), s6 = new Student(5, "Camila", 19);
        
        ArrayList <Student> students = new ArrayList <Student> ();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        Class c1 = new Class(0, "Spanish", students, ptp1, "classroom01");
        Class c2 = new Class(0, "English", students, ptp2, "classroom02");
        Class c3 = new Class(0, "Introduction to programming", students, ftp1, "classroom03");
        Class c4 = new Class(0, "Introduction to system modeling", students, ptp1, "classroom04");

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
                        System.out.println("Professor: " + professor.getName());
                        System.out.println("Base Salary: " + professor.getBaseSalary());
                        System.out.println("Salary: " + professor.calculateSalary());
                    }
                    break;
                case 2:
                    System.out.println("Se presiono un 2");
                    break;
                case 3:
                    System.out.println("Se presiono un 3");
                    break;
                case 4:
                    System.out.println("Se presiono un 4");
                    break;
                case 5:
                    System.out.println("Se presiono un 5");
                    break;
                case 6:
                    System.out.println("Se presiono un 6");
                    break;
            }

        }

    };
}
