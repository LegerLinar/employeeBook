package employeeService.employeeBook.interfaces;

import employeeService.employeeBook.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentsService {
    List<Employee> getEmployeesByDep(String department);
    void findEmployeesMinSalaryByDep(String department);
    void findEmployeesMaxSalaryOfDep(String department);
    void countSummarySalaryOfDep(String department);
    void countAverageSalaryOfDep(String department);
    void toIndexSalaryOfDep(String department, int percent);
    void printDepartment(String department);
    void printAllDepartmentPersonnel();

}
