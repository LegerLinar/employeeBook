package employeeService.employeeBook.services;

import employeeService.employeeBook.interfaces.DepartmentsService;
import employeeService.employeeBook.model.Employee;
import employeeService.employeeBook.model.EmployeeBook;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;

@Service
public class DepartmentsServiceImpl implements DepartmentsService {
    private final EmployeeBook employeeBook = new EmployeeBook();
    Map<String, Employee> employeeMap = employeeBook.employeeBook();

    public DepartmentsServiceImpl() {
    }

//


    public Map<String, Employee> getEmployees() {
        return employeeMap;
    }


    public List<Employee> getEmployeesByDep(String department) {
        return employeeMap.values().stream().
                filter(e -> e.getDepartment().contentEquals(department)).
                collect(Collectors.toList());
    }


    public Employee findEmployeesMinSalaryByDep(String department) {

        return employeeMap.values().stream()
                .filter(e -> e.getDepartment().contentEquals(department))
                .min(comparingInt(Employee::getSalary))
                .orElseThrow(RuntimeException::new);
    }

    public void findEmployeesMaxSalaryOfDep(String department) {
        int maxSalary = 0;
        String employeeName = "";
        for (Employee employee : getEmployeesByDep(department)) {
            if (employee.getSalary() > maxSalary || maxSalary == 0) {
                maxSalary = employee.getSalary();
                employeeName = employee.getEmployeeInitials();
            }
        }
        if (employeeName.equals("")) {
            System.out.println("В указанном отделе нет сотрудников");
        } else {
            System.out.println("Сотрудник " + employeeName + " получает наибольшую зарплату в отделе " + department + " - " + maxSalary + "руб.");
        }


    }

    public int countSummarySalaryOfDep(String department) {
        return employeeMap.values().stream().
                filter(e -> e.getDepartment().contentEquals(department))
                .map(e -> e.getSalary())
                .mapToInt(Integer::intValue)
                .sum();
    }


    public double countAverageSalaryOfDep(String department) {
       return employeeMap.values().stream()
                .filter(e -> e.getDepartment().contentEquals(department))
                .map(e -> e.getSalary())
                .mapToDouble(Integer::doubleValue)
                .average().getAsDouble();

    }

    public void toIndexSalaryOfDep(String department, int percent) {
        int increaseAmount;
        for (Employee employee : getEmployeesByDep(department)) {
            if (employee != null) {
                increaseAmount = employee.getSalary() * percent / 100;
                employee.setSalary(employee.getSalary() + increaseAmount);

            }
        }
    }

    public void printDepartment(String department) {
        System.out.println("Сотрудники отдела " + department);
        for (Employee employee : getEmployeesByDep(department)) {
            if (employee == null) {
                System.out.println("В отделе нет сотрудников");
            }

            System.out.println(employee.getEmployeeInitials() + ", заработная плата - " + employee.getSalary() + "руб. id - " + employee.getId());
        }
    }


    public void printAllDepartmentPersonnel() {

        for (String actualDepartment : Employee.getDepartments()) {
            System.out.println("Отдел " + actualDepartment + ":");
            for (Employee employee : getEmployeesByDep(actualDepartment)) {
                if (employee == null) {
                    System.out.println("В отделе нет сотрудников");
                }

                System.out.println(employee.getEmployeeInitials() + ", id: " + employee.getId());

            }
        }
    }
//    Class End ––––––––––––––––––––––––
}
