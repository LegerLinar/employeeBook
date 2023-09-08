package employeeService.employeeBook;

import employeeService.employeeBook.interfaces.EmployeeService;
import employeeService.employeeBook.model.Employee;
import employeeService.employeeBook.services.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.Arguments;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static employeeService.employeeBook.TestConstants.*;


/*constants
TEST_IVAN_NO_DEP
TEST_NO_NAME
NON_ALPHA_NAME
 */
public class EmployeeServiceImplTests {

    private final EmployeeService out = new EmployeeServiceImpl();


    @Test
    public void shouldAddNewEmployee() {
        String result = out.addNewEmployee(
                NORMAL_SURNAME,
                NORMAL_NAME,
                NORMAL_PATRONYMIC,
                NORMAL_DEPARTMENT,
                BASIC_SALARY);
        Employee expected = new Employee(
                NORMAL_SURNAME,
                NORMAL_NAME,
                NORMAL_PATRONYMIC,
                NORMAL_DEPARTMENT,
                BASIC_SALARY
        );
        assertEquals("Сотрудник добавлен", result);
        assertEquals(expected, out.searchEmployee(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC));

    }
//    public void shouldSearchEmployee() {
//        out.addNewEmployee();
//
//        assertEquals(IVAN, out.searchEmployee(IVAN.getSurname(), IVAN.getName(), IVAN.getPatronymic()));
//    }

    //    public Employee searchEmployee(int id) {
//        return null;
//    }
//
//
//
//
//    public void dismissEmployee(String surname, String name, String patronymic) {
//
//    }
//
//
//    public void dismissEmployee(int id) {
//
//    }
//
//
//    public int countMonthSalaryExpenses() {
//        return 0;
//    }
//
//
//    public Employee findEmployeeMinSalary() {
//        return null;
//    }
//
//
//    public Employee findEmployeeMaxSalary() {
//        return null;
//    }
//
//
//    public double countAverageMonthSalary() {
//        return 0;
//    }
//
//
//    public void changeEmployeesSalary(String surname, String name, String patronymic, int changeSalary) {
//
//    }
//
//
//    public void changeEmployeesSalary(int id, int changeSalary) {
//
//    }
//
//
//    public void changeEmployeeDepartment(String surname, String name, String patronymic, String department) {
//
//    }
//
//
//    public void changeEmployeeDepartment(int id, String department) {
//
//    }
//
//
//    public void toIndexSalary(int percent) {
//
//    }
//
//
//    public Collection<Employee> printAllEmployeesData() {
//        return null;
//    }
//
//
//    public Set<String> showEmployeesNames() {
//        return null;
//    }
//
//
//    public Set<Employee> searchWhoEarnLess(int salary) {
//        return null;
//    }
//
//
//    public void printWhoEarnMore(int salary) {
//
//    }
    public static Stream<Arguments> providedParamsForTestsName() {
        return Stream.of(
                Arguments.of(EMPTY_SURNAME, EMPTY_NAME, EMPTY_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY),
                Arguments.of(NON_ALPHA_SURNAME, NON_ALPHA_NAME, NON_ALPHA_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY),
                Arguments.of(NULL_SURNAME, NULL_NAME, NORMAL_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY),
                Arguments.of(NON_CAPITALIZE_SURNAME, NON_CAPITALIZE_NAME, NON_CAPITALIZE_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY),
                Arguments.of(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY)
        );
    }

    public static Stream<Arguments> providedParamsForTestsDepartment() {
        return Stream.of(
                Arguments.of(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, EMPTY_DEPARTMENT, BASIC_SALARY),
                Arguments.of(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, NOT_EXISTING_DEPARTMENT, BASIC_SALARY),
                Arguments.of(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, NULL_DEPARTMENT, BASIC_SALARY),
                Arguments.of(NORMAL_SURNAME, NORMAL_NAME, NORMAL_PATRONYMIC, NORMAL_DEPARTMENT, BASIC_SALARY)
        );
    }
     /* ------ CONSTANTS ---------------
      public static final String EMPTY_NAME = "";
    public static final String EMPTY_SURNAME = "";
    public static final String EMPTY_PATRONYMIC = "";
    public static final String EMPTY_DEPARTMENT = "";
    public static final String NON_ALPHA_NAME = "1";
    public static final String NON_ALPHA_SURNAME = "0,6";
    public static final String NON_ALPHA_PATRONYMIC = "0,2";
    public static final String NOT_EXISTING_DEPARTMENT = "0,2";
    public static final String NON_CAPITALIZE_NAME = "иван";
    public static final String NON_CAPITALIZE_SURNAME = "иванов";
    public static final String NON_CAPITALIZE_PATRONYMIC = "иванович";
     public static final String NULL_NAME = null;
    public static final String NULL_SURNAME = null;
    public static final String NULL_PATRONYMIC = null;
    public static final String NULL_DEPARTMENT = null;
    public static final String NORMAL_NAME = "Иван";
    public static final String NORMAL_SURNAME = "Иванов";
    public static final String NORMAL_PATRONYMIC= "Иванович";
    BASIC_SALARY
     */
}
