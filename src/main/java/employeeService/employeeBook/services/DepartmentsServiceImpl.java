package employeeService.employeeBook.services;

import employeeService.employeeBook.interfaces.DepartmentsService;
import employeeService.employeeBook.model.Employee;
import employeeService.employeeBook.model.EmployeeBook;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentsServiceImpl implements DepartmentsService {
    private EmployeeBook employeeBook =  new EmployeeBook();
    Map<String, Employee> employeeMap = employeeBook.employeeBook();
    private EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

    public DepartmentsServiceImpl() {
    }

//


    public Map<String, Employee> getEmployees() {
        return employeeMap;
    }


    public List<Employee> getEmployeesByDep(String department) {
        int subEmployeesCounter = 0;
        List<Employee> subEmployeeListServiceImpl = new ArrayList<>(0);
        for (Employee employee : employeeMap.values()) {
            if (employee.getDepartment().contentEquals(department)) {
                subEmployeeListServiceImpl.add(employee);

            }
        }
        if (subEmployeeListServiceImpl.isEmpty()) {
            System.out.println("В отделе нет сотрудников");
        }
        return subEmployeeListServiceImpl;
    }


    public void findEmployeesMinSalaryByDep(String department) {
        int minSalary = 0;
        String employeeName = "";
        for (Employee employee : getEmployeesByDep(department)) {
            if (employee.getSalary() < minSalary || minSalary == 0) {
                minSalary = employee.getSalary();
                employeeName = employee.getEmployeeInitials();
            }
        }
        if (employeeName.equals("")) {
            System.out.println("В указанном отделе нет сотрудников");
        } else {
            System.out.println("Сотрудник " + employeeName + " получает наименьшую зарплату в отделе " + department + " - " + minSalary + "руб.");
        }
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

    public void countSummarySalaryOfDep(String department) {
        int summarySalary = 0;
        for (Employee employee : getEmployeesByDep(department)) {

            summarySalary += employee.getSalary();
        }
        System.out.println("Сумма затрат на заработную плату отделу " + department + " в месяц, составляет - " + summarySalary + "руб.");
    }


    public void countAverageSalaryOfDep(String department) {
        int summarySalary = 0;
        int employeesCounter = 0;
        for (Employee employee : getEmployeesByDep(department)) {
            summarySalary += employee.getSalary();
            employeesCounter++;
        }
        int averageSummarySalary = summarySalary / employeesCounter;
        System.out.println("Средняя заработная плата за месяц в отделе " + department + " составляет - " + averageSummarySalary + "руб.");
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
