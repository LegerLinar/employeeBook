package employeeService.employeeBook.exceptions;

public class EmployeeNotExistException extends IllegalArgumentException{
    public EmployeeNotExistException(String msg){
        super(msg);
    }
}
