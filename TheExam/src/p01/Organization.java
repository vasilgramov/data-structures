package p01;

import java.util.*;
import java.util.stream.Collectors;

public class Organization implements IOrganization {

    private Map<String, ArrayList<Person>> people;
    private List<Person> peopleByInsertOrder;
    private TreeMap<Integer, ArrayList<Person>> peopleByNameLength;


    public Organization() {
        this.people = new HashMap<>();
        this.peopleByInsertOrder = new ArrayList<>();
        this.peopleByNameLength = new TreeMap<>();
    }

    @Override
    public int getCount() {
        return this.peopleByInsertOrder.size();
    }

    @Override
    public boolean contains(Person person) {
        return this.people.containsKey(person.getName()) && this.people.get(person.getName()).contains(person);
    }

    @Override
    public boolean containsByName(String name) {
        return this.people.containsKey(name) && this.people.get(name).size() > 0;
    }

    @Override
    public void add(Person person) {
        if (!this.people.containsKey(person.getName())) {
            this.people.put(person.getName(), new ArrayList<>());
        }

        this.people.get(person.getName()).add(person);

        this.peopleByInsertOrder.add(person);

        if (!this.peopleByNameLength.containsKey(person.getName().length())) {
            this.peopleByNameLength.put(person.getName().length(), new ArrayList<>());
        }

        this.peopleByNameLength.get(person.getName().length()).add(person);
    }

    @Override
    public Person getAtIndex(int index) {
        if (index >= this.peopleByInsertOrder.size()) {
            throw new IllegalArgumentException();
        }

        return this.peopleByInsertOrder.get(index);
    }

    @Override
    public Iterable<Person> getByName(String name) {
        if (!this.people.containsKey(name)) {
            return new ArrayList<>();
        }

        return this.people.get(name);
    }

    @Override
    public Iterable<Person> firstByInsertOrder() {
        return this.peopleByInsertOrder.stream().limit(1).collect(Collectors.toList());
    }

    @Override
    public Iterable<Person> firstByInsertOrder(int count) {
        return this.peopleByInsertOrder.stream().limit(count).collect(Collectors.toList());
    }

    @Override
    public Iterable<Person> searchWithNameSize(int minLength, int maxLength) {
        NavigableMap<Integer, ArrayList<Person>> integerListNavigableMap = this.peopleByNameLength.subMap(minLength, true, maxLength, true);
        Deque<Person> people = new ArrayDeque<>();
        for (Map.Entry<Integer, ArrayList<Person>> integerListEntry : integerListNavigableMap.entrySet()) {
            people.addAll(integerListEntry.getValue());
        }

        return people;
    }

    @Override
    public Iterable<Person> getWithNameSize(int length) {
        if (!this.peopleByNameLength.containsKey(length)) {
            throw new IllegalArgumentException();
        }

        return this.peopleByNameLength.get(length);
    }

    @Override
    public Iterable<Person> peopleByInsertOrder() {
        return this.peopleByInsertOrder;
    }

    @Override
    public Iterator<Person> iterator() {
        return this.peopleByInsertOrder.iterator();
    }
}
