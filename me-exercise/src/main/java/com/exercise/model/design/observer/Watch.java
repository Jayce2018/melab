package com.exercise.model.design.observer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 间谍
 */
public class Watch extends Thread {
    private HanFeiZi hanFeiZi;

    private LiSi liSi;

    private String type;

    public Watch(HanFeiZi hanFeiZi, LiSi liSi, String type) {
        this.hanFeiZi = hanFeiZi;
        this.liSi = liSi;
        this.type = type;
    }

    @Override
    public void run() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss a");
        synchronized (this) {
            while (true) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.type.equals("breakfast")) {
                    System.out.println(simpleDateFormat.format(new Date()));
                    if (this.hanFeiZi.isHavingBreakfast()) {
                        this.liSi.update("韩非子在早餐");
                        this.hanFeiZi.setHavingBreakfast(false);
                    }
                }
                if (this.type.equals("fun")) {
                    System.out.println(simpleDateFormat.format(new Date()));
                    if (this.hanFeiZi.isHavingFun()) {
                        this.liSi.update("韩非子在嗨皮");
                        this.hanFeiZi.setHavingFun(false);
                    }
                }
            }
        }
    }
}
