package com.work.rabittmq.module.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component

public class AcceptMessage {
    protected static Logger logger= LoggerFactory.getLogger(AcceptMessage.class);
    private final  String queueHello="hello";
    private final  String queueHelloack="helloack";
    private final  String queueHelloPsfanoutA="fanout.A";
    private final  String queueHelloPsfanoutB="fanout.B";
    private final  String queueHelloPsfanoutC="fanout.C";
    private final  String queueHellodirectA="direct.A";
    private final  String queueHellodirectB="direct.B";
    private final  String queueHellotopicA="topic.A";
    private final  String queueHellotopicB="topic.B";



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
    @RabbitListener(queues = queueHelloPsfanoutA)
    public void getMessagePsfanoutA(String hello) {
        try {
            System.out.println("Receiver getMessagePsfanoutA : " + hello);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RabbitListener(queues = queueHelloPsfanoutB)
    public void getMessagePsfanoutB(String hello) {
        try {
            System.out.println("Receiver getMessagePsfanoutB : " + hello);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @RabbitListener(queues = queueHelloPsfanoutC)
    public void getMessagePsfanoutC(String hello) {
        try {
            System.out.println("Receiver getMessagePsfanoutC : " + hello);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = queueHellodirectA)
    public void getMessagedirectA(String message) {
        logger.info("direct.A Receiver  : " + message);
    }
    @RabbitListener(queues = queueHellodirectB)
    public void getMessagedirectB(String message) {
        logger.info("direct.B Receiver  : " + message);
    }



    @RabbitListener(queues = queueHellotopicA)
    public void getMessagetopicA(String message) {
        logger.info("Topic getMessagetopicA  : " + message);
    }
    @RabbitListener(queues = queueHellotopicB)
    public void getMessagetopicB(String message) {
        logger.info("Topic getMessagetopicB  : " + message);
    }

}
