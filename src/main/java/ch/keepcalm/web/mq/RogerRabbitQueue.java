package ch.keepcalm.web.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by marcelwidmer on 10.10.16.
 */
@Component
public class RogerRabbitQueue {

    @Bean
    public Queue myQueue() {
        return new Queue("myQueue");
    }

}
