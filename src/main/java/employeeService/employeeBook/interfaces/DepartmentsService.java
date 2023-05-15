package employeeService.employeeBook.interfaces;

import employeeService.employeeBook.model.Employee;

import java.util.List;

public interface DepartmentsService {
    List<Employee> getEmployeesByDep(String department);
    Employee findEmployeesMinSalaryByDep(String department);
    void findEmployeesMaxSalaryOfDep(String department);
    int countSummarySalaryOfDep(String department);
    double countAverageSalaryOfDep(String department);
    void toIndexSalaryOfDep(String department, int percent);
    void printDepartment(String department);
    void printAllDepartmentPersonnel();

}
