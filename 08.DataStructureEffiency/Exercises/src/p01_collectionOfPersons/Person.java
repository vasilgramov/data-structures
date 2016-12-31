package p01_collectionOfPersons;

public class Person {
    private String name;
    private int age;
    private String email;
    private String town;

    //----------------------------------------------------------------
    public Person(String name, int age, String email, String town) {
        this.setName(name);
        this.setAge(age);
        this.setEmail(email);
        this.setTown(town);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public String getTown() {
        return town;
    }

    private void setTown(String town) {
        this.town = town;
    }
    //----------------------------------------------------------------


    @Override
    public String toString() {
        return this.getName() + " - " + this.getAge() + " - " + this.getEmail() + " - " + this.getTown();
    }
}
