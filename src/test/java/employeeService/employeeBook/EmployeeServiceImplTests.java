package employeeService.employeeBook;

import employeeService.employeeBook.interfaces.EmployeeService;
import employeeService.employeeBook.model.Employee;
import employeeService.employeeBook.model.EmployeeBook;
import employeeService.employeeBook.services.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


/*constants
TEST_IVAN_NO_DEP
TEST_NO_NAME
NON_ALPHA_NAME
 */
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTests {

    @Mock
    private EmployeeBook employeeBookMock;

    @InjectMocks
    private EmployeeServiceImpl out;
//
//    @Test
//    public void shouldSearchEmployee() {
//        when(out.searchEmployee("surname",  "name",  "patronymic"))
//                .thenReturn()
//    }


    public Employee searchEmployee(int id) {
        return null;
    }


    public Employee addNewEmployee(String surname, String name, String patronymic, String department, int salary) {
        return null;
    }


    public void dismissEmployee(String surname, String name, String patronymic) {

    }


    public void dismissEmployee(int id) {

    }


    public int countMonthSalaryExpenses() {
        return 0;
    }


    public Employee findEmployeeMinSalary() {
        return null;
    }


    public Employee findEmployeeMaxSalary() {
        return null;
    }


    public double countAverageMonthSalary() {
        return 0;
    }


    public void changeEmployeesSalary(String surname, String name, String patronymic, int changeSalary) {

    }


    public void changeEmployeesSalary(int id, int changeSalary) {

    }


    public void changeEmployeeDepartment(String surname, String name, String patronymic, String department) {

    }


    public void changeEmployeeDepartment(int id, String department) {

    }


    public void toIndexSalary(int percent) {

    }


    public Collection<Employee> printAllEmployeesData() {
        return null;
    }


    public Set<String> showEmployeesNames() {
        return null;
    }


    public Set<Employee> searchWhoEarnLess(int salary) {
        return null;
    }


    public void printWhoEarnMore(int salary) {

    }
}
