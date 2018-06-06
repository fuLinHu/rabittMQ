package com.work.rabittmq.module.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component

public class AcceptMessage {

    private final  String queueHello="hello";
    private final  String queueHelloack="helloack";

    @RabbitListener(queues = queueHello)
    public void getMessage(String hello) {
        System.out.println("Receiver sendMessage : " + hello);
    }
    @RabbitListener(queues = queueHello)
    public void getMessage1(String hello) {
        System.out.println("Receiver sendMessage1 : " + hello);
    }
    @RabbitListener(queues = queueHelloack)
    public void getMessageack1(String hello) {
        try {
            Thread.sleep(2000);
            System.out.println("Receiver getMessageack1 : " + hello);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }





    @RabbitListener(queues = queueHelloack)
    public void getMessageack2(String hello) {
        try {
            Thread.sleep(3000);
            System.out.println("Receiver getMessageack2 : " + hello);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
