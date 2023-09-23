package employeeService.employeeBook;

import employeeService.employeeBook.exceptions.*;
import employeeService.employeeBook.interfaces.EmployeeService;
import employeeService.employeeBook.model.Employee;
import employeeService.employeeBook.services.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static employeeService.employeeBook.TestConstants.*;

public class EmployeeServiceImplTests {

    private final EmployeeService out = new EmployeeServiceImpl();


    @Test
    public void shouldAddNewEmployee() {
        String expectedMsg = "Сотрудник добавлен";
        String actualMsg = out.addNewEmployee(
                NORMAL_SURNAME,
                NORMAL_NAME,
                NORMAL_PATRONYMIC,
                NORMAL_DEPARTMENT,
                BASIC_SALARY);
        Employee actualEmployee = out.searchEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC);
        Employee expected = new Employee(
                NORMAL_SURNAME,
                NORMAL_NAME,
                NORMAL_PATRONYMIC,
                NORMAL_DEPARTMENT,
                BASIC_SALARY
        );
        HashMap<String, Employee> expectedMap = new HashMap<>(Map.of(expected.getEmployeeInitials(), expected));


        assertEquals(expectedMsg, actualMsg);
        assertEquals(expected, actualEmployee);
        assertIterableEquals(expectedMap.values(), out.printAllEmployeesData());

    }


    @Test
    public void shouldAddCorrectEmployeeName() {
        String expectedMsg = "Сотрудник добавлен";
        String actualMsg = out.addNewEmployee(
                NON_CAPITALIZE_SURNAME,
                NON_CAPITALIZE_NAME,
                NON_CAPITALIZE_PATRONYMIC,
                NORMAL_DEPARTMENT,
                BASIC_SALARY);
        Employee actualEmployee = out.searchEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC);
        Employee expectedEmployee = new Employee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY);

        assertEquals(expectedMsg, actualMsg);
        assertEquals(expectedEmployee, actualEmployee);

    }

    /* ------ CONSTANTS ---------------
    public static final String EMPTY_NAME = "";
  public static final String EMPTY_SURNAME = "";
  public static final String EMPTY_PATRONYMIC = "";
  public static final String NON_ALPHA_NAME = "1";
  public static final String NON_ALPHA_SURNAME = "0,6";
  public static final String NON_ALPHA_PATRONYMIC = "0,2";
   public static final String NULL_NAME = null;
  public static final String NULL_SURNAME = null;
  public static final String NULL_PATRONYMIC = null;
  public static final String NON_CAPITALIZE_NAME = "иван";
  public static final String NON_CAPITALIZE_SURNAME = "иванов";
  public static final String NON_CAPITALIZE_PATRONYMIC = "иванович";
  public static final String NORMAL_NAME = "Иван";
  public static final String NORMAL_SURNAME = "Иванов";
  public static final String NORMAL_PATRONYMIC= "Иванович";
  public static final String NULL_DEPARTMENT = null;
  public static final String EMPTY_DEPARTMENT = "";
  public static final String NOT_EXISTING_DEPARTMENT = "0,2";
  BASIC_SALARY
   */
    @ParameterizedTest
    @MethodSource("nameExceptionsParams")
    public void shouldThrowWrongNameException(String surname, String name, String patronymic, String dep, int salary) {
        assertThrows(WrongNameException.class,
                () -> out.addNewEmployee(surname, name, patronymic, dep, salary));

    }

    public static Stream<Arguments> nameExceptionsParams() {
        return Stream.of(
                Arguments.of(EMPTY_SURNAME, EMPTY_NAME, EMPTY_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY),
                Arguments.of(NON_ALPHA_SURNAME, NON_ALPHA_NAME, NON_ALPHA_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY),
                Arguments.of(NULL_SURNAME, NULL_NAME, NULL_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY)
        );
    }

    @Test
    public void shouldThrowAlreadyExistException() {
        out.addNewEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY);
        assertThrows(EmployeeAlreadyExistException.class,
                () -> out.addNewEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY));
    }

    @ParameterizedTest
    @MethodSource("departmentStringExceptionParams")
    public void shouldThrowDepartmentStringExceptions(String department) {
        assertThrows(IllegalDepartmentNameException.class,
                () -> out.addNewEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, department, BASIC_SALARY));
    }

    public static Stream<Arguments> departmentStringExceptionParams() {
        return Stream.of(
                Arguments.of(NULL_DEPARTMENT),
                Arguments.of(EMPTY_DEPARTMENT),
                Arguments.of(NOT_EXISTING_DEPARTMENT)
        );
    }

    @ParameterizedTest
    @MethodSource("normalDataParams")
    public void shouldReturnEmployeesList(String surname, String name, String patronymic, String dep, int salary) {
        out.addNewEmployee(surname, name, patronymic, dep, salary);

        HashMap<String, Employee> expectedMap = new HashMap<>();
        Employee testEmployee = new Employee(surname, name, patronymic, dep, salary);
        expectedMap.put(testEmployee.getEmployeeInitials(), testEmployee);

        Collection<Employee> actualCollection = out.printAllEmployeesData();
        Collection<Employee> expectedCollection = expectedMap.values();

        assertNotNull(actualCollection);
        assertIterableEquals(expectedCollection, actualCollection);
    }

    public static Stream<Arguments> normalDataParams() {
        return Stream.of(
                Arguments.of(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY)
        );
    }

    @ParameterizedTest
    @MethodSource("normalDataParams")
    public void shouldSearchEmployee(String surname, String name, String patronymic, String dep, int salary) {
        out.addNewEmployee(surname, name, patronymic, dep, salary);

        HashMap<String, Employee> expectedMap = new HashMap<>();
        Employee testEmployee = new Employee(surname, name, patronymic, dep, salary);
        expectedMap.put(testEmployee.getEmployeeInitials(), testEmployee);

        Employee actualEmployee = out.searchEmployee(surname, name, patronymic);
        Employee expectedEmployee = expectedMap.get(testEmployee.getEmployeeInitials());

        assertNotNull(actualEmployee);
        assertEquals(expectedEmployee, actualEmployee);
    }

    @ParameterizedTest
    @MethodSource("nameExceptionsParams")
    public void shouldThrowWrongNameForSearch(String surname, String name, String patronymic) {

        assertThrows(WrongNameException.class,
                () -> out.searchEmployee(surname, name, patronymic));

    }

    @Test
    public void shouldThrowVoidMapException() {
        assertThrows(EmployeeNotExistException.class,
                () -> out.searchEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC));
    }

    @ParameterizedTest
    @MethodSource("normalDataParams")
    public void shouldSearchEmployeeById(String surname, String name, String patronymic, String dep, int salary) {
        out.addNewEmployee(surname, name, patronymic, dep, salary);
        int actual = out.searchEmployee(surname, name, patronymic).getId();
        Employee expectedEmployee = out.searchEmployee(surname, name, patronymic);

        assertEquals(1, out.printAllEmployeesData().size());
        assertEquals(expectedEmployee, out.searchEmployee(actual));
    }


    @ParameterizedTest
    @MethodSource("normalDataParams")
    public void shouldDismissEmployee(String surname, String name, String patronymic, String dep, int salary) {
        out.addNewEmployee(surname, name, patronymic, dep, salary);
        Employee testEmployee = out.searchEmployee(surname, name, patronymic);
        out.dismissEmployee(testEmployee.getSurname(), testEmployee.getName(), testEmployee.getPatronymic());

        assertThrows(VoidDepartmentException.class,
                () -> out.printAllEmployeesData());
    }


    @ParameterizedTest
    @MethodSource("normalDataParams")
    public void shouldDismissEmployeeById(String surname, String name, String patronymic, String dep, int salary) {
        out.addNewEmployee(surname, name, patronymic, dep, salary);
        int actualEmployeeId = out.searchEmployee(surname, name, patronymic).getId();

        out.dismissEmployee(actualEmployeeId);

        assertThrows(VoidDepartmentException.class,
                () -> out.printAllEmployeesData());
    }


    @Test
    public void shouldCountMonthSalaryExpenses() {
        out.addNewEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY);
        out.addNewEmployee(NORMAL_SECOND_SURNAME, NORMAL_SECOND_NAME, NORMAL_SECOND_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY);
        int expected = BASIC_SALARY + BASIC_SALARY;

        assertEquals(expected, out.countMonthSalaryExpenses());

    }


    @Test
    public void shouldFindEmployeeMinSalary() {
        out.addNewEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, NORMAL_DEPARTMENT, 2 * BASIC_SALARY);
        out.addNewEmployee(NORMAL_SECOND_SURNAME, NORMAL_SECOND_NAME, NORMAL_SECOND_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY);
        out.addNewEmployee(NORMAL_THIRD_SURNAME, NORMAL_THIRD_NAME, NORMAL_THIRD_PATRONYMIC, NORMAL_DEPARTMENT, 3 * BASIC_SALARY);
        Employee expected = out.searchEmployee(NORMAL_SECOND_SURNAME, NORMAL_SECOND_NAME, NORMAL_SECOND_PATRONYMIC);

        assertEquals(expected, out.findEmployeeMinSalary());
    }


    @Test
    public void findEmployeeMaxSalary() {
        out.addNewEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, NORMAL_DEPARTMENT, 2 * BASIC_SALARY);
        out.addNewEmployee(NORMAL_SECOND_SURNAME, NORMAL_SECOND_NAME, NORMAL_SECOND_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY);
        out.addNewEmployee(NORMAL_THIRD_SURNAME, NORMAL_THIRD_NAME, NORMAL_THIRD_PATRONYMIC, NORMAL_DEPARTMENT, 3 * BASIC_SALARY);
        Employee expected = out.searchEmployee(3);

        assertEquals(expected, out.findEmployeeMaxSalary());
    }


    @Test
    public void shouldCountAverageMonthSalary() {
        out.addNewEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, NORMAL_DEPARTMENT, 2 * BASIC_SALARY);
        out.addNewEmployee(NORMAL_SECOND_SURNAME, NORMAL_SECOND_NAME, NORMAL_SECOND_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY);
        out.addNewEmployee(NORMAL_THIRD_SURNAME, NORMAL_THIRD_NAME, NORMAL_THIRD_PATRONYMIC, NORMAL_DEPARTMENT, 3 * BASIC_SALARY);

        double expected = (out.searchEmployee(1).getSalary() + out.searchEmployee(2).getSalary() + out.searchEmployee(3).getSalary()) / 3;

        assertEquals(expected, out.countAverageMonthSalary());
    }


    @Test
    public void shouldChangeEmployeesSalary() {
        out.addNewEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY);
        double expected = BASIC_SALARY + BASIC_SALARY;

        out.changeEmployeesSalary(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, BASIC_SALARY);

        assertEquals(expected, out.searchEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC).getSalary());
    }

    @Test
    public void shouldChangeEmployeesSalaryById() {
        out.addNewEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY);
        double expected = BASIC_SALARY + BASIC_SALARY;

        out.changeEmployeesSalary(1, BASIC_SALARY);

        assertEquals(expected, out.searchEmployee(1).getSalary());
    }


    @Test
    public void shouldChangeEmployeeDepartment() {
        out.addNewEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, "2", BASIC_SALARY);
        String expected = "3";

        out.changeEmployeeDepartment(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, "3");

        assertEquals(expected, out.searchEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC).getDepartment());
        assertThrows(IllegalDepartmentNameException.class,
                () -> out.changeEmployeeDepartment(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, "Wrongname"));
    }


    @Test
    public void shouldChangeEmployeeDepartmentById() {
        out.addNewEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, "2", BASIC_SALARY);
        String expected = "3";

        out.changeEmployeeDepartment(1, "3");

        assertEquals(expected, out.searchEmployee(1).getDepartment());
        assertThrows(IllegalDepartmentNameException.class,
                () -> out.changeEmployeeDepartment(1, "Wrongname"));

    }


    @Test
    public void toIndexSalary() {
        out.addNewEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, "2", BASIC_SALARY);
        double expected = BASIC_SALARY + BASIC_SALARY * 50 / 100;
        out.toIndexSalary(50);
        assertEquals(expected, out.searchEmployee(1).getSalary());
    }


    @Test
    public void printAllEmployeesData() {
        out.addNewEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, "2", BASIC_SALARY);
        out.addNewEmployee(NORMAL_SURNAME + "f", NORMAL_NAME + "f", NORMAL_PATRONYMIC + "f", "2", BASIC_SALARY);
        Collection<Employee> expected = new ArrayList<>(List.of(
                new Employee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, "2", BASIC_SALARY),
                new Employee(NORMAL_SURNAME + "f", NORMAL_NAME + "f", NORMAL_PATRONYMIC + "f", "2", BASIC_SALARY)
        ));

        assertEquals(2, out.printAllEmployeesData().size());
        assertIterableEquals(expected, out.printAllEmployeesData());
    }


    @Test
    public void showEmployeesNames() {
        out.addNewEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, "2", BASIC_SALARY);
        out.addNewEmployee(NORMAL_SURNAME + "f", NORMAL_NAME + "f", NORMAL_PATRONYMIC + "f", "2", BASIC_SALARY);
        Set<String> expected = new HashSet<>(Set.of(
                NORMAL_SURNAME + " " + NORMAL_NAME + " " + NORMAL_PATRONYMIC,
                NORMAL_SURNAME + "f" + " " + NORMAL_NAME + "f" + " " + NORMAL_PATRONYMIC + "f"
        ));

        assertEquals(2, out.showEmployeesNames().size());
        assertIterableEquals(expected, out.showEmployeesNames());

    }


    @Test
    public void shouldSearchWhoEarnLess() {
        out.addNewEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, "2", BASIC_SALARY);
        out.addNewEmployee(NORMAL_SECOND_SURNAME, NORMAL_SECOND_NAME, NORMAL_SECOND_PATRONYMIC, "2", BASIC_SALARY * 3);
        out.addNewEmployee(NORMAL_THIRD_SURNAME, NORMAL_THIRD_NAME, NORMAL_THIRD_PATRONYMIC, "2", BASIC_SALARY * 2);

        Set<Employee> expected = new HashSet<>(Set.of(
                new Employee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, "2", BASIC_SALARY)
        ));
        assertEquals(1, out.searchWhoEarnLess(BASIC_SALARY * 2).size());
        assertIterableEquals(expected, out.searchWhoEarnLess(BASIC_SALARY * 2));
    }


    @Test
    public void shouldPrintWhoEarnMore() {
        out.addNewEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, "2", BASIC_SALARY);
        out.addNewEmployee(NORMAL_SECOND_SURNAME, NORMAL_SECOND_NAME, NORMAL_SECOND_PATRONYMIC, "2", BASIC_SALARY * 3);
        out.addNewEmployee(NORMAL_THIRD_SURNAME, NORMAL_THIRD_NAME, NORMAL_THIRD_PATRONYMIC, "2", BASIC_SALARY * 2);

        Set<Employee> expected = new HashSet<>(Set.of(
                new Employee(NORMAL_SECOND_SURNAME, NORMAL_SECOND_NAME, NORMAL_SECOND_PATRONYMIC, "2", BASIC_SALARY * 3)
        ));
        assertEquals(1, out.searchWhoEarnMore(BASIC_SALARY * 2).size());
        assertIterableEquals(expected, out.searchWhoEarnMore(BASIC_SALARY * 2));
    }

}
