package com.exercise.model.generic.base;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public class BaseOperate<T> {

    public String toJsonString(T t) {
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(t);
        return jsonObject.toJSONString();
    }

    public T getLast(List<T> list) throws Exception {
        int s = list.size();
        if (s > 0)
            return list.get(s - 1);
        throw new Exception("对象为空！");
    }
}
