package com.exercise.model.utils.cmd;

import java.io.IOException;

public class CmdUtils {
    public static void main(String[] args) {
        String string = "mysql";
        StringBuilder command=new StringBuilder("cmd.exe /k start sc query " + string);
        //command.append("echo \"hello world\"");
        //执行命令
        try {
            Runtime.getRuntime().exec(command.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
