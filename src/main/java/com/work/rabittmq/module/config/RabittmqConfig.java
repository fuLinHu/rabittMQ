package com.work.rabittmq.module.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabittmqConfig {

    @Value("${queue.name.hello}")
    private String queueHello;
    @Value("${queue.name.helloack}")
    private String queueHelloack;

    @Bean
    public Queue Queue() {
        return new Queue(queueHello);
    }
    @Bean
    public Queue Queueack() {
        //queue持久化，就是在实例时调用具有参数durable的构造函数.
        //默认是持久化的
        return new Queue(queueHelloack);
    }
}
