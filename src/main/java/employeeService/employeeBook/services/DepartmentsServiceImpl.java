package employeeService.employeeBook.services;

import java.util.*;

public class DepartmentsServiceImpl {
    private Map<String, EmployeeServiceImpl> employeeMap;


    public DepartmentsServiceImpl() {
        employeeMap = new HashMap<>(Map.of(
                "Кисложопкин Аркадий Васильевич",
                new EmployeeServiceImpl("Кисложопкин", "Аркадий", "Васильевич", "1", 35000),
                "Селиванов Акакий Александрович",
                new EmployeeServiceImpl("Селиванов", "Акакий", "Александрович", "4", 32000),
                "Кулиджи Казимир Космосович",
                new EmployeeServiceImpl("Кулиджи", "Казимир", "Космосович", "3", 42000),
                "Франклин Бенджамин Батькович",
                new EmployeeServiceImpl("Франклин", "Бенджамин", "Батькович", "5", 200_000),
                "Джугашвили Иосиф Виссарионович",
                new EmployeeServiceImpl("Джугашвили", "Иосиф", "Виссарионович", "5", 1_000),
                "Хирохито Сёма Ёсихитович",
                new EmployeeServiceImpl("Хирохито", "Сёма", "Ёсихитович", "4", 100_000)
        ));
    }

//

    public Map<String, EmployeeServiceImpl> getEmployees() {
        return employeeMap;
    }

    public void printAllEmployeesData() {
        System.out.println("Личный состав:");
        for (EmployeeServiceImpl employeeServiceImpl : employeeMap.values()) {
            System.out.println(employeeServiceImpl);
        }
    }

    public int countMonthSalaryExpenses() {
        int monthSalaryExpenses = 0;
        for (EmployeeServiceImpl employeeServiceImpl :
                employeeMap.values()) {
            monthSalaryExpenses += employeeServiceImpl.getSalary();
        }
        return monthSalaryExpenses;
    }

    public String findEmployeeMinSalary() {
        int minSalary = 0;
        String employeeWithMinSalary = "";
        for (EmployeeServiceImpl employeeServiceImpl : employeeMap.values()) {
            if (employeeServiceImpl.getSalary() < minSalary || minSalary == 0) {
                minSalary = employeeServiceImpl.getSalary();
                employeeWithMinSalary = employeeServiceImpl.toString();
            }
        }
        return employeeWithMinSalary;

    }

    public String findEmployeeMaxSalary() {
        int maxSalary = 0;
        String employeeWithMaxSalary = "";
        for (EmployeeServiceImpl employeeServiceImpl : employeeMap.values()) {
            if (employeeServiceImpl.getSalary() > maxSalary) {
                maxSalary = employeeServiceImpl.getSalary();
                employeeWithMaxSalary = employeeServiceImpl.toString();
            }
        }
        return employeeWithMaxSalary;
    }

    public int countAverageMonthSalary() {
        int totalMonthSalary = 0;
        int employeesCounter = 0;
        for (EmployeeServiceImpl employeeServiceImpl : employeeMap.values()) {
            totalMonthSalary += employeeServiceImpl.getSalary();
            employeesCounter++;
        }
        return totalMonthSalary / employeesCounter;
    }

    public void showEmployeesNames() {
        StringBuilder employeesString = new StringBuilder();
        for (String employee : employeeMap.keySet()) {
            System.out.println(employee);
        }
    }


    // ------------------------------------- NEXT LEVEL METHODS   -------------------------------------

    public void isDepartment(String department) {
        byte verifier = 0;
        for (EmployeeServiceImpl employeeServiceImpl : employeeMap.values()) {
            if (employeeServiceImpl != null && employeeServiceImpl.isDepartmentExist(department)) {
                verifier++;
                break;
            }
        }
        if (verifier == 0) {
            throw new IllegalArgumentException("Введите название существующего отдела");
        }
    }
    //проверка на существование отдела

    public List<EmployeeServiceImpl> getEmployeesByDep(String department) {
        int subEmployeesCounter = 0;
        List<EmployeeServiceImpl> subEmployeeListServiceImpl = new ArrayList<>(0);
        for (EmployeeServiceImpl employeeServiceImpl : employeeMap.values()) {
            if (employeeServiceImpl.getDepartment().contentEquals(department)) {
                subEmployeeListServiceImpl.add(employeeServiceImpl);

            }
        }
        if (subEmployeeListServiceImpl.isEmpty()) {
            System.out.println("В отделе нет сотрудников");
        }
        return subEmployeeListServiceImpl;
    }
//    создание субмассива отдела для поиска свойств экземпляра EmployeeService в нем

