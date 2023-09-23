package employeeService.employeeBook.model;

import employeeService.employeeBook.exceptions.WrongNameException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.commons.lang3.StringUtils.isAlpha;
//
//@Repository
//public class EmployeeBook{
//
//
//    public static Map<String, Employee> employeeBook = new HashMap<>(Map.of(
//            "Кисложопкин Аркадий Васильевич",
//            new Employee("Кисложопкин", "Аркадий", "Васильевич", "1", 35000),
//            "Селиванов Акакий Александрович",
//            new Employee("Селиванов", "Акакий", "Александрович", "4", 32000),
//            "Кулиджи Казимир Космосович",
//            new Employee("Кулиджи", "Казимир", "Космосович", "3", 42000),
//            "Франклин Бенджамин Батькович",
//            new Employee("Франклин", "Бенджамин", "Батькович", "5", 200_000),
//            "Джугашвили Иосиф Виссарионович",
//            new Employee("Джугашвили", "Иосиф", "Виссарионович", "5", 1_000),
//            "Хирохито Сёма Ёсихитович",
//            new Employee("Хирохито", "Сёма", "Ёсихитович", "4", 100_000)
//    ));
//
//    public EmployeeBook() {
//    }
//
//
//}
