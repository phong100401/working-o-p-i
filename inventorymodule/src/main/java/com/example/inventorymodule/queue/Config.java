package com.example.inventorymodule.queue;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.amqp.core.BindingBuilder.bind;

@Configuration
public class Config {
    public static final String DIRECT_EXCHANGE = "direct.exchange";

    public static final String QUEUE_ORDER = "direct.queue.order";
    public static final String QUEUE_PAY = "direct.queue.pay";
    public static final String QUEUE_INVENTORY = "direct.queue.inventory";



    public static final String DIRECT_ROUTING_KEY_ORDER = "direct.routingKeyOrder";
    public static final String DIRECT_ROUTING_KEY_PAY = "direct.routingKeyPay";
    public static final String DIRECT_ROUTING_KEY_INVENTORY = "routingKeyInventory";
    public static final String DIRECT_SHARE_ROUTING_KEY = "routingKeyShare";


    @Bean
    public Declarables binding() {
        Queue directQueueOrder = new Queue(QUEUE_ORDER);
        Queue directQueuePay = new Queue(QUEUE_PAY);
        Queue directQueueInventory = new Queue(QUEUE_INVENTORY);
        DirectExchange directExchange = new DirectExchange(DIRECT_EXCHANGE);
        return new Declarables(
                directQueueOrder,
                directQueuePay,
                directQueueInventory,
                directExchange,
                bind(directQueueOrder).to(directExchange).with(DIRECT_ROUTING_KEY_ORDER),

                bind(directQueuePay).to(directExchange).with(DIRECT_ROUTING_KEY_PAY),
                bind(directQueueInventory).to(directExchange).with(DIRECT_ROUTING_KEY_INVENTORY),

                bind(directQueuePay).to(directExchange).with(DIRECT_SHARE_ROUTING_KEY),
                bind(directQueueInventory).to(directExchange).with(DIRECT_SHARE_ROUTING_KEY)
        );
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
