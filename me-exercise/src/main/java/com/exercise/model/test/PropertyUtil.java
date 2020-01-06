package com.exercise.model.test;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PropertyUtil {
    @SuppressWarnings("unchecked")
    public static PropertyDescriptor getPropertyDescriptor(Class clazz, String propertyName) {
        //构建一个可变字符串用来构建方法名称
        StringBuffer sb = new StringBuffer();
        Method setMethod = null;
        Method getMethod = null;
        PropertyDescriptor pd = null;
        try {
            //根据字段名来获取字段
            Field f = clazz.getDeclaredField(propertyName);
            if (f!= null) {
                //构建方法的后缀
                String methodEnd = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
                //构建set方法
                sb.append("set").append(methodEnd);
                setMethod = clazz.getDeclaredMethod(sb.toString(), f.getType());
                //清空整个可变字符串
                sb.delete(0, sb.length());
                //构建get方法
                sb.append("get").append(methodEnd);
                //构建get 方法
                getMethod = clazz.getDeclaredMethod(sb.toString());
                //构建一个属性描述器 把对应属性 propertyName 的 get 和 set 方法保存到属性描述器中
                pd = new PropertyDescriptor(propertyName, getMethod, setMethod);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return pd;
    }

    @SuppressWarnings("unchecked")
    public static void setProperty(Object obj, String propertyName, Object value){
        //获取对象的类型
        Class clazz = obj.getClass();
        //获取 clazz 类型中的 propertyName 的属性描述器
        PropertyDescriptor pd = getPropertyDescriptor(clazz,propertyName);
        //从属性描述器中获取 set 方法
        Method setMethod = pd.getWriteMethod();
        try {
            //调用 set 方法将传入的value值保存属性中去
            setMethod.invoke(obj, value);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static Object getProperty(Object obj, String propertyName){
        //获取对象的类型
        Class clazz = obj.getClass();
        //获取 clazz 类型中的 propertyName 的属性描述器
        PropertyDescriptor pd = getPropertyDescriptor(clazz,propertyName);
        //从属性描述器中获取 get 方法
        Method getMethod = pd.getReadMethod();
        Object value =null ;
        try {
            //调用方法获取方法的返回值
            value = getMethod.invoke(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回值
        return value;
    }
}

class Book{
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Book book=new Book();
        book.setId(1L);
        book.setName("name");
        System.out.println(PropertyUtil.getProperty(book,"id"));

    }
}




