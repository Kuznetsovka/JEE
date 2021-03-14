package org.example.jms;

import org.example.service.ProductDto;
import org.example.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/myProductQueue")
        }
)
public class JmsProductReceiver implements MessageListener {

    private final Logger logger = LoggerFactory.getLogger(JmsProductReceiver.class);

    @EJB
    private ProductServiceImpl productService;

    @Override
    public void onMessage(Message message) {
        logger.info("New JMS message");

        if (message instanceof ObjectMessage) {
            ObjectMessage om = (ObjectMessage) message;
            try {
                productService.saveOrUpdate((ProductDto) om.getObject());
            } catch (JMSException e) {
                logger.error("", e);
            }
        }
    }
}
