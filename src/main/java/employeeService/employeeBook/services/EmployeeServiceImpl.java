package employeeService.employeeBook.services;

import employeeService.employeeBook.exceptions.EmployeeAlreadyExistException;
import employeeService.employeeBook.exceptions.IllegalDepartmentNameException;
import employeeService.employeeBook.exceptions.WrongNameException;
import employeeService.employeeBook.model.Employee;
import employeeService.employeeBook.interfaces.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public static Map<String, Employee> employeeBook = new HashMap<>(Map.of(
            "Кисложопкин Аркадий Васильевич",
            new Employee("Кисложопкин", "Аркадий", "Васильевич", "1", 35000),
            "Селиванов Акакий Александрович",
            new Employee("Селиванов", "Акакий", "Александрович", "4", 32000),
            "Кулиджи Казимир Космосович",
            new Employee("Кулиджи", "Казимир", "Космосович", "3", 42000),
            "Франклин Бенджамин Батькович",
            new Employee("Франклин", "Бенджамин", "Батькович", "5", 200_000),
            "Джугашвили Иосиф Виссарионович",
            new Employee("Джугашвили", "Иосиф", "Виссарионович", "5", 1_000),
            "Хирохито Сёма Ёсихитович",
            new Employee("Хирохито", "Сёма", "Ёсихитович", "4", 100_000)
    ));

    public EmployeeServiceImpl() {
    }


    @Override
    public Collection<Employee> printAllEmployeesData() {
        return employeeBook.values();
    }

    @Override
    public int countMonthSalaryExpenses() {
        return employeeBook.values().stream().
                map(e -> e.getSalary()).
                mapToInt(Integer::intValue).
                sum();

    }

    @Override
    public Employee findEmployeeMinSalary() {

        final int employeeWithMinSalary =
                employeeBook.values().stream().
                        map(e -> e.getSalary()).
                        min(Comparator.naturalOrder()).
                        get();
        return employeeBook.values().stream().
                filter(e -> e.getSalary() == employeeWithMinSalary).
                findFirst().get();


    }

    @Override
    public Employee findEmployeeMaxSalary() {
        int max = employeeBook.values().stream().
                map(e -> e.getSalary()).
                max(Comparator.naturalOrder()).get();

        return employeeBook.values().stream().
                filter(e -> e.getSalary() == max).
                findFirst().get();
    }

    @Override
    public double countAverageMonthSalary() {
        return employeeBook.values().stream().
                map(e -> e.getSalary()).
                mapToInt(Integer::intValue).
                average().getAsDouble();
    }

    @Override
    public Set<String> showEmployeesNames() {
        return employeeBook.keySet();
    }

    @Override
    public Set<Employee> searchWhoEarnLess(int salary) {

        return
                employeeBook.values().stream().
                        filter(e -> e.getSalary() < salary).
                        collect(Collectors.toSet());
    }

    @Override
    public void printWhoEarnMore(int salary) {
        employeeBook.values().stream().
                filter(e -> e.getSalary() > salary).
                collect(Collectors.toSet());
    }

    @Override
    public Employee searchEmployee(String surname,
                                   String name,
                                   String patronymic) {
        validateEmployee(surname, name, patronymic);
        return employeeBook.get(surname + " " + name + " " + patronymic);

    }

    @Override
    public Employee searchEmployee(int id) {

        return employeeBook.values().stream().
                filter(e -> e.getId() == id).
                findFirst().
                get();
    }

    @Override
    public void toIndexSalary(int percent) {

        int increaseAmount;
        for (Employee employee : employeeBook.values()) {
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

        validateEmployee(surname, name, patronymic);
        validateDepartment(department);

        surname = capitalize(lowerCase(surname));
        name = capitalize(lowerCase(name));
        patronymic = capitalize(lowerCase(patronymic));

        return employeeBook.put(surname + " "
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
        validateEmployee(surname, name, patronymic);

        employeeBook.remove(surname
                + " "
                + name
                + " "
                + patronymic);

    }

    @Override
    public void dismissEmployee(int id) {


        if (searchEmployee(id) != null) {
            employeeBook.remove(searchEmployee(id).getEmployeeInitials());
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
        validateEmployee(surname, name, patronymic);

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
        validateEmployee(surname, name, patronymic);

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

    private void validateEmployee(String surname, String name, String patronymic) {
        if (!isAlpha(surname)
                && !isAlpha(name)
                && !isAlpha(patronymic)) {
            throw new WrongNameException("Имя должно состоять только из строчных символов");
        }
        if (employeeBook.keySet().contains(surname + " "
                + name + " "
                + patronymic)) {
            throw new EmployeeAlreadyExistException("Сотрудник с таким именем уже в штате");
        }


    }

    private void validateDepartment(String department) {
        if (!Employee.getDepartments().contains(department)) {
            throw new IllegalDepartmentNameException("Такого отдела не существует");
        }
    }



//    --------------------------- CLASS END -------------------------
}