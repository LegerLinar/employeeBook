package employeeService.employeeBook.controller;


import employeeService.employeeBook.interfaces.EmployeeService;
import employeeService.employeeBook.model.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/printAll")
    public String printAllEmployeesData(){
        return employeeService.printAllEmployeesData().toString();
    }
    @GetMapping(path = "/totalmonthsalary")
    public int countMonthSalaryExpenses(){
        return employeeService.countMonthSalaryExpenses();
    }
    @GetMapping(path = "/minsalary")
    public String findEmployeeWithMinSalary(){
        return "Сотрудник с самой низкой заработной платой: " +
                employeeService.findEmployeeMinSalary();
    }
    @GetMapping(path = "/averagesalary")
    public String countAverageMonthSalary(){

        return "Средняя заработная плата сотрудников: " +
                employeeService.countAverageMonthSalary();
    }

    @GetMapping(path = "/maxsalary")
    public String findEmployeeWMaxSalary(){
        return "Сотрудник с самой высокой заработной платой: " +
                employeeService.findEmployeeMaxSalary();
    }
    @GetMapping(path = "/employeenames")
    public String showEmployeesNames(){
        return "Все сотрудники - " +
                employeeService.showEmployeesNames();
    }

    @GetMapping(path = "/whoearnless", params = {"salary"})
    public String whoEarnLess(@RequestParam int salary){
        Set<Employee> employeeSet = employeeService.searchWhoEarnLess(salary);
        if (employeeSet.isEmpty()){
            return "Никто не зарабатывает меньше " + salary;
        }
        return "Меньше " + salary + " зарабатывает: " +
                employeeSet;
    }
    @GetMapping(path = "/whoearnmore", params = {"salary"})
    public String whoEarnMore(@RequestParam int salary){
        Set<Employee> employeeSet = employeeService.searchWhoEarnLess(salary);
        if (employeeSet.isEmpty()){
            return "Никто не зарабатывает больше " + salary;
        }
        return "Больше " + salary + " зарабатывает: " +
                employeeSet;
    }


}
