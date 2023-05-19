package employeeService.employeeBook.controller;


import employeeService.employeeBook.exceptions.WrongNameException;
import employeeService.employeeBook.interfaces.EmployeeService;
import employeeService.employeeBook.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/printAll")
    public String printAllEmployeesData() {
        return employeeService.printAllEmployeesData().toString();
    }

    @GetMapping(path = "/totalmonthsalary")
    public int countMonthSalaryExpenses() {
        return employeeService.countMonthSalaryExpenses();
    }

    @GetMapping(path = "/minsalary")
    public String findEmployeeWithMinSalary() {
        return "Сотрудник с самой низкой заработной платой: " +
                employeeService.findEmployeeMinSalary();
    }

    @GetMapping(path = "/averagesalary")
    public String countAverageMonthSalary() {

        return "Средняя заработная плата сотрудников: " +
                employeeService.countAverageMonthSalary();
    }

    @GetMapping(path = "/maxsalary")
    public String findEmployeeWMaxSalary() {
        return "Сотрудник с самой высокой заработной платой: " +
                employeeService.findEmployeeMaxSalary();
    }

    @GetMapping(path = "/employeenames")
    public String showEmployeesNames() {
        return "Все сотрудники - " +
                employeeService.showEmployeesNames();
    }

    @GetMapping(path = "/whoearnless", params = {"salary"})
    public String whoEarnLess(@RequestParam int salary) {
        Set<Employee> employeeSet = employeeService.searchWhoEarnLess(salary);
        if (employeeSet.isEmpty()) {
            return "Никто не зарабатывает меньше " + salary;
        }
        return "Меньше " + salary + " зарабатывает: " +
                employeeSet;
    }

    @GetMapping(path = "/whoearnmore", params = {"salary"})
    public String whoEarnMore(@RequestParam int salary) {
        Set<Employee> employeeSet = employeeService.searchWhoEarnLess(salary);
        if (employeeSet.isEmpty()) {
            return "Никто не зарабатывает больше " + salary;
        }
        return "Больше " + salary + " зарабатывает: " +
                employeeSet;
    }

    @GetMapping(path = "/search")
    public String searchEmployee(@RequestParam("surname") String surname,
                                 @RequestParam("name") String name,
                                 @RequestParam("patron") String patron) {
        return "" + employeeService.searchEmployee(surname, name, patron);
    }

    @GetMapping(path = "/search", params = {"id"})
    public String searchEmployeeById(@RequestParam("id") int id) {
        return "" + employeeService.searchEmployee(id);
    }

    @GetMapping(path = "/indexsalary")
    public String toIndexSalary(@RequestParam("index") int index) {
        employeeService.toIndexSalary(index);
        return "Зарплата всех сотрудников увеличена на " + index;

    }

    @GetMapping(path = "/add")
    public String addNewEmployee(@RequestParam("surname") String surname,
                                 @RequestParam("name") String name,
                                 @RequestParam("patronymic") String patronymic,
                                 @RequestParam("department") String department,
                                 @RequestParam("salary") int salary) {
        employeeService.addNewEmployee(
                surname,
                name,
                patronymic,
                department,
                salary);
        return "Новый сотрудник успешно добавлен" + StringUtils.isAlpha(surname);
    }

    @GetMapping(path = "/remove")
    public String removeEmployee(@RequestParam("surname") String surname,
                                 @RequestParam("name") String name,
                                 @RequestParam("patronymic") String patronymic) {
        employeeService.dismissEmployee(surname,
                name,
                patronymic);

        return "Сотрудник удален";
    }

    @GetMapping(path = "/remove", params = {"id"})
    public String removeEmployee(@RequestParam("id") int id){
        employeeService.dismissEmployee(id);

        return "Сотрудник удален";
    }


}
