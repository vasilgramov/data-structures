package p01;

public class Person implements Comparable<Person> {

    private String name;

    private double salary;

    public Person(String name, double salary) {
        this.setName(name);
        this.setSalary(salary);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public int compareTo(Person o) {
        int nameComparison = this.name.compareTo(o.getName());

        return nameComparison != 0 ? nameComparison : Double.compare(this.salary, o.getSalary());
    }
}
