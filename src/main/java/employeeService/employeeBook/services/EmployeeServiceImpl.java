package employeeService.employeeBook.services;

import employeeService.employeeBook.employeeBook.Employee;
import employeeService.employeeBook.employeeBook.EmployeeBook;
import employeeService.employeeBook.interfaces.EmployeeService;

import java.util.Map;

public class EmployeeServiceImpl {
    private Map<String, Employee> employeeMap = (Map<String, Employee>) new EmployeeBook();

    public EmployeeServiceImpl() {

    }

    public void printAllEmployeesData() {
        System.out.println("Личный состав:");
        for (Employee employee : employeeMap.values()) {
            System.out.println(employee);
        }
    }

    public int countMonthSalaryExpenses() {
        int monthSalaryExpenses = 0;
        for (Employee employee :
                employeeMap.values()) {
            monthSalaryExpenses += employee.getSalary();
        }
        return monthSalaryExpenses;
    }

    public String findEmployeeMinSalary() {
        int minSalary = 0;
        String employeeWithMinSalary = "";
        for (Employee employee : employeeMap.values()) {
            if (employee.getSalary() < minSalary || minSalary == 0) {
                minSalary = employee.getSalary();
                employeeWithMinSalary = employee.toString();
            }
        }
        return employeeWithMinSalary;

    }

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

    public int countAverageMonthSalary() {
        int totalMonthSalary = 0;
        int employeesCounter = 0;
        for (Employee employee : employeeMap.values()) {
            totalMonthSalary += employee.getSalary();
            employeesCounter++;
        }
        return totalMonthSalary / employeesCounter;
    }

    public void showEmployeesNames() {
        StringBuilder employeesString = new StringBuilder();
        for (String employee : employeeMap.keySet()) {
            System.out.println(employee);
        }
    }

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

    public Employee searchEmployee(String surname, String name, String patronymic) {
        return employeeMap.get(surname + " " + name + " " + patronymic);

    }

    public Employee searchEmployee(int id) {
        for (Employee employee : employeeMap.values()) {
            if (employee.getId() == id) {
                return employee;
            }
        }
        return null;
    }

    public void toIndexSalary(int percent) {
        int increaseAmount;
        for (Employee employee : employeeMap.values()) {
            increaseAmount = employee.getSalary() * percent / 100;
            employee.setSalary(employee.getSalary() + increaseAmount);
        }
    }

    public void addNewEmployee(String surname, String name, String patronymic, String department, int salary) {
        employeeMap.put(surname + " " + name + " " + patronymic, new Employee(surname, name, patronymic, department, salary));
        System.out.println("Добавлен");
    }

    public void dismissEmployee(String surname, String name, String patronymic) {
        if (employeeMap.remove(surname + " " + name + " " + patronymic) == null) {
            System.out.println("Сотрудник не найден");
        } else {
            System.out.println("Сотрудник уволен");
        }
    }

    public void dismissEmployee(int id) {

        if (searchEmployee(id) != null) {
            employeeMap.remove(searchEmployee(id).getEmployeeInitials());
            System.out.println("Сотрудник уволен");
        } else {
            System.out.println("Сотрудник не найден");
        }
    }

    public void changeEmployeesSalary(String surname, String name, String patronymic, int changeSalary) {

        Employee employee = searchEmployee(surname, name, patronymic);
        if (employee != null) {
            employee.setSalary(employee.getSalary() + changeSalary);
            System.out.println("Зарплата сотрудника " + employee.getEmployeeInitials() + " (id: " + employee.getId() + ") изменена");
        } else {
            System.out.println("Сотрудник не найден");
        }
    }

    public void changeEmployeesSalary(int id, int changeSalary) {

        Employee employee = searchEmployee(id);
        if (employee != null) {
            employee.setSalary(employee.getSalary() + changeSalary);
            System.out.println("Зарплата сотрудника " + employee.getEmployeeInitials() + " (id: " + employee.getId() + ") изменена");
        } else {
            System.out.println("Сотрудник не найден");
        }
    }

    public void changeEmployeeDepartment(String surname, String name, String patronymic, String department) {
        Employee employee = searchEmployee(surname, name, patronymic);
        if (employee != null) {
            employee.setDepartment(department);
            System.out.println("Сотрудник " + employee.getEmployeeInitials() + " переведен в отдел " + employee.getDepartment());
        } else {
            System.out.println("Сотрудник не найден");
        }
    }

    //
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
/*

 */