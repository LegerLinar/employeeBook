package employeeService.employeeBook.services;

import employeeService.employeeBook.exceptions.IllegalDepartmentNameException;
import employeeService.employeeBook.exceptions.VoidDepartmentException;
import employeeService.employeeBook.interfaces.DepartmentsService;
import employeeService.employeeBook.interfaces.EmployeeService;
import employeeService.employeeBook.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;

@Service
public class DepartmentsServiceImpl implements DepartmentsService {


    private final EmployeeService employeeService;

    public DepartmentsServiceImpl(EmployeeService employeeServiceImpl) {
        this.employeeService = employeeServiceImpl;
    }

//


    public Map<String, Employee> getEmployees() {
        return employeeService.getEmployeeBook();
    }


    public List<Employee> getEmployeesByDep(String department) {
       verifyDepartment(department);
        List<Employee> cur = employeeService.getEmployeeBook().values().stream().
                filter(e -> e.getDepartment().contentEquals(department)).
                collect(Collectors.toList());

        if(cur.isEmpty()){
            throw new VoidDepartmentException("В отделе нет сотрудников");
        }
        return cur;
    }


    public Employee findEmployeesMinSalaryByDep(String department) {
        Employee cur = getEmployeesByDep(department).stream()
                .min(comparingInt(Employee::getSalary))
                .orElseThrow();
        if(cur == null){
            throw new VoidDepartmentException("В указанном отделе нет сотрудников");
        }
        return cur;
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
        return curEmployee;

    }

    public double countSummarySalaryOfDep(String department) {
        if(getEmployeesByDep(department).isEmpty()){
            throw new VoidDepartmentException("В указанном отделе нет сотрудников");
        }
        double result = getEmployeesByDep(department).stream()
                .map(e -> e.getSalary())
                .mapToDouble(Integer::doubleValue)
                .sum();
        return result;
    }


    public double countAverageSalaryOfDep(String department) {
        return getEmployeesByDep(department).stream()
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
        return getEmployeesByDep(department).stream()
                .collect(Collectors.toList());
    }


    public Map<String, List<Employee>> printAllDepartmentPersonnel() {
        return employeeService.getEmployeeBook().values().stream()
                .sorted(comparing(Employee::getSurname))
                .collect(Collectors.groupingBy(Employee::getDepartment));

    }

    private void verifyDepartment(String department) {
        if(!Employee.getDepartments().contains(department)){
            throw new IllegalDepartmentNameException("Указан несуществующий отдел");
        }
    }
//    Class End ––––––––––––––––––––––––
}