    public void toIndexSalary(int percent) {
        int increaseAmount;
        for (EmployeeServiceImpl employeeServiceImpl : employeeMap.values()) {
            increaseAmount = employeeServiceImpl.getSalary() * percent / 100;
            employeeServiceImpl.setSalary(employeeServiceImpl.getSalary() + increaseAmount);
        }
    }

    public void findEmployeesMinSalaryByDep(String department) {
        isDepartment(department);
        int minSalary = 0;
        String employeeName = "";
        for (EmployeeServiceImpl employeeServiceImpl : getEmployeesByDep(department)) {
            if (employeeServiceImpl.getSalary() < minSalary || minSalary == 0) {
                minSalary = employeeServiceImpl.getSalary();
                employeeName = employeeServiceImpl.getEmployeeInitials();
            }
        }
        if (employeeName.equals("")) {
            System.out.println("В указанном отделе нет сотрудников");
        } else {
            System.out.println("Сотрудник " + employeeName + " получает наименьшую зарплату в отделе " + department + " - " + minSalary + "руб.");
        }
    }

    public void findEmployeesMaxSalaryOfDep(String department) {
        isDepartment(department);
        int maxSalary = 0;
        String employeeName = "";
        for (EmployeeServiceImpl employeeServiceImpl : getEmployeesByDep(department)) {
            if (employeeServiceImpl.getSalary() > maxSalary || maxSalary == 0) {
                maxSalary = employeeServiceImpl.getSalary();
                employeeName = employeeServiceImpl.getEmployeeInitials();
            }
        }
        if (employeeName.equals("")) {
            System.out.println("В указанном отделе нет сотрудников");
        } else {
            System.out.println("Сотрудник " + employeeName + " получает наибольшую зарплату в отделе " + department + " - " + maxSalary + "руб.");
        }


    }

    public void countSummarySalaryOfDep(String department) {
        isDepartment(department);
        int summarySalary = 0;
        for (EmployeeServiceImpl employeeServiceImpl : getEmployeesByDep(department)) {

            summarySalary += employeeServiceImpl.getSalary();
        }
        System.out.println("Сумма затрат на заработную плату отделу " + department + " в месяц, составляет - " + summarySalary + "руб.");
    }


    public void countAverageSalaryOfDep(String department) {
        isDepartment(department);
        int summarySalary = 0;
        int employeesCounter = 0;
        for (EmployeeServiceImpl employeeServiceImpl : getEmployeesByDep(department)) {
            summarySalary += employeeServiceImpl.getSalary();
            employeesCounter++;
        }
        int averageSummarySalary = summarySalary / employeesCounter;
        System.out.println("Средняя заработная плата за месяц в отделе " + department + " составляет - " + averageSummarySalary + "руб.");
    }

    public void toIndexSalaryOfDep(String department, int percent) {
        int increaseAmount;
        for (EmployeeServiceImpl employeeServiceImpl : getEmployeesByDep(department)) {
            if (employeeServiceImpl != null) {
                increaseAmount = employeeServiceImpl.getSalary() * percent / 100;
                employeeServiceImpl.setSalary(employeeServiceImpl.getSalary() + increaseAmount);

            }
        }
    }

    public void printDepartment(String department) {
        isDepartment(department);
        System.out.println("Сотрудники отдела " + department);
        for (EmployeeServiceImpl employeeServiceImpl : getEmployeesByDep(department)) {
            if (employeeServiceImpl == null) {
                System.out.println("В отделе нет сотрудников");
            }

            System.out.println(employeeServiceImpl.getEmployeeInitials() + ", заработная плата - " + employeeServiceImpl.getSalary() + "руб. id - " + employeeServiceImpl.getId());
        }
    }

    public void printWhoEarnLess(int salary) {
        System.out.println("Сотрудники зарабатывающие менее " + salary + " руб.");
        int existCounter = 0;
        for (EmployeeServiceImpl employeeServiceImpl : employeeMap.values()) {
            if (employeeServiceImpl.getSalary() < salary) {
                existCounter = employeeServiceImpl.getSalary();
                System.out.println(employeeServiceImpl);
            }
        }
        if (existCounter == 0) {
            System.out.println("Сотрудников с зарплатой ниже " + salary + "руб. нет");
        }
    }

