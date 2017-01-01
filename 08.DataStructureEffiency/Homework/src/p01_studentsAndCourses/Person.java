package p01_studentsAndCourses;

public class Person {
    private String firstName;
    private String lastName;
    private String course;

    public Person(String firstName, String lastName, String course) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setCourse(course);
    }

    public String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCourse() {
        return course;
    }

    private void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName() + "|";
    }
}
