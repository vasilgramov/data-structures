package p01_collectionOfPersons;

import java.util.*;

public class PersonCollectionImpl implements PersonCollection {

    private Map<String, Person> people;
    private Map<String, Set<Person>> byDomain;
    private Map<String, Set<Person>> byNameAndTown;
    private TreeMap<Integer, Set<Person>> byAge;
    private Map<String, Map<Integer, Set<Person>>> byAgeAndTown;

    public PersonCollectionImpl() {
        super();

        this.people = new HashMap<>();
        this.byDomain = new HashMap<>();
        this.byNameAndTown = new HashMap<>();
        this.byAge = new TreeMap<>();
        this.byAgeAndTown = new TreeMap<>();
    }

    @Override
    public boolean addPerson(String email, String name, int age, String town) {
        if (this.people.containsKey(email)) {
            return false;
        }

        Person person = new Person(email, name, age, town);

        this.people.put(email, person);

        String domain = this.getDomain(email);
        this.updateByDomain(domain, person);

        String nameTownKey = name + town;
        this.updateByNameAndTown(nameTownKey, person);

        this.updateByAge(age, person);

        this.updateByAgeAndTown(town, age, person);

        return true;
    }

    @Override
    public int getCount() {
        return this.people.size();
    }

    @Override
    public boolean deletePerson(String email) {
        if (!this.people.containsKey(email)) {
            return false;
        }

        Person person = this.people.get(email);
        this.people.remove(email);
        this.byDomain.get(this.getDomain(email)).remove(person);
        System.out.println(person.getName() + person.getTown());
        this.byNameAndTown.get(person.getName() + person.getTown()).remove(person);
        this.byAge.get(person.getAge()).remove(person);
        this.byAgeAndTown.get(person.getTown()).get(person.getAge()).remove(person);

        return true;
    }

    @Override
    public Person findPerson(String email) {
        if (!this.people.containsKey(email)) {
            return null;
        }

        return this.people.get(email);
    }

    @Override
    public Iterable<Person> findPersons(String emailDomain) {
        if (!this.byDomain.containsKey(emailDomain)) {
            return new TreeSet<>();
        }

        return this.byDomain.get(emailDomain);
    }

    @Override
    public Iterable<Person> findPersons(String name, String town) {
        String key = name + town;
        if (!this.byNameAndTown.containsKey(key)) {
            return new TreeSet<>();
        }

        return this.byNameAndTown.get(name + town);
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge) {
        Deque<Person> people = new LinkedList<>();

        Map.Entry<Integer, Set<Person>> entry = this.byAge.ceilingEntry(startAge);
        while (entry != null && entry.getKey() <= endAge) {
            people.addAll(entry.getValue());
            entry = this.byAge.ceilingEntry(entry.getKey() + 1);
        }

        return people;
    }

    @Override
    public Iterable<Person> findPersons(int startAge, int endAge, String town) {
        Deque<Person> people = new LinkedList<>();

        Map.Entry<Integer, Set<Person>> entry = this.byAge.ceilingEntry(startAge);
        while (entry != null && entry.getKey() <= endAge) {
            for (Person person : entry.getValue()) {
                if (person.getTown().equals(town)) {
                    people.addFirst(person);
                }
            }

            entry = this.byAge.ceilingEntry(entry.getKey() + 1);
        }

        return people;
    }

    private String getDomain(String email) {
        int start = email.indexOf('@');
        return email.substring(start + 1);
    }

    // DRY //
    private void updateByDomain(String domain, Person person) {
        if (!this.byDomain.containsKey(domain)) {
            this.byDomain.putIfAbsent(domain, new TreeSet<>());
        }

        Set<Person> people = this.byDomain.get(domain);
        people.add(person);
        this.byDomain.put(domain, people);
    }

    private void updateByNameAndTown(String nameTownKey, Person person) {
        if (!this.byNameAndTown.containsKey(nameTownKey)) {
            this.byNameAndTown.put(nameTownKey, new TreeSet<>());
        }

        Set<Person> people = this.byNameAndTown.get(nameTownKey);
        people.add(person);
        this.byNameAndTown.put(nameTownKey, people);
    }

    private void updateByAge(int age, Person person) {
        if (!this.byAge.containsKey(age)) {
            this.byAge.put(age, new TreeSet<>());
        }

        Set<Person> people = this.byAge.get(age);
        people.add(person);
        this.byAge.put(age, people);
    }

    private void updateByAgeAndTown(String town, int age, Person person) {
        if (!this.byAgeAndTown.containsKey(town)) {
            this.byAgeAndTown.put(town, new TreeMap<>());
        }

        if (!this.byAgeAndTown.get(town).containsKey(age)) {
            this.byAgeAndTown.get(town).put(age, new TreeSet<>());
        }

        Set<Person> people = this.byAgeAndTown.get(town).get(age);
        people.add(person);

        Map<Integer, Set<Person>> innerMap = this.byAgeAndTown.get(town);
        innerMap.put(age, people);
        this.byAgeAndTown.put(town, innerMap);
    }
}
