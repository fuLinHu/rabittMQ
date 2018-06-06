package com.work.rabittmq.module.controller;

import com.work.rabittmq.module.service.impl.SendMessage;
import jdk.internal.org.objectweb.asm.commons.TryCatchBlockSorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabittmq")
public class rabittmqController {
    @Autowired
    private SendMessage sendMessage;
    @RequestMapping("/sendonetoone")
    public String sendMessageonetoone(String message){
        try{
            sendMessage.sendMessageonetoone(message);
            System.out.println("---------"+message+"-------------");
            return message;
        }catch (Exception e){
            e.printStackTrace();
            return "发布消息失败。。。。。。。。";
        }
    }
    @RequestMapping("/sendonetomany") //轮询
    public String sendMessageonetomany(String message){
        try{
            sendMessage.sendMessage(message);
            System.out.println("---------"+message+"-------------");
            return message;
        }catch (Exception e){
            e.printStackTrace();
            return "发布消息失败。。。。。。。。";
        }
    }
    @RequestMapping("/sendonetomanyack") //ack
    public String sendMessageonetomanyack(String message){
        try{
            sendMessage.sendMessageonetomanyack(message);
            System.out.println("---------"+message+"-------------");
            return message;
        }catch (Exception e){
            e.printStackTrace();
            return "发布消息失败。。。。。。。。";
        }
    }


}
