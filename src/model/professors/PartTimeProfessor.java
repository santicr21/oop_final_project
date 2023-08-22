package model.professors;

import model.Professor;

public class PartTimeProfessor extends Professor {
    private int activeHoursPerWeek;

    // With this method we can calculate the salary of the current part-time professor
    @Override
    public void calculateSalary() {
        float baseSalary = super.getBaseSalary();
        float salary = baseSalary * this.activeHoursPerWeek;
        super.setSalary(salary);
    }

    public int getActiveHoursPerWeek() {
        return activeHoursPerWeek;
    }

    public void setActiveHoursPerWeek(int activeHoursPerWeek) {
        this.activeHoursPerWeek = activeHoursPerWeek;
    }
}
