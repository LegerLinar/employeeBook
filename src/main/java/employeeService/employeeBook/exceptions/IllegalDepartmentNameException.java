package employeeService.employeeBook.exceptions;

public class IllegalDepartmentNameException extends IllegalArgumentException{
    public IllegalDepartmentNameException(String msg) {
        super(msg);
    }
}
