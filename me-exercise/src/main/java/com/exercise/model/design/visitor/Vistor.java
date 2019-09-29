package com.exercise.model.design.visitor;

public class Vistor implements IVisitor {
    @Override
    public void visit(CommonEmployee commonEmployee) {
        System.out.println(this.getCommonEmployeeInfo(commonEmployee));
    }

    @Override
    public void visit(Manager manager) {
        System.out.println(this.getManagerInfo(manager));
    }

    public String getBaseInfo(Employee employee) {
        String info = "姓名：" + employee.getName() + "\t";
        info += "性别：" + employee.getSex() + "\t";
        return info;
    }

    public String getCommonEmployeeInfo(CommonEmployee commonEmployee) {
        String info = this.getBaseInfo(commonEmployee);
        info += "工作：" + commonEmployee.getJob();
        return info;
    }

    public String getManagerInfo(Manager manager) {
        String info = this.getBaseInfo(manager);
        info += "业绩" + manager.getPerformance();
        return info;
    }
}
