import java.util.*;

public class Enterprise implements IEnterprise {

    private Set<Employee> employees;

    private Map<UUID, Employee> employeeById;
    private Map<Integer, Set<Employee>> employeesByHireDate;
    private Map<Position, Set<Employee>> employeesByPosition;
    private TreeMap<Double, Set<Employee>> employeesBySalary;
    private Map<String, Set<Employee>> employeesByFirstName;
    private Map<String, Set<Employee>> employeesByFullNameAndPosition;

    public Enterprise() {
        this.employees = new LinkedHashSet<>();

        this.employeeById = new HashMap<>();
        this.employeesByHireDate = new HashMap<>();
        this.employeesByPosition = new HashMap<>();
        this.employeesBySalary = new TreeMap<>();
        this.employeesByFirstName = new HashMap<>();
        this.employeesByFullNameAndPosition = new HashMap<>();
    }

    @Override
    public int getCount() {
        return this.employeeById.size();
    }

    @Override
    public void add(Employee employee) {
        this.employees.add(employee);

        this.employeeById.put(employee.getId(), employee);

        // update employees by position
        // =====================================================================================
        if (!this.employeesByPosition.containsKey(employee.getPosition())) {
            this.employeesByPosition.put(employee.getPosition(), new LinkedHashSet<>());
        }

        Set<Employee> byPosition = this.employeesByPosition.get(employee.getPosition());
        byPosition.add(employee);
        this.employeesByPosition.put(employee.getPosition(), byPosition);
        // =====================================================================================

        // update employees by hire date
        // =====================================================================================
        if (!this.employeesByHireDate.containsKey(employee.getHireDate().getMonth())) {
            this.employeesByHireDate.put(employee.getHireDate().getMonth(), new LinkedHashSet<>());
        }

        Set<Employee> byHireDate = this.employeesByHireDate.get(employee.getHireDate().getMonth());
        byHireDate.add(employee);
        this.employeesByHireDate.put(employee.getHireDate().getMonth(), byHireDate);
        // =====================================================================================

        // update employees by salary
        // =====================================================================================
        if (!this.employeesBySalary.containsKey(employee.getSalary())) {
            this.employeesBySalary.put(employee.getSalary(), new LinkedHashSet<>());
        }

        Set<Employee> bySalaries = this.employeesBySalary.get(employee.getSalary());
        bySalaries.add(employee);
        this.employeesBySalary.put(employee.getSalary(), bySalaries);
        // =====================================================================================

        // update employees by first name
        // =====================================================================================
        if (!this.employeesByFirstName.containsKey(employee.getFirstName())) {
            this.employeesByFirstName.put(employee.getFirstName(), new LinkedHashSet<>());
        }

        Set<Employee> byFirstName = this.employeesByFirstName.get(employee.getFirstName());
        byFirstName.add(employee);
        this.employeesByFirstName.put(employee.getFirstName(), byFirstName);
        // =====================================================================================

        // update employees by full name and position
        // =====================================================================================
        String fullNameAndPosition = employee.getFirstName() + employee.getLastName() + employee.getPosition();
        if (!this.employeesByFullNameAndPosition.containsKey(fullNameAndPosition)) {
            this.employeesByFullNameAndPosition.put(fullNameAndPosition, new LinkedHashSet<>());
        }

        Set<Employee> byFullNameAndPosition = this.employeesByFullNameAndPosition.get(fullNameAndPosition);
        byFullNameAndPosition.add(employee);
        this.employeesByFullNameAndPosition.put(fullNameAndPosition, byFullNameAndPosition);
        // =====================================================================================
    }

    @Override
    public boolean contains(UUID id) {
        return this.employeeById.containsKey(id);
    }

    @Override
    public boolean contains(Employee employee) {
        return this.employeeById.containsKey(employee.getId());
    }

    @Override
    public boolean change(UUID id, Employee employee) {
        if (!this.employeeById.containsKey(id)) {
            return false;
        }

        employee.setId(id);
        this.employeeById.put(id, employee);
        return true;
    }

    @Override
    public boolean fire(UUID id) {
        if (!this.employeeById.containsKey(id)) {
            return false;
        }

        Employee toBeRemoved = this.employeeById.remove(id);

        this.employees.remove(toBeRemoved);

        this.employeeById.remove(toBeRemoved.getId());
        this.employeesByHireDate.get(toBeRemoved.getHireDate().getMonth()).remove(toBeRemoved);
        this.employeesByPosition.get(toBeRemoved.getPosition()).remove(toBeRemoved);
        this.employeesBySalary.get(toBeRemoved.getSalary()).remove(toBeRemoved);
        this.employeesByFirstName.get(toBeRemoved.getFirstName()).remove(toBeRemoved);
        this.employeesByFullNameAndPosition.get(
                toBeRemoved.getFirstName() + toBeRemoved.getLastName() + toBeRemoved.getPosition()).remove(toBeRemoved);

        return true;
    }

    @Override
    public boolean raiseSalary(int months, int percent) {
        if (!this.employeesByHireDate.containsKey(months)) {
            return false;
        }



        Set<Employee> employees = this.employeesByHireDate.get(months);
        for (Employee employee : employees) {
            this.employeesBySalary.remove(employee.getSalary());

            employee.setSalary(employee.getSalary() + (employee.getSalary() * percent / 100.0));

            if (!this.employeesBySalary.containsKey(employee.getSalary())) {
                this.employeesBySalary.put(employee.getSalary(), new LinkedHashSet<>());
            }


            Set<Employee> updatedEmployees = this.employeesBySalary.get(employee.getSalary());
            updatedEmployees.add(employee);
            this.employeesBySalary.put(employee.getSalary(), updatedEmployees);
        }

        return true;
    }

