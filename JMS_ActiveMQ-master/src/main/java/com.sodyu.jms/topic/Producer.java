package com.sodyu.jms.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import javax.jms.*;

/**
 * @Date 2016/11/1 14:20
 * @since JRE 1.6.0_22  or higher
 */
public class Producer {
    private static final String USERNAME= ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD=ActiveMQConnection.DEFAULT_PASSWORD;
    private static final String URL=ActiveMQConnection.DEFAULT_BROKER_URL;
    protected static final Logger log = Logger.getLogger(Producer.class.getName());

    public static void main(String[] args)  {
        System.out.println(URL);
        ConnectionFactory connectionFactory;
        Connection connection=null;
        Session session=null;
        Destination destination=null;
        MessageProducer producer=null;
        try {
            connectionFactory =new ActiveMQConnectionFactory(USERNAME,PASSWORD,URL);
            connection=connectionFactory.createConnection();
            connection.start();
            session=connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            destination=session.createTopic("topic");
            producer=session.createProducer(destination);
            sendMessage(session, producer);
            session.commit();
        } catch (JMSException e) {
            log.error("发送消息发生异常"+e);
        } finally {
            if(connection!=null){
                try {
                    if(session!=null){
                        session.close();
                    }
                    connection.close();
                } catch (JMSException e) {
                    log.error("关闭连接异常");
                }
            }

        }

    }


    private static void sendMessage(Session session,MessageProducer producer) throws JMSException{
        for (int i = 0; i <10 ; i++) {
            TextMessage message = session.createTextMessage("ActiveMQ消息" + i);
            System.out.println(message);
            producer.send(message);
        }
    }
}
