package employeeService.employeeBook.model;

import employeeService.employeeBook.exceptions.IllegalDepartmentNameException;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Employee {
    private String surname;
    private String name;
    private String patronymic;
    private String department;
    private int salary;
    private int id;

    private static int idCounter = 1;

    static final Set<String> departments = new HashSet<>(Set.of("1", "2", "3", "4", "5"));



    public Employee(String surname, String name, String patronymic, String department, int salary) {

        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.department  = department;
        this.salary = salary;
        id = idCounter++;
    }

    // ------------------ own utily methods ---------------------


    public String getEmployeeInitials() {
        return surname + " " + name + " " + patronymic;
    }


    //   ---------------------- setters-getters area -----------------------
    public String getSurname() {
        return surname;
    }


    public String getName() {
        return name;
    }



    public String getPatronymic() {
        return patronymic;
    }


    public String getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public static Set<String> getDepartments() {
        return departments;
    }

//    Setters


    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDepartment(String department) {
        if(departments.contains(department)){
            this.department = department;
        } else{
            throw new IllegalDepartmentNameException("Указанный отдел не найден");
        }
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + patronymic + ". Отдел - " + department + ". Зарплата в мес. - " + salary + "руб. (id: " + id + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return surname.equals(employee.surname) && name.equals(employee.name) && patronymic.equals(employee.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, patronymic);
    }

    //    ------------------------------------- class end -------------------------------------

}
