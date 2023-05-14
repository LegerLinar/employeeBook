package employeeService.employeeBook.interfaces;

import employeeService.employeeBook.model.Employee;

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
    String findEmployeeMinSalary();
    String findEmployeeMaxSalary();
    int countAverageMonthSalary();

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
    void printAllEmployeesData();
    void showEmployeesNames();
    void printWhoEarnLess(int salary);
    void printWhoEarnMore(int salary);



}
