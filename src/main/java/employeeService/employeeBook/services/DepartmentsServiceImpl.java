package employeeService.employeeBook.services;

import employeeService.employeeBook.exceptions.VoidDepartmentException;
import employeeService.employeeBook.interfaces.DepartmentsService;
import employeeService.employeeBook.interfaces.EmployeeService;
import employeeService.employeeBook.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static employeeService.employeeBook.services.EmployeeServiceImpl.employeeBook;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

@Service
public class DepartmentsServiceImpl implements DepartmentsService {
    

    private final EmployeeService employeeService;
    public DepartmentsServiceImpl(EmployeeService employeeServiceImpl) {
        this.employeeService = new EmployeeServiceImpl();
    }

//


    public Map<String, Employee> getEmployees() {
        return employeeBook;
    }


    public List<Employee> getEmployeesByDep(String department) {
        return employeeBook.values().stream().
                filter(e -> e.getDepartment().contentEquals(department)).
                collect(Collectors.toList());
    }


    public Employee findEmployeesMinSalaryByDep(String department) {

        return employeeBook.values().stream()
                .filter(e -> e.getDepartment().contentEquals(department))
                .min(comparingInt(Employee::getSalary))
                .orElseThrow();
    }

    public Employee findEmployeesMaxSalaryOfDep(String department) {
        int maxSalary = 0;
        Employee curEmployee = null;
        for (Employee employee : getEmployeesByDep(department)) {
            if (employee.getSalary() > maxSalary || maxSalary == 0) {
                maxSalary = employee.getSalary();
                curEmployee = employee;
            }
        }
        if (curEmployee == null) {
            throw new VoidDepartmentException("В указанном отделе нет сотрудников");
        }
        return curEmployee;

    }

    public int countSummarySalaryOfDep(String department) {
        return employeeBook.values().stream().
                filter(e -> e.getDepartment().contentEquals(department))
                .map(e -> e.getSalary())
                .mapToInt(Integer::intValue)
                .sum();
    }


    public double countAverageSalaryOfDep(String department) {
        return employeeBook.values().stream()
                .filter(e -> e.getDepartment().contentEquals(department))
                .map(e -> e.getSalary())
                .mapToDouble(Integer::doubleValue)
                .average().getAsDouble();

    }

    //вроде не требуется
    public void toIndexSalaryOfDep(String department, int percent) {
        int increaseAmount;
        for (Employee employee : getEmployeesByDep(department)) {
            if (employee != null) {
                increaseAmount = employee.getSalary() * percent / 100;
                employee.setSalary(employee.getSalary() + increaseAmount);

            }
        }
    }

    public List<Employee> printDepartment(String department) {


        return employeeBook.values().stream()
                .filter(e -> e.getDepartment().contentEquals(department))
                .collect(Collectors.toList());
    }


    public Map<String, List<Employee>> printAllDepartmentPersonnel() {
        return employeeBook.values().stream()
                .sorted(comparing(Employee::getSurname))
                .collect(Collectors.groupingBy(Employee::getDepartment));

    }
//    Class End ––––––––––––––––––––––––
}
