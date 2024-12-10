//package com.dme.DormitoryProject.rabbitmq;
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.logging.log4j.LogManager;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.logging.Logger;
//
//@RestController
//@RequestMapping("api/rabbit")
//@Slf4j
//public class ProducerController {
//
//
//    @Value("${spring.rabbitmq.notification.exchange}")
//    private String exchange;
//
//    @Value("${spring.rabbitmq.notification.routing-key}")
//    private String routingKey;
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @PostMapping("/sendMessage")
//    public void sendMessage(@RequestBody StudentMessage studentMessage) {
//        rabbitTemplate.convertAndSend(exchange, routingKey, studentMessage);
//        System.out.println("Send sent: [{}]"+ studentMessage);
//    }
//}
