package employeeService.employeeBook.controller;

public interface EmployeeBookService {
    void add(Employee employee);
    void find(String initials);
    void remove(String initials);

    void getAll();
}
