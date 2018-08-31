package com.sodyu.jms.topic;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @Date 2016/11/1 15:25
 * @since JRE 1.6.0_22  or higher
 */
public class Listener implements MessageListener{

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("消费者接收到消息："+((TextMessage)message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
