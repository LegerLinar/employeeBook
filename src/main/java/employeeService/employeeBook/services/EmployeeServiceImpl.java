package employeeService.employeeBook.services;

import employeeService.employeeBook.model.Employee;
import employeeService.employeeBook.model.EmployeeBook;
import employeeService.employeeBook.interfaces.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeBook employeeBook = new EmployeeBook();
    private Map<String, Employee> employeeMap = employeeBook.employeeBook();

    public EmployeeServiceImpl() {

    }

    @Override
    public Set<String> printAllEmployeesData() {
        return employeeMap.keySet();
    }
    @Override
    public int countMonthSalaryExpenses() {
        int max = employeeMap.values().stream().
                map(e -> e.getSalary()).
                mapToInt(Integer::intValue).
                sum();
        return max;
    }

    @Override
    public Employee findEmployeeMinSalary() {

        final int employeeWithMinSalary =
        employeeMap.values().stream().
                map(e -> e.getSalary()).
                min(Comparator.naturalOrder()).
                get();
        return  employeeMap.values().stream().
                filter(e -> e.getSalary() == employeeWithMinSalary).
                findFirst().get();


    }
    @Override
    public String findEmployeeMaxSalary() {
        int maxSalary = 0;
        String employeeWithMaxSalary = "";
        for (Employee employee : employeeMap.values()) {
            if (employee.getSalary() > maxSalary) {
                maxSalary = employee.getSalary();
                employeeWithMaxSalary = employee.toString();
            }
        }
        return employeeWithMaxSalary;
    }
    @Override
    public double countAverageMonthSalary() {
       return employeeMap.values().stream().
              map(e -> e.getSalary()).
              mapToInt(Integer::intValue).
              average().getAsDouble();
    }

    @Override
    public void showEmployeesNames() {
        StringBuilder employeesString = new StringBuilder();
        for (String employee : employeeMap.keySet()) {
            System.out.println(employee);
        }
    }

    @Override
    public void printWhoEarnLess(int salary) {
        System.out.println("Сотрудники зарабатывающие менее " + salary + " руб.");
        int existCounter = 0;
        for (Employee employee : employeeMap.values()) {
            if (employee.getSalary() < salary) {
                existCounter = employee.getSalary();
                System.out.println(employee);
            }
        }
        if (existCounter == 0) {
            System.out.println("Сотрудников с зарплатой ниже " + salary + "руб. нет");
        }
    }

    @Override
    public void printWhoEarnMore(int salary) {
        System.out.println("Сотрудники зарабатывающие более " + salary + " руб.");
        int existCounter = 0;
        for (Employee employee : employeeMap.values()) {
            if (employee.getSalary() >= salary) {
                existCounter = employee.getSalary();
                System.out.println(employee);
            }
        }
        if (existCounter == 0) {
            System.out.println("Сотрудников с зарплатой выше или равной " + salary + "руб. нет");
        }
    }

    @Override
    public Employee searchEmployee(String surname,
                                   String name,
                                   String patronymic) {
        return employeeMap.get(surname + " " + name + " " + patronymic);

    }

    @Override
    public Employee searchEmployee(int id) {
        for (Employee employee : employeeMap.values()) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public void toIndexSalary(int percent) {
        int increaseAmount;
        for (Employee employee : employeeMap.values()) {
            increaseAmount = employee.getSalary() * percent / 100;
            employee.setSalary(employee.getSalary() + increaseAmount);
        }
    }

    @Override
    public void addNewEmployee(String surname, String name, String patronymic, String department, int salary) {
        employeeMap.put(surname + " " + name + " " + patronymic, new Employee(surname, name, patronymic, department, salary));
        System.out.println("Добавлен");
    }

    @Override
    public void dismissEmployee(String surname, String name, String patronymic) {
        if (employeeMap.remove(surname + " " + name + " " + patronymic) == null) {
            System.out.println("Сотрудник не найден");
        } else {
            System.out.println("Сотрудник уволен");
        }
    }

    @Override
    public void dismissEmployee(int id) {

        if (searchEmployee(id) != null) {
            employeeMap.remove(searchEmployee(id).getEmployeeInitials());
            System.out.println("Сотрудник уволен");
        } else {
            System.out.println("Сотрудник не найден");
        }
    }

    @Override
    public void changeEmployeesSalary(String surname,
                                      String name,
        String patronymic,
                                      int changeSalary) {

        Employee employee = searchEmployee(surname, name, patronymic);
        if (employee != null) {
            employee.setSalary(employee.getSalary() + changeSalary);
            System.out.println("Зарплата сотрудника " + employee.getEmployeeInitials() + " (id: " + employee.getId() + ") изменена");
        } else {
            System.out.println("Сотрудник не найден");
        }
    }

    @Override
    public void changeEmployeesSalary(int id, int changeSalary) {

        Employee employee = searchEmployee(id);
        if (employee != null) {
            employee.setSalary(employee.getSalary() + changeSalary);
            System.out.println("Зарплата сотрудника " + employee.getEmployeeInitials() + " (id: " + employee.getId() + ") изменена");
        } else {
            System.out.println("Сотрудник не найден");
        }
    }

    @Override
    public void changeEmployeeDepartment(String surname,
                                         String name,
                                         String patronymic,
                                         String department) {
        Employee employee = searchEmployee(surname, name, patronymic);
        if (employee != null) {
            employee.setDepartment(department);
            System.out.println("Сотрудник " + employee.getEmployeeInitials() + " переведен в отдел " + employee.getDepartment());
        } else {
            System.out.println("Сотрудник не найден");
        }
    }

    @Override
    public void changeEmployeeDepartment(int id, String department) {
        Employee employee = searchEmployee(id);
        if (employee != null) {
            employee.setDepartment(department);
            System.out.println("Сотрудник " + employee.getEmployeeInitials() + " переведен в отдел " + employee.getDepartment());
        } else {
            System.out.println("Сотрудник не найден");
        }
    }


//    --------------------------- CLASS END -------------------------
}