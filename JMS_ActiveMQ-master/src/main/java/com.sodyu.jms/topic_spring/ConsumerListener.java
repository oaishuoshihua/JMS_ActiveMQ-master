package com.sodyu.jms.topic_spring;


import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @Date 2016/11/1 15:25
 * @since JRE 1.6.0_22  or higher
 */
public class ConsumerListener implements javax.jms.MessageListener {
    @Override
    public void onMessage(Message message) {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("============" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
    }
}
