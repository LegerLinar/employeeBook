package employeeService.employeeBook.exceptions;

import employeeService.employeeBook.services.EmployeeServiceImpl;

public class EmployeeAlreadyExistException extends RuntimeException{
    public EmployeeAlreadyExistException(String msg){
        super(msg);
    }
}
