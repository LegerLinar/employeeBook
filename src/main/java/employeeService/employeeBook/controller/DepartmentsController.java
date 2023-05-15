package employeeService.employeeBook.controller;

import employeeService.employeeBook.interfaces.DepartmentsService;
import employeeService.employeeBook.model.Employee;
import employeeService.employeeBook.services.DepartmentsServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentsController {
    private final DepartmentsService departmentsService;
    public DepartmentsController(DepartmentsService departmentsService){
        this.departmentsService = new DepartmentsServiceImpl();
    }

    @GetMapping(path = "/staffofdepartment")
    public String getEmployeesByDep(
            @RequestParam("department") String department){
        return "" + departmentsService.getEmployeesByDep(department);
    }

    @GetMapping(path = "/depminsalary")
    public String findEmployeesMinSalaryByDep(
            @RequestParam("department") String department){
        return "" + departmentsService.findEmployeesMinSalaryByDep(department);
    }

    @GetMapping(path = "/departmentsalary")
    public String countSummarySalaryOfDep(
            @RequestParam("department") String department){
        return "Суммарная заработная плата отдела " + department +
                " : " + departmentsService.countSummarySalaryOfDep(department)
                + "руб.";
    }
}
