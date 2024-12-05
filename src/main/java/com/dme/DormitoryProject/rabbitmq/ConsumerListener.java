package com.dme.DormitoryProject.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerListener {

    @RabbitListener(queues = "${spring.rabbitmq.notification.queue}")
    public void listen(StudentMessage studentMessage) {
        System.out.println("Receive message: [{}]"+ studentMessage);
    }
}