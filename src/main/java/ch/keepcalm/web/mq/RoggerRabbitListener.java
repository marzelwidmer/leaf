package ch.keepcalm.web.mq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RoggerRabbitListener {

    private static final Logger logger = LoggerFactory.getLogger(RoggerRabbitListener.class);

    @RabbitListener(queues = "myQueue")
    public void processMessage(String data) {
        logger.info(data + " revived");
    }

}

