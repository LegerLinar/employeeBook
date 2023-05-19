package employeeService.employeeBook.services;

import employeeService.employeeBook.exceptions.WrongNameException;
import employeeService.employeeBook.model.Employee;
import employeeService.employeeBook.model.EmployeeBook;
import employeeService.employeeBook.interfaces.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeBook employeeBook = new EmployeeBook();
    private Map<String, Employee> employeeMap = employeeBook.employeeBook();

    public EmployeeServiceImpl() {

    }

    @Override
    public Collection<Employee> printAllEmployeesData() {
        return employeeMap.values();
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
        return employeeMap.values().stream().
                filter(e -> e.getSalary() == employeeWithMinSalary).
                findFirst().get();


    }

    @Override
    public Employee findEmployeeMaxSalary() {
        int max = employeeMap.values().stream().
                map(e -> e.getSalary()).
                max(Comparator.naturalOrder()).get();

        return employeeMap.values().stream().
                filter(e -> e.getSalary() == max).
                findFirst().get();
    }

    @Override
    public double countAverageMonthSalary() {
        return employeeMap.values().stream().
                map(e -> e.getSalary()).
                mapToInt(Integer::intValue).
                average().getAsDouble();
    }

    @Override
    public Set<String> showEmployeesNames() {
        return employeeMap.keySet();
    }

    @Override
    public Set<Employee> searchWhoEarnLess(int salary) {

        return
                employeeMap.values().stream().
                        filter(e -> e.getSalary() < salary).
                        collect(Collectors.toSet());
    }

    @Override
    public void printWhoEarnMore(int salary) {
        employeeMap.values().stream().
                filter(e -> e.getSalary() > salary).
                collect(Collectors.toSet());
    }

    @Override
    public Employee searchEmployee(String surname,
                                   String name,
                                   String patronymic) {
        if (validateName(surname, name, patronymic)) {
            throw new WrongNameException();
        }
        return employeeMap.get(surname + " " + name + " " + patronymic);

    }

    @Override
    public Employee searchEmployee(int id) {

        return employeeMap.values().stream().
                filter(e -> e.getId() == id).
                findFirst().
                get();
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
    public Employee addNewEmployee(String surname,
                                   String name,
                                   String patronymic,
                                   String department,
                                   int salary) {

        if (validateName(surname, name, patronymic)) {
            throw new WrongNameException();
        }
        surname = lowerCase(surname);
        name = lowerCase(name);
        patronymic = lowerCase(patronymic);

        surname = capitalize(surname);
        name = capitalize(name);
        patronymic = capitalize(patronymic);


        return employeeMap.put(surname + " "
                        + name + " "
                        + patronymic,
                new Employee(surname,
                        name,
                        patronymic,
                        department,
                        salary));
//        System.out.println("Добавлен");
    }

    @Override
    public void dismissEmployee(String surname,
                                String name,
                                String patronymic) {
        if (validateName(surname, name, patronymic)) {
            throw new WrongNameException();
        }
        employeeMap.remove(surname
                + " "
                + name
                + " "
                + patronymic);

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


    //  –––––––––––––––––––––––----- Till that point all in Controller --------------------
    @Override
    public void changeEmployeesSalary(String surname,
                                      String name,
                                      String patronymic,
                                      int changeSalary) {
        if (validateName(surname, name, patronymic)) {
            throw new WrongNameException();
        }

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
        if (validateName(surname, name, patronymic)) {
            throw new WrongNameException();
        }
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

    public boolean validateName(String surname, String name, String patronymic){
        return  isAlpha(surname)
                && !isAlpha(name)
                && !isAlpha(patronymic);
        }

    }

//    --------------------------- CLASS END -------------------------
}