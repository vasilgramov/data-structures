package p02;

import java.util.UUID;

public interface IEnterprise extends Iterable<Employee>{

    void add(Employee employee);
    boolean contains(UUID id);
    boolean contains(Employee employee);
    boolean change(UUID id, Employee employee);
    boolean fire(UUID id);
    boolean raiseSalary(int months, int percent);
    int getCount();

    Employee getByUUID(UUID id);
    Position positionByUUID(UUID id);

    Iterable<Employee> getByPosition(Position position);
    Iterable<Employee> getBySalary(double minSalary);
    Iterable<Employee> getBySalaryAndPosition(double salary, Position position);

    Iterable<Employee> searchBySalary(double minSalary, double maxSalary);
    Iterable<Employee> searchByPosition(Iterable<Position> positions);
    Iterable<Employee> allWithPositionAndMinSalary(Position position, double minSalary);
    Iterable<Employee> searchByFirstName(String firstName);
    Iterable<Employee> searchByNameAndPosition(String firstName, String lastName, Position position);

    /*
    void Add(p02.Employee employee);
    bool Change(Guid guid, p02.Employee employee);
    bool Fire(Guid guid);
    bool RaiseSalary(int months, int percent);
    int Count { get; set; }

    p02.Employee GetByGuid(Guid guid);
    p02.Position PositionByGuid(Guid guid);

    IEnumerable<p02.Employee> GetByPosition(p02.Position position);
    IEnumerable<p02.Employee> GetBySalary(double minSalary);
    IEnumerable<p02.Employee> GetBySalaryAndPosition(double salary, p02.Position position);

    IEnumerable<p02.Employee> SearchBySalary(double minSalary, double maxSalary);
    IEnumerable<p02.Employee> SearchByPosition(IEnumerable<p02.Position> positions);
    IEnumerable<p02.Employee> AllWithPositionAndMinSalary(p02.Position position, double minSalary);
    IEnumerable<p02.Employee> SearchByFirstName(string firstName);
    IEnumerable<p02.Employee> SearchByNameAndPosition(string firstName, string lastName, p02.Position position);
     */
}
