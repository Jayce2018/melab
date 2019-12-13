package com.exercise.model.test;

import io.swagger.annotations.ApiModel;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

@ApiModel(discriminator = "@Author:sun jie 2019/1/19 14:25")
public class Test {
    private static String str;

    public void t() {

    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IOException {
        String key="Hello world";
        String base="SGVsbG8gd29ybGQ=";

        BASE64Decoder decoder = new BASE64Decoder();
        String baseKey = new String(decoder.decodeBuffer(base));
        BASE64Encoder encoder=new BASE64Encoder();
        //encoder.e
        System.out.println(baseKey);

        Base64 base64=new Base64();
        String baseKey2=new String(base64.decode(base));
        System.out.println(baseKey2);
    }
}

