package com.smartgreenhouse.event;


import org.springframework.amqp.core.Queue;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
	

    @Bean
    public TopicExchange environmentExchange() {
        return new TopicExchange(RabbitMQConstants.EXCHANGE);
    }

    @Bean
    public Queue environmentQueue() {
        return new Queue(RabbitMQConstants.QUEUE, true);
    }

    @Bean
    public Binding environmentBinding(
            Queue environmentQueue,
            TopicExchange environmentExchange) {

        return BindingBuilder
                .bind(environmentQueue)
                .to(environmentExchange)
                .with(RabbitMQConstants.ROUTING_KEY);
    }
}


