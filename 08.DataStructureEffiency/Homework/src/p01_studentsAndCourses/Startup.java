package p01_studentsAndCourses;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Startup {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        TreeMap<String, ArrayList<Person>> personsByCourse = new TreeMap<String, ArrayList<Person>>();

        Person person1 = new Person("Stela", "Mineva", "Java");
        Person person2 = new Person("Yasen", "Ivanov", "C#");
        Person person3 = new Person("Stefla", "Nikolova", "SQL");
        Person person4 = new Person("Miroslav", "Tsenov", "SQL");
        Person person5 = new Person("Milena", "Petrova", "C#");
        Person person6 = new Person("Asya", "Yankova", "SQL");
        Person person7 = new Person("Ivan", "Grigorov", "C#");
        Person person8 = new Person("Vanya", "Angelova", "Java");
        Person person9 = new Person("Todor", "Georgiev", "SQL");

        ArrayList<Person> personArrayList = new ArrayList<Person>();
        personArrayList.add(person1);
        personArrayList.add(person2);
        personArrayList.add(person3);
        personArrayList.add(person4);
        personArrayList.add(person5);
        personArrayList.add(person6);
        personArrayList.add(person7);
        personArrayList.add(person8);
        personArrayList.add(person9);

        for (Person person : personArrayList) {
            if (!personsByCourse.containsKey(person.getCourse())) {
                personsByCourse.put(person.getCourse(), new ArrayList<Person>());
            }

            ArrayList<Person> currentArrayList = personsByCourse.get(person.getCourse());
            currentArrayList.add(person);
            personsByCourse.put(person.getCourse(), currentArrayList);
        }

        for (Map.Entry<String, ArrayList<Person>> entry : personsByCourse.entrySet()) {
            String key = entry.getKey();
            ArrayList<Person> value = entry.getValue();

            System.out.println(key + ": ");
            for (Person person : value) {
                System.out.print(person);
            }

            System.out.println();
        }
    }
}