    public void printWhoEarnMore(int salary) {
        System.out.println("Сотрудники зарабатывающие более " + salary + " руб.");
        int existCounter = 0;
        for (EmployeeServiceImpl employeeServiceImpl : employeeMap.values()) {
            if (employeeServiceImpl.getSalary() >= salary) {
                existCounter = employeeServiceImpl.getSalary();
                System.out.println(employeeServiceImpl);
            }
        }
        if (existCounter == 0) {
            System.out.println("Сотрудников с зарплатой выше или равной " + salary + "руб. нет");
        }
    }

//    –––––––––––––––––––– Last Level –––––––––––––––––––––––

    EmployeeServiceImpl searchEmployee(String surname, String name, String patronymic) {
        return employeeMap.get(surname + " " + name + " " + patronymic);

    }

    EmployeeServiceImpl searchEmployee(int id) {
        for (EmployeeServiceImpl employeeServiceImpl : employeeMap.values()) {
            if (employeeServiceImpl.getId() == id) {
                return employeeServiceImpl;
            }
        }
        return null;
    }

    //    /\/\/\/\/\/\/\/\/\/\/\/\/\/\ util private methods /\/\/\/\/\/\/\/\/\/\/\/\/\/\
    public void addNewEmployee(String surname, String name, String patronymic, String department, int salary) {
        employeeMap.put(surname + " " + name + " " + patronymic, new EmployeeServiceImpl(surname, name, patronymic, department, salary));
        System.out.println("Добавлен");
    }

    public void dismissEmployee(String surname, String name, String patronymic) {
        if (employeeMap.remove(surname + " " + name + " " + patronymic) == null) {
            System.out.println("Сотрудник не найден");
        } else {
            System.out.println("Сотрудник уволен");
        }
    }

    public void dismissEmployee(int id) {

        if (searchEmployee(id) != null) {
            employeeMap.remove(searchEmployee(id).getEmployeeInitials());
            System.out.println("Сотрудник уволен");
        } else {
            System.out.println("Сотрудник не найден");
        }
    }


    public void changeEmployeesSalary(String surname, String name, String patronymic, int changeSalary) {

        EmployeeServiceImpl employeeServiceImpl = searchEmployee(surname, name, patronymic);
        if (employeeServiceImpl != null) {
            employeeServiceImpl.setSalary(employeeServiceImpl.getSalary() + changeSalary);
            System.out.println("Зарплата сотрудника " + employeeServiceImpl.getEmployeeInitials() + " (id: " + employeeServiceImpl.getId() + ") изменена");
        } else {
            System.out.println("Сотрудник не найден");
        }
    }

    public void changeEmployeesSalary(int id, int changeSalary) {

        EmployeeServiceImpl employeeServiceImpl = searchEmployee(id);
        if (employeeServiceImpl != null) {
            employeeServiceImpl.setSalary(employeeServiceImpl.getSalary() + changeSalary);
            System.out.println("Зарплата сотрудника " + employeeServiceImpl.getEmployeeInitials() + " (id: " + employeeServiceImpl.getId() + ") изменена");
        } else {
            System.out.println("Сотрудник не найден");
        }
    }

    //
    public void changeEmployeeDepartment(String surname, String name, String patronymic, String department) {
        EmployeeServiceImpl employeeServiceImpl = searchEmployee(surname, name, patronymic);
        if (employeeServiceImpl != null) {
            employeeServiceImpl.setDepartment(department);
            System.out.println("Сотрудник " + employeeServiceImpl.getEmployeeInitials() + " переведен в отдел " + employeeServiceImpl.getDepartment());
        } else {
            System.out.println("Сотрудник не найден");
        }
    }

    //
    public void changeEmployeeDepartment(int id, String department) {
        isDepartment(department);
        EmployeeServiceImpl employeeServiceImpl = searchEmployee(id);
        if (employeeServiceImpl != null) {
            employeeServiceImpl.setDepartment(department);
            System.out.println("Сотрудник " + employeeServiceImpl.getEmployeeInitials() + " переведен в отдел " + employeeServiceImpl.getDepartment());
        } else {
            System.out.println("Сотрудник не найден");
        }
    }

    //
    public Set<String> printActualDepartments() {

        return EmployeeServiceImpl.departments;
    }

    //
    public void printAllDepartmentPersonnel() {

        for (String actualDepartment : printActualDepartments()) {
            System.out.println("Отдел " + actualDepartment + ":");
            for (EmployeeServiceImpl employeeServiceImpl : getEmployeesByDep(actualDepartment)) {
                if (employeeServiceImpl == null) {
                    System.out.println("В отделе нет сотрудников");
                }

                System.out.println(employeeServiceImpl.getEmployeeInitials() + ", id: " + employeeServiceImpl.getId());

            }
        }
    }
//
//
//    –––––––––––––––––––––––
//    Class End ––––––––––––––––––––––––
}
