package com.sodyu.jms.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

/**
 * @Date 2016/11/1 14:20
 * @since JRE 1.6.0_22  or higher
 */
public class Consumer {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    protected static final Logger log = Logger.getLogger(Consumer.class.getName());

    public static void main(String[] args) {
        System.out.println(URL);
        ConnectionFactory connectionFactory;
        Connection connection = null;
        Session session = null;
        Destination destination = null;
        MessageConsumer consumer = null;
        try {
            connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, URL);
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("helloworld");
            consumer = session.createConsumer(destination);
//            while(true){
//                TextMessage message=(TextMessage)consumer.receive(100000);
//                if(message!=null){
//                    System.out.println(message);
//                }else {
//                    break;
//                }
//            }


            consumer.setMessageListener(new Listener());
            session.commit();
        } catch (JMSException e) {
            log.error("接收消息发生异常"+e);
        }
    }
}
