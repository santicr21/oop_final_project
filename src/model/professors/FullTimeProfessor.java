package model.professors;

import model.Professor;

public class FullTimeProfessor extends Professor {

    private int experienceYears;

    // With this method we can calculate the salary of the current full time professor
    @Override
    public void calculateSalary() {
        float baseSalary = super.getBaseSalary();
        float salary = baseSalary * experienceYears * 110/100;
        super.setSalary(salary);
    }
}
