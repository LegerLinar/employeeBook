package employeeService.employeeBook.interfaces;

import employeeService.employeeBook.model.Employee;

import java.util.Collection;
import java.util.Set;

public interface EmployeeService {
    public Employee searchEmployee(String surname,
                                   String name,
                                   String patronymic);
    void addNewEmployee(String surname,
                        String name,
                        String patronymic,
                        String department,
                        int salary);
    void dismissEmployee(String surname,
                         String name,
                         String patronymic);

    void dismissEmployee(int id);


    Employee searchEmployee(int id);

    int countMonthSalaryExpenses();
    Employee findEmployeeMinSalary();
    Employee findEmployeeMaxSalary();
    double countAverageMonthSalary();

    public void changeEmployeesSalary(String surname,
                                      String name,
                                      String patronymic,
                                      int changeSalary);

    void changeEmployeesSalary(int id, int changeSalary);

    void changeEmployeeDepartment(String surname,
                                  String name,
                                  String patronymic,
                                  String department);
    void changeEmployeeDepartment(int id, String department);
    void toIndexSalary(int percent);
    Collection<Employee> printAllEmployeesData();
    Set<String> showEmployeesNames();
    void printWhoEarnLess(int salary);
    void printWhoEarnMore(int salary);



}
