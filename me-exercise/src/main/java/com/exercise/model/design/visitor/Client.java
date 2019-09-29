package com.exercise.model.design.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式
 */
public class Client {
    public static void main(String[] args) {
        List<Employee> employeeList = Client.mockData();
        for (Employee employee:employeeList){
            employee.accept(new Vistor());
        }
    }

    private static List<Employee> mockData() {
        List<Employee> employeeList = new ArrayList<>();
        CommonEmployee commonEmployee = new CommonEmployee();
        commonEmployee.setJob("job");
        commonEmployee.setName("name");
        commonEmployee.setSex(Employee.MALE);
        employeeList.add(commonEmployee);

        Manager manager = new Manager();
        manager.setPerformance("performance");
        manager.setName("name");
        manager.setSex(Employee.FEMALE);
        employeeList.add(manager);

        return employeeList;
    }
}
