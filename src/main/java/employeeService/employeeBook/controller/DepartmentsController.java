package employeeService.employeeBook.controller;

import employeeService.employeeBook.interfaces.DepartmentsService;
import employeeService.employeeBook.services.DepartmentsServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentsController {
    private final DepartmentsService departmentsService;
    public DepartmentsController(DepartmentsService departmentsService){
        this.departmentsService = new DepartmentsServiceImpl();
    }
}
