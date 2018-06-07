package com.work.rabittmq.module.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabittmqConfig {

    @Value("${queue.name.hello}")
    private String queueHello;
    @Value("${queue.name.helloack}")
    private String queueHelloack;
    @Value("${queue.name.helloPsfanoutA}")
    private  String queueHelloPsfanoutA;
    @Value("${queue.name.helloPsfanoutB}")
    private  String queueHelloPsfanoutB;
    @Value("${queue.name.helloPsfanoutC}")
    private  String queueHelloPsfanoutC;
    @Value("${queue.name.hellodirectA}")
    private  String queueHellodirectA;
    @Value("${queue.name.hellodirectB}")
    private  String queueHellodirectB;
    @Value("${queue.name.hellotopicA}")
    private  String queueHellotopicA;
    @Value("${queue.name.hellotopicB}")
    private  String queueHellotopicB;






    @Value("${exchange.fanout.name}")
    private String exchangefanout;
    @Value("${exchange.direct.name}")
    private String exchangedirect;
    @Value("${exchange.topic.name}")
    private String exchangetopic;



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

    //-------------------------------------p/s模式---------------------------------------------//
    /*Fanout Exchange 消息广播的模式，不管路由键或者是路由模式，会把消息发给绑定给它的全部队列，
    如果配置了routing_key会被忽略。*/
    @Bean
    public Queue AMessage() {
        return new Queue(queueHelloPsfanoutA);
    }

    @Bean
    public Queue BMessage() {
        return new Queue(queueHelloPsfanoutB);
    }

    @Bean
    public Queue CMessage() {
        return new Queue(queueHelloPsfanoutC);
    }

    /**
     * 定义交换机
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(exchangefanout);
    }
    //交换机  绑定队列
    @Bean
    Binding bindingExchangeA(Queue AMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }
    //-----------routing模式--------------------------------------------------
    @Bean
    public Queue AMessagedirect() {
        return new Queue(queueHellodirectA);
    }

    @Bean
    public Queue BMessagedirect() {
        return new Queue(queueHellodirectB);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(exchangedirect);
    }

    @Bean
    Binding bindingExchangeMessage(Queue AMessagedirect, DirectExchange directExchange) {
        return BindingBuilder.bind(AMessagedirect).to(directExchange).with("orange");
    }

    @Bean
    Binding bindingExchangeMessageBOfBlack(Queue BMessagedirect, DirectExchange directExchange) {
        return BindingBuilder.bind(BMessagedirect).to(directExchange).with("black");
    }

    @Bean
    Binding bindingExchangeMessageBOfGreen(Queue BMessagedirect, DirectExchange directExchange) {
        return BindingBuilder.bind(BMessagedirect).to(directExchange).with("green");
    }
    //-------------TOPIC  模式------------------------------------------
    @Bean
    public Queue queueMessagetopicA() {
        return new Queue(queueHellotopicA);
    }

    @Bean
    public Queue queueMessagetopicB() {
        return new Queue(queueHellotopicB);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(exchangetopic);
    }

    @Bean
    Binding bindingExchangeMessagetopicA(Queue queueMessagetopicA, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueMessagetopicA).to(topicExchange).with("topic.message");
    }
    @Bean
    Binding bindingExchangeMessagetopicB(Queue queueMessagetopicB, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueMessagetopicB).to(topicExchange).with("topic.#");
    }




}
