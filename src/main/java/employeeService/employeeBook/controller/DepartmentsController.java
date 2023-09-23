package employeeService.employeeBook.controller;

import employeeService.employeeBook.interfaces.DepartmentsService;
import employeeService.employeeBook.model.Employee;
import employeeService.employeeBook.services.DepartmentsServiceImpl;
import employeeService.employeeBook.services.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentsController {
    private final DepartmentsService departmentsService;

    public DepartmentsController(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }

    @GetMapping(path = "/{id}/employees/")
    public String getEmployeesByDep(
            @PathVariable String id) {
        return "" + departmentsService.getEmployeesByDep(id);
    }

    @GetMapping(path = "/{id}/salary/sum")
    public String countSummarySalaryOfDep(
            @PathVariable String id) {
        return "Суммарная заработная плата отдела " + id +
                " : " + departmentsService.countSummarySalaryOfDep(id)
                + "руб.";

    }
    @GetMapping(path = "/{id}/salary/average")
    public String countAverageSalary(
            @PathVariable("id") String id) {
        return "Средняя заработная плата в отделе " + id +
                " : " + departmentsService.countAverageSalaryOfDep(id)
                + "руб.";
    }

    @GetMapping(path = "/{id}/salary/min")
    public String findEmployeesMinSalaryByDep(
            @PathVariable("id") String id) {
        return "" + departmentsService.findEmployeesMinSalaryByDep(id);
    }
    @GetMapping(path = "/{id}/salary/max")
    public String findEmployeesMaxSalaryByDep(
            @PathVariable("id") String id) {
        return "" + departmentsService.findEmployeesMaxSalaryOfDep(id);
    }

    @GetMapping(path = "/print")
    public String printDepartment(
            @RequestParam("department") String department) {
        return "Сотрудники отдела " + department
                + " : " + departmentsService.printDepartment(department);
    }

    @GetMapping(path = "/employees")
    public String getAll() {
        return "Сотрудники" + " : "
                + departmentsService.printAllDepartmentPersonnel();
    }

}
