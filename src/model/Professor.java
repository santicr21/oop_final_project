package model;

public abstract class Professor {
    private String name;
    private float baseSalary;
    private int professorId;

    public Professor(String name, float baseSalary, int professorId) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.professorId = professorId;
    }

    public abstract float calculateSalary();

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(float baseSalary) {
        this.baseSalary = baseSalary;
    }
}
