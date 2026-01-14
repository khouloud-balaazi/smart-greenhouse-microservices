package com.smartgreenhouse.event;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Component;

import com.smartgreenhouse.dto.MeasureEvent;
import com.smartgreenhouse.entities.Mesure;
import com.smartgreenhouse.entities.Parametre;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RabbitMeasurementEventPublisher
        implements MeasurementEventPublisher {

    private final RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
    }

    @Override
    public void publish(Mesure mesure, boolean alert) {

        Parametre parametre = mesure.getParametre();

        MeasureEvent event = new MeasureEvent();
        event.setType(parametre.getType());
        event.setValeur(mesure.getValeur());
        event.setSeuilMin(parametre.getSeuilMin());
        event.setSeuilMax(parametre.getSeuilMax());
        event.setAlert(alert);
        event.setTimestamp(LocalDateTime.now());

        rabbitTemplate.convertAndSend(
                RabbitMQConstants.EXCHANGE,
                RabbitMQConstants.ROUTING_KEY,
                event
        );
    }
}

