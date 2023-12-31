package model.professors;

import model.Professor;

public class PartTimeProfessor extends Professor {
    private int activeHoursPerWeek;

    @Override
    public String toString() {
        return "Professor's id: " + super.getProfessorId() + " Professor's name: " + super.getName() + " Professor's base salary: " + super.getBaseSalary() + " Salary: " + this.calculateSalary() + " Active years per week: " + this.activeHoursPerWeek;
    }

    public PartTimeProfessor(String name, float baseSalary, int activeHoursPerWeek, int professorId) {
        super(name, baseSalary, professorId);
        this.activeHoursPerWeek = activeHoursPerWeek;
    }

    // With this method we can calculate the salary of the current part-time professor
    @Override
    public float calculateSalary() {
        float baseSalary = super.getBaseSalary();
        return baseSalary * this.activeHoursPerWeek;
    }

    public int getActiveHoursPerWeek() {
        return activeHoursPerWeek;
    }

    public void setActiveHoursPerWeek(int activeHoursPerWeek) {
        this.activeHoursPerWeek = activeHoursPerWeek;
    }
}
