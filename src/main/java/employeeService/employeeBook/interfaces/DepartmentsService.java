package employeeService.employeeBook.interfaces;

import employeeService.employeeBook.employeeBook.Employee;

import java.util.Map;

public interface DepartmentsService {
    void addNewEmployee(String surname, String name, String patronymic, String department, int salary);
    void find(String initials);
    void remove(String initials);

    Map<String, Employee> getEmployees();

}
