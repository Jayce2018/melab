package com.exercise.model.design.singleton;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import lombok.Data;
/**
 * 单例模式的优点：
 *
 * 在内存中只有一个对象，节省内存空间。
 * 避免频繁的创建销毁对象，可以提高性能。
 * 避免对共享资源的多重占用。
 * 可以全局访问。
 *
 * </>适用场景：由于单例模式的以上优点，所以是编程中用的比较多的一种设计模式。我总结了一下我所知道的适合使用单例模式的场景：
 *
 * 需要频繁实例化然后销毁的对象。
 * 创建对象时耗时过多或者耗资源过多，但又经常用到的对象。
 * 有状态的工具类对象。
 * 频繁访问数据库或文件的对象。
 * 以及其他我没用过的所有要求只有一个对象的场景。
 * */
@Data
@ApiModel(description = "单例模式",value ="singleton")
public class Singleton {
    private static Singleton singleton;

    private Integer code;

    private String value;

    public Singleton(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    private static Singleton getInstance(){
        if(null==singleton){
            singleton=new Singleton(200,"ok");
        }
        return singleton;
    }

    public static void main(String[] args) {
        Singleton single=Singleton.getInstance();
        System.out.println(JSONObject.toJSON(single));
    }
}
