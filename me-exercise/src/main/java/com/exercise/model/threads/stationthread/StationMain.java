package com.exercise.model.threads.stationthread;

public class StationMain {
    public static void main(String[] args) {
        Station stationA=new Station("站台A");
        Station stationB=new Station("站台B");
        Station stationC=new Station("站台C");
        stationA.start();
        stationB.start();
        stationC.start();
    }
}
