package employeeService.employeeBook;

import employeeService.employeeBook.exceptions.IllegalDepartmentNameException;
import employeeService.employeeBook.exceptions.VoidDepartmentException;
import employeeService.employeeBook.interfaces.DepartmentsService;
import employeeService.employeeBook.interfaces.EmployeeService;
import employeeService.employeeBook.model.Employee;
import employeeService.employeeBook.services.DepartmentsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static employeeService.employeeBook.DepartmentServiceTestConstants.*;
import static employeeService.employeeBook.TestConstants.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTests {
    @Mock
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private DepartmentsServiceImpl out;

/*
VOID_MAP
NORMAL_MAP
VOID_SECOND_DEP_MAP
ONLY_SECOND_DEP_IS_FULL_MAP
 */


    public static Stream<Arguments> employeesMapConstants() {
        return Stream.of(
                Arguments.of(VOID_MAP),
                Arguments.of(NORMAL_MAP),
                Arguments.of(VOID_SECOND_DEP_MAP),
                Arguments.of(ONLY_SECOND_DEP_IS_FULL_MAP)
        );

    }

    @Test
    public void shouldGetEmployeesByDep() {
        when(employeeServiceMock.getEmployeeBook())
                .thenReturn(NORMAL_MAP);

        List<Employee> expectedList = new ArrayList<>(List.of(
                new Employee("Селиванов", "Акакий", "Александрович", "2", 32000),
                new Employee("Селиванова", "Акакия", "Александровна", "2", 32000)
        ));

        List<Employee> actualList = out.getEmployeesByDep("2");
        assertIterableEquals(expectedList, actualList);
        verify(employeeServiceMock, times(1)).getEmployeeBook();
    }

    @Test
    public void shouldThrowExceptionIllegalDep() {
        assertThrows(IllegalDepartmentNameException.class,
                () -> out.getEmployeesByDep("wrong"));
    }

    @Test
    public void shouldThrowVoidDepException() {
        when(employeeServiceMock.getEmployeeBook())
                .thenReturn(VOID_SECOND_DEP_MAP);
        assertThrows(VoidDepartmentException.class,
                () -> out.getEmployeesByDep("2"));
        verify(employeeServiceMock, times(1)).getEmployeeBook();
    }

    @Test
    public void shouldFindEmployeesMinSalaryByDep() {
        when(employeeServiceMock.getEmployeeBook())
                .thenReturn(ONLY_SECOND_DEP_IS_FULL_MAP);
        Employee expected = new Employee("Джугашвили", "Иосиф", "Виссарионович", "5", 1_000);
        Employee actual = out.findEmployeesMinSalaryByDep("2");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindEmployeesMaxSalaryOfDep() {
        when(employeeServiceMock.getEmployeeBook())
                .thenReturn(ONLY_SECOND_DEP_IS_FULL_MAP);
        Employee expected = new Employee("Франклин", "Бенджамин", "Батькович", "2", 200_000);
        Employee actual = out.findEmployeesMaxSalaryOfDep("2");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCountSummarySalaryOfDep() {
        when(employeeServiceMock.getEmployeeBook())
                .thenReturn(NORMAL_MAP);
        double expected = 200_000 + 100_000;
        double actual = out.countSummarySalaryOfDep("4");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldCountAverageSalaryOfDep() {
        when(employeeServiceMock.getEmployeeBook())
                .thenReturn(NORMAL_MAP);
        double expected = (200_000 + 100_000) / 2;
        double actual = out.countAverageSalaryOfDep("4");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldIndexSalaryOfDep() {
        when(employeeServiceMock.getEmployeeBook())
                .thenReturn(NORMAL_MAP);
        Employee expectedEmployee = new Employee("Джугашвили", "Иосиф", "Виссарионович", "5", 1_000);
        double expected = expectedEmployee.getSalary() + expectedEmployee.getSalary() * 50 / 100;

        out.toIndexSalaryOfDep("5", 50);
        double actual = out.getEmployees().get("Джугашвили Иосиф Виссарионович").getSalary();
        assertEquals(expected, actual);
    }

    @Test
    public void printDepartment() {
        when(employeeServiceMock.getEmployeeBook())
                .thenReturn(NORMAL_MAP);
        List<Employee> expected = new ArrayList<>(List.of(
                new Employee("Франклин", "Бенджамин", "Батькович", "4", 200_000),
                new Employee("Хирохито", "Сёма", "Ёсихитович", "4", 100_000)
        ));
        List<Employee> actual = out.printDepartment("4");

        assertIterableEquals(expected, actual);
    }

    @Test
    public void printAllDepartmentPersonnel() {
        when(employeeServiceMock.getEmployeeBook())
                .thenReturn(NORMAL_MAP);
        Map<String, List<Employee>> expected = new HashMap<>();
        expected.put("1",
                new ArrayList<>(List.of(
                        new Employee("Кисложопкин", "Аркадий", "Васильевич", "1", 35000))
                ));
        expected.put("2",
                new ArrayList<>(List.of(
                        new Employee("Селиванов", "Акакий", "Александрович", "2", 32000),
                        new Employee("Селиванова", "Акакия", "Александровна", "2", 32000))
                ));
        expected.put("3",
                new ArrayList<Employee>(List.of(
                        new Employee("Кулиджи", "Казимир", "Космосович", "3", 42000))
                ));
        expected.put("4",
                new ArrayList<Employee>(List.of(
                        new Employee("Франклин", "Бенджамин", "Батькович", "4", 200_000),
                        new Employee("Хирохито", "Сёма", "Ёсихитович", "4", 100_000))
                ));

        expected.put("5",
                new ArrayList<Employee>(List.of(new Employee("Джугашвили", "Иосиф", "Виссарионович", "5", 1_000))
                )
        );

        Map<String, List<Employee>> actual = out.printAllDepartmentPersonnel();

        assertIterableEquals(expected.keySet(), actual.keySet());
        assertIterableEquals(expected.values(), actual.values());
    }
}
