package com.sodyu.jms.topic_spring;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.*;
import java.io.Serializable;
import java.util.Map;

/**
 * ClassName：
 * @Date 2016/11/1 14:20
 * @since JRE 1.6.0_22  or higher
 */
@Service
public class Producer {
    @Autowired
    private JmsTemplate jmsQueueTemplate;

    public void sendTextMessage(final String queueName, final String txtMessage) {
        jmsQueueTemplate.send(queueName, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(txtMessage);
            }
        });
    }

    public void sendObjectMessage(final String queueName, final Object objectMessage) {
        jmsQueueTemplate.send(queueName, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage((Serializable) objectMessage);
            }
        });
    }

    public void sendMapMessage(final String queueName, final Map<String, Object> mapMessage) {
        jmsQueueTemplate.send(queueName, new MessageCreator() {
            @SuppressWarnings("unchecked")
            public Message createMessage(Session session) throws JMSException {
                MapMessage map = session.createMapMessage();
                for (Map.Entry<String, Object> entry : ((Map<String, Object>) mapMessage).entrySet()) {
                    map.setObject(entry.getKey(), entry.getValue());
                }
                return map;
            }
        });
    }

    public void sendByteMessage(final String queueName, final byte[] message) {
        jmsQueueTemplate.send(queueName, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                BytesMessage bytesMessage = session.createBytesMessage();
                bytesMessage.writeBytes(message);
                return bytesMessage;
            }
        });
    }

    public void sendStreamMessage(final String queueName, final Object message) {
        jmsQueueTemplate.send(queueName, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                StreamMessage streamMessage = session.createStreamMessage();
                streamMessage.writeObject(message);
                return streamMessage;
            }
        });
    }
}
