package com.exercise.model.design.corp;

public class Client {
    public static void main(String[] args) {
        Branch boss=new Branch("boss","老总");
        Branch manager1=new Branch("manager1","总管1");
        Branch manager2=new Branch("manager2","总管2");
        Branch staff1=new Branch("staff1","员工1");
        Branch staff2=new Branch("staff2","员工2");
        Branch staff3=new Branch("staff3","员工3");
        Branch staff4=new Branch("staff4","员工4");

        boss.addSubordinate(manager1);
        boss.addSubordinate(manager2);
        manager1.addSubordinate(staff1);
        manager1.addSubordinate(staff2);
        manager2.addSubordinate(staff3);
        manager2.addSubordinate(staff4);

        display(boss);

    }

    //下属展示
    public static void display(Branch branch){
        if(null!=branch.getSubordinate()&&branch.getSubordinate().size()!=0) {
            //System.out.println(branch.getInfo());
            for (Corp item : branch.getSubordinate()) {
                System.out.println(item.getInfo());
                display((Branch) item);
            }
        }else{
            return;
        }

    }
}
