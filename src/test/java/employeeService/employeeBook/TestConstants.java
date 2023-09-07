package employeeService.employeeBook;

import employeeService.employeeBook.model.Employee;

public class TestConstants {
    public static Employee IVAN = new Employee("Иванов", "Иван", "Иванович", "2", 15000);
    public static Employee TEST_IVAN_NO_DEP = new Employee("Иванов", "Иван", "Иванович", "", 15000);
    public static Employee TEST_NO_NAME = new Employee("", "", "", "2", 15000);
    public static Employee NON_ALPHA_NAME = new Employee("1", "2", "3", "1", 12000);
}
