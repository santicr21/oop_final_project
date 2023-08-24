package model.professors;

import model.Professor;

public class FullTimeProfessor extends Professor {

    private int experienceYears;

    @Override
    public String toString() {
        return "Professor's id: " + super.getProfessorId() + " Professor's name: " + super.getName() + " Professor's base salary: " + super.getBaseSalary() + " Salary: " + this.calculateSalary() + " Experience in years: " + this.experienceYears;
    }

    public FullTimeProfessor(String name, float baseSalary, int experienceYears, int professorId) {
        super(name, baseSalary, professorId);
        this.experienceYears = experienceYears;
    }

    // With this method we can calculate the salary of the current full time professor
    @Override
    public float calculateSalary() {
        float baseSalary = super.getBaseSalary();
        return baseSalary * experienceYears * 110/100;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }
}
