package p01_collectionOfPersons;

import java.util.*;

public class PersonCollection {
    private TreeMap<String, Person> sortedByEmail;
    private HashMap<String, TreeMap<String, Person>> byDomain;
    private HashMap<String, TreeMap<String, Person>> byNameAndTown;
    private TreeMap<Integer, ArrayList<Person>> sortedByAge;
    private HashMap<String, TreeMap<Integer, ArrayList<Person>>> byTownAndAge;

    public PersonCollection() {
        this.sortedByEmail = new TreeMap<String, Person>();
        this.byDomain = new HashMap<String, TreeMap<String, Person>>();
        this.byNameAndTown = new HashMap<String, TreeMap<String, Person>>();
        this.sortedByAge = new TreeMap<Integer, ArrayList<Person>>();
        this.byTownAndAge = new HashMap<String, TreeMap<Integer, ArrayList<Person>>>();
    }

    public boolean addPerson(Person person) {
        if (this.sortedByEmail.containsKey(person.getEmail())) {
            return false;
        }

        toSortedByEmail(person);
        //---------------------------------------------------------
        toSeparatedByDomain(person);
        //---------------------------------------------------------
        toSeparatedByNameAndTown(person);
        //---------------------------------------------------------
        toSortedByAge(person);
        //---------------------------------------------------------
        if (!this.byTownAndAge.containsKey(person.getTown())) {
            this.byTownAndAge.put(person.getTown(), new TreeMap<Integer, ArrayList<Person>>());
        }

        TreeMap<Integer, ArrayList<Person>> currentTreeMap = this.byTownAndAge.get(person.getTown());
        if (!currentTreeMap.containsKey(person.getAge())) {
            currentTreeMap.put(person.getAge(), new ArrayList<>());
        }

        ArrayList<Person> currentList = currentTreeMap.get(person.getAge());
        currentList.add(person);

        currentTreeMap.put(person.getAge(), currentList);
        this.byTownAndAge.put(person.getTown(), currentTreeMap);

        return true;
    }

    private void toSortedByAge(Person person) {
        if (!this.sortedByAge.containsKey(person.getAge())) {
            this.sortedByAge.put(person.getAge(), new ArrayList<Person>());
        }
        ArrayList<Person> currentList = this.sortedByAge.get(person.getAge());
        currentList.add(person);
        this.sortedByAge.put(person.getAge(), currentList);
    }

    private void toSeparatedByNameAndTown(Person person) {
        String nameAndTown = person.getName() + "$" + person.getTown();
        if (!this.byNameAndTown.containsKey(nameAndTown)) {
            this.byNameAndTown.put(nameAndTown, new TreeMap<>());
        }
        TreeMap<String, Person> currentTreeMap = this.byNameAndTown.get(nameAndTown);
        currentTreeMap.put(person.getEmail(), person);
        this.byNameAndTown.put(nameAndTown, currentTreeMap);
    }

    private void toSeparatedByDomain(Person person) {
        String domain = person.getEmail().substring(person.getEmail().indexOf('@'), person.getEmail().length());
        if (!this.byDomain.containsKey(domain)) {
            this.byDomain.put(domain, new TreeMap<>());
        }
        TreeMap<String, Person> currentTreeMap = this.byDomain.get(domain);
        currentTreeMap.put(person.getEmail(), person);
        this.byDomain.put(domain, currentTreeMap);
    }

    private void toSortedByEmail(Person person) {
        this.sortedByEmail.put(person.getEmail(), person);
    }

    public Person findByEmail(String email) {
        if (!this.sortedByEmail.containsKey(email)) {
            return null;
        }

        return this.sortedByEmail.get(email);
    }

    public boolean deletePersonByEmail(String email) {
        if (!this.sortedByEmail.containsKey(email)) {
            return false;
        }

        this.sortedByEmail.remove(email);
        return true;
    }

    public TreeMap<String, Person> getAllWithDomain(String domain) {
        if (!this.byDomain.containsKey(domain)) {
            return null;
        }

        return this.byDomain.get(domain);
    }

    public TreeMap<String, Person> getAllByNameAndTown(String name, String town) {
        String nameAndTown = name + "$" + town;
        if (!this.byNameAndTown.containsKey(nameAndTown)) {
            return null;
        }

        return this.byNameAndTown.get(nameAndTown);
    }

    public SortedMap<Integer, ArrayList<Person>> getAllInInterval(int start, int end) {
        return this.sortedByAge.subMap(start, end);
    }

    public SortedMap<Integer, ArrayList<Person>> getAllInIntervalInTown(String town, int start, int end) {
        TreeMap<Integer, ArrayList<Person>> currentTreeMap = this.byTownAndAge.get(town);

        return currentTreeMap.subMap(start, end);
    }
}
