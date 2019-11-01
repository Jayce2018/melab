package com.exercise.model.rabbitmq.pdf;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

public class Client extends Application{
    private String routingKey="apple";

    // 测试线程池
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    @Override
    public void start(Stage primaryStage) throws Exception {
        executorService.submit(()->{
            try {
                send();
            } catch (IOException | InterruptedException | TimeoutException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(()->{
            try {
                receive();
            } catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
        });

    }

    public  void send() throws IOException, TimeoutException, InterruptedException {
        MqProducer mqProducer=new MqProducer();
        while (true) {
            mqProducer.execute(routingKey);
            Thread.sleep(3*1000);
        }
    }

    public void receive() throws IOException, TimeoutException {
        MqConsumer consumer=new MqConsumer();
        consumer.execute(routingKey);
    }
}
