package p01_collectionOfPersons;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Startup {
    public static void main(String[] args) {
        PersonCollection personCollection = new PersonCollection();

        Person person4 = new Person("Stamat", 11, "baa@gmail.com", "Plovdiv");
        Person person3 = new Person("Mariika", 22, "aab@abv.com", "Sofia");
        Person person1 = new Person("Pesho", 17, "aaa@java.com", "Burgas");
        Person person5 = new Person("Svetlio", 42, "asd@java.com", "Burgas");
        Person person2 = new Person("Gosho", 17, "aba@gmail.com", "Varna");
        Person person6 = new Person("Angel", 33, "bfg@net.com", "Burgas");
        Person person7 = new Person("Dobrin", 25, "dga@abv.com", "Burgas");
        Person person8 = new Person("Gosho", 17, "zzz@gmail.com", "Plovdiv");

        personCollection.addPerson(person1);
        personCollection.addPerson(person2);
        personCollection.addPerson(person3);
        personCollection.addPerson(person4);
        personCollection.addPerson(person5);
        personCollection.addPerson(person6);
        personCollection.addPerson(person7);
        personCollection.addPerson(person8);


//        System.out.println("if person is already added");
//        System.out.println(personCollection.addPerson(person7));
//        System.out.println(personCollection.addPerson(person7));
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        System.out.println("find by email, if !contains returns null");
//        Person personWithEmailAAA = personCollection.findByEmail("qqq@java.com");
//        System.out.println(personWithEmailAAA);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        System.out.println("deleting person by email");
//        System.out.println(personCollection.deletePersonByEmail("qqq@java.com"));
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        System.out.println("get all persons with specific domain");
//        TreeMap<String, Person> sortedByEmail = personCollection.getAllWithDomain("@gmail.com");
//        for (Map.Entry<String, Person> entry : sortedByEmail.entrySet()) {
//            String key = entry.getKey();
//            Person value = entry.getValue();
//
//            System.out.println("domain -> @gmail.com -> " + value);
//        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        TreeMap<String, Person> allWithSpecificNameAndTown = personCollection.getAllByNameAndTown("Gosho", "Varna");
//        for (Map.Entry<String, Person> entry : allWithSpecificNameAndTown.entrySet()) {
//            String key = entry.getKey();
//            Person value = entry.getValue();
//
//            System.out.println("all with name: Gosho and town: Varna => " + value);
//        }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        System.out.println("all between 1 - 18");
//        SortedMap<Integer, ArrayList<Person>> allInInterval = personCollection.getAllInInterval(1, 18);
//        for (Map.Entry<Integer, ArrayList<Person>> entry : allInInterval.entrySet()) {
//            Integer key = entry.getKey();
//            ArrayList<Person> value = entry.getValue();
//            for (Person person : value) {
//                System.out.println(person);
//            }
//        }

        System.out.println("get all persons in age interval && in specific town");
        System.out.println("in Plovdiv and between 0 - 19");
        SortedMap<Integer, ArrayList<Person>> getInIntervalInTown = personCollection.getAllInIntervalInTown("Plovdiv", 0, 19);
        for (Map.Entry<Integer, ArrayList<Person>> entry : getInIntervalInTown.entrySet()) {
            Integer key = entry.getKey();
            ArrayList<Person> value = entry.getValue();

            for (Person person : value) {
                System.out.println(person);
            }
        }
    }
}
