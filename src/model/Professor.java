package model;

public abstract class Professor {
    private String name;
    private float baseSalary;

    public Professor(String name, float baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public abstract float calculateSalary();

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
