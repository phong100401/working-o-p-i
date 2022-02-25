package com.example.ordermodule.queue;

import com.example.ordermodule.dto.OrderDto;
import com.example.ordermodule.entity.Order;
import com.example.ordermodule.enums.InventoryStatus;
import com.example.ordermodule.enums.PaymentStatus;
import com.example.ordermodule.service.OrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.ordermodule.queue.Config.*;

@Service
@Log4j2
public class ConsumerService {
    @Autowired
    OrderService orderService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Transactional
    public void handlerMessage(OrderDto orderDto){
        try {
            if(!orderDto.validationPayment()) return;
            Order orderExist = orderService.findById(orderDto.getOrderId());
            if(orderExist == null){
                log.error("order not found");
                return;
            }
            if(orderDto.getQueueName().equals(QUEUE_PAY)){
                orderExist.setPaymentStatus(orderDto.getPaymentStatus());
            }
            if (orderDto.getQueueName().equals(QUEUE_INVENTORY)){
                orderExist.setInventoryStatus(orderDto.getInventoryStatus());
            }
            handlerOrder(orderExist.getId());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Transactional
    public void handlerOrder(int orderId){
        Order order = orderService.findById(orderId);
        try {
            if(order.getPaymentStatus().equals(PaymentStatus.PAID.name())
            && order.getInventoryStatus().equals(InventoryStatus.OUT_OF_STOCK.name())){
                order.setPaymentStatus(PaymentStatus.REFUNDED.name());
                rabbitTemplate.convertAndSend(DIRECT_EXCHANGE, DIRECT_ROUTING_KEY_PAY, new OrderDto(order));
                return;
            }
            if(order.getPaymentStatus().equals(PaymentStatus.FAIL.name())
            && order.getInventoryStatus().equals(InventoryStatus.DONE.name())){
                order.setInventoryStatus(InventoryStatus.RETURN.name());
                rabbitTemplate.convertAndSend(DIRECT_EXCHANGE, DIRECT_ROUTING_KEY_INVENTORY, new OrderDto(order));
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