    @Override
    public Employee getByUUID(UUID id) {
        if (!this.employeeById.containsKey(id)) {
            throw new IllegalArgumentException();
        }

        return this.employeeById.get(id);
    }

    @Override
    public Position positionByUUID(UUID id) {
        if (!this.employeeById.containsKey(id)) {
            throw new IllegalArgumentException();
        }

        return this.employeeById.get(id).getPosition();
    }

    @Override
    public Iterable<Employee> getByPosition(Position position) {
        if (!this.employeesByPosition.containsKey(position) || this.employeesByPosition.get(position).isEmpty()) {
            throw new IllegalArgumentException();
        }

        return this.employeesByPosition.get(position);
    }

    @Override
    public Iterable<Employee> getBySalary(double minSalary) {
        if (this.employeesBySalary.ceilingKey(minSalary) == null) {
            throw new IllegalArgumentException();
        }

        NavigableMap<Double, Set<Employee>> doubleSetNavigableMap =
                this.employeesBySalary.subMap(minSalary, true, Double.MAX_VALUE, true);
        Deque<Employee> employees = new ArrayDeque<>();
        for (Map.Entry<Double, Set<Employee>> doubleSetEntry : doubleSetNavigableMap.entrySet()) {
            employees.addAll(doubleSetEntry.getValue());
        }

        if (employees.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return employees;
    }

    @Override
    public Iterable<Employee> getBySalaryAndPosition(double salary, Position position) {
        if (!this.employeesBySalary.containsKey(salary) || !this.employeesByPosition.containsKey(position)) {
            throw new IllegalArgumentException();
        }

        Set<Employee> bySalary = this.employeesBySalary.get(salary);
        Set<Employee> byPosition = this.employeesByPosition.get(position);

        Deque<Employee> employees = new ArrayDeque<>();

        if (bySalary.size() < byPosition.size()) {
            for (Employee employee : bySalary) {
                if (employee.getPosition().equals(position)) {
                    employees.addLast(employee);
                }
            }
        } else {
            for (Employee employee : byPosition) {
                if (employee.getSalary() == salary) {
                    employees.addLast(employee);
                }
            }
        }

        if (employees.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return employees;
    }

    @Override
    public Iterable<Employee> searchBySalary(double minSalary, double maxSalary) {
        NavigableMap<Double, Set<Employee>> doubleSetNavigableMap =
                this.employeesBySalary.subMap(minSalary, true, maxSalary, true);

        Deque<Employee> employees = new ArrayDeque<>();
        if (doubleSetNavigableMap == null || doubleSetNavigableMap.isEmpty()) {
            return employees;
        }

        for (Map.Entry<Double, Set<Employee>> doubleSetEntry : doubleSetNavigableMap.entrySet()) {
            employees.addAll(doubleSetEntry.getValue());
        }

        return employees;
    }

    @Override
    public Iterable<Employee> searchByPosition(Iterable<Position> positions) {
        Deque<Employee> employees = new ArrayDeque<>();

        for (Position position : positions) {
            if (!this.employeesByPosition.containsKey(position)) {
                continue;
            }

            Set<Employee> byPosition = this.employeesByPosition.get(position);
            employees.addAll(byPosition);
        }

        return employees;
    }

    @Override
    public Iterable<Employee> allWithPositionAndMinSalary(Position position, double minSalary) {
        if (!this.employeesByPosition.containsKey(position) || this.employeesByPosition.get(position).isEmpty()) {
            return new ArrayList<>();
        }

        if (this.employeesBySalary.ceilingKey(minSalary) == null) {
            return new ArrayList<>();
        }


        Deque<Employee> employees = new ArrayDeque<>();
        Set<Employee> byPositoin = this.employeesByPosition.get(position);
        NavigableMap<Double, Set<Employee>> bySalary =
                this.employeesBySalary.subMap(minSalary, true, Double.MAX_VALUE, true);

        if (byPositoin.size() < bySalary.size()) {
            for (Employee employee : byPositoin) {
                if (employee.getSalary() > minSalary) {
                    employees.addLast(employee);
                }
            }
        } else {
            for (Set<Employee> employeeSet : bySalary.values()) {
                for (Employee employee : employeeSet) {
                    if (employee.getPosition().equals(position)) {
                        employees.add(employee);
                    }
                }
            }
        }

        return employees;
    }

    @Override
    public Iterable<Employee> searchByFirstName(String firstName) {
        if (!this.employeesByFirstName.containsKey(firstName)) {
            return new ArrayList<>();
        }

        return this.employeesByFirstName.get(firstName);
    }

    @Override
    public Iterable<Employee> searchByNameAndPosition(String firstName, String lastName, Position position) {
        String fullNameAndPosition = firstName + lastName + position;
        if (!this.employeesByFullNameAndPosition.containsKey(fullNameAndPosition)) {
            return new ArrayList<>();
        }

        return this.employeesByFullNameAndPosition.get(fullNameAndPosition);
    }

    @Override
    public Iterator<Employee> iterator() {
        return this.employees.iterator();
    }
}
