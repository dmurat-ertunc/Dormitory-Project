package com.dme.DormitoryProject.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class MyMessageListener implements ChannelAwareMessageListener {

    @Override
    @RabbitListener(queues = "${spring.rabbitmq.notification.queue}")
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            String body = new String(message.getBody());
            System.out.println("Received message: " + body);

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}