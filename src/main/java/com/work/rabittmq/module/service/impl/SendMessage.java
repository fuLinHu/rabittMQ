package com.work.rabittmq.module.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendMessage {
    protected static Logger logger= LoggerFactory.getLogger(SendMessage.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${queue.name.hello}")
    private String queueHello;
    @Value("${queue.name.helloack}")
    private String queueHelloack;

    @Value("${exchange.fanout.name}")
    private String exchangefanout;
    @Value("${exchange.direct.name}")
    private String exchangedirect;
    @Value("${exchange.topic.name}")
    private String exchangetopic;


    public void sendMessage(String message){
        try {
            Thread.sleep(1000L);
            System.out.println("---------"+message+"------sendMessage-------");
            for(int i=0;i<10;i++){
                this.rabbitTemplate.convertAndSend(queueHello, message);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void sendMessageonetoone(String message) {
        try {
            Thread.sleep(1000L);
            System.out.println("---------"+message+"------sendMessage-------");
            this.rabbitTemplate.convertAndSend(queueHello, message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void sendMessageonetomanyack(String message) {
        try {
            Thread.sleep(1000L);
            System.out.println("---------"+message+"------sendMessage-------");
            for(int i=0;i<100;i++){
                this.rabbitTemplate.convertAndSend(queueHelloack, message+"____"+"{"+i+"}");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void sendMessagePsfanout(String message) {
        logger.debug("Sender : " + message);
         //参数   1.交换机名字   2.第二个参数表示routing key  3.发送信息
        this.rabbitTemplate.convertAndSend(exchangefanout,"", message);
    }

    public void sendOrangedirect(String message) {
        String context = "hi, i am message orange"+"  "+message;
        logger.info("Sender : " + context);
        //参数   1.交换机名字   2.第二个参数表示routing key  3.发送信息
        this.rabbitTemplate.convertAndSend(exchangedirect, "orange", context);
    }
    public void sendBlackdirect(String message) {
        String context = "hi, i am messages black"+"  "+message;
        logger.debug("Sender : " + context);
        this.rabbitTemplate.convertAndSend(exchangedirect, "black", context);
    }
    public void sendGreendirect(String message) {
        String context = "hi, i am messages green"+"  "+message;
        logger.debug("Sender : " + context);
        this.rabbitTemplate.convertAndSend(exchangedirect, "green", context);
    }


    public void sendMessagetopicA(String message) {
        String context = "hi, i am message sendMessagetopicA   "+message;
        logger.info("Sender : " + context);
        this.rabbitTemplate.convertAndSend(exchangetopic, "topic.message", context);
    }
    public void sendMessagetopicB(String message) {
        String context = "hi, i am messages sendMessagetopicB   "+message;
        logger.info("Sender : " + context);
        this.rabbitTemplate.convertAndSend(exchangetopic, "topic.messages", context);
    }
}
