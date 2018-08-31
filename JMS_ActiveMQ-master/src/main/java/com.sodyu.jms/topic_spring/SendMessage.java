package com.sodyu.jms.topic_spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * ClassName：
 * @Date 2016/11/8 19:17
 * @since JRE 1.6.0_22  or higher
 */
@Service
public class SendMessage {
    private static final ApplicationContext context= new ClassPathXmlApplicationContext("ApplicationContext.xml");
    //private static final ApplicationContext context = new AnnotationConfigApplicationContext("com.sodyu.jms.topic_spring");
    @Autowired
    private Producer producer;
    public static void main(String[] args) {
        Producer producer=(Producer)context.getBean(Producer.class);
        System.out.println(producer);

        for (int i = 0; i <10 ; i++) {
            producer.sendTextMessage("spring_activemq","message:"+i);
        }

    }
}
