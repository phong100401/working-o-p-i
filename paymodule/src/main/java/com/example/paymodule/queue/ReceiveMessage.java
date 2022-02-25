package com.example.paymodule.queue;

import com.example.paymodule.dto.OrderDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.example.paymodule.queue.Config.QUEUE_PAY;

@Component
public class ReceiveMessage {
    @Autowired
    ConsumerService consumerService;

    @RabbitListener(queues = {QUEUE_PAY})
    public void getInfoOrder(OrderDto orderDto) {
        consumerService.handerPayment(orderDto);
    }
}
