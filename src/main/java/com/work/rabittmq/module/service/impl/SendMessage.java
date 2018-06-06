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
}
