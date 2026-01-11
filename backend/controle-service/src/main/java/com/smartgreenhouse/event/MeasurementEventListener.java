package com.smartgreenhouse.event;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.smartgreenhouse.dto.MeasureEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MeasurementEventListener {

	  @RabbitListener(queues = RabbitMQConstants.QUEUE)
    public void consume(MeasureEvent event) {

        log.info("📥 EVENT RABBITMQ REÇU");
        log.info("Type = {}", event.getType());
        log.info("Valeur = {}", event.getValeur());
        log.info("Alerte = {}", event.isAlert());

        if (event.isAlert()) {
            log.warn("🚨 ACTION DE CONTRÔLE À DÉCLENCHER");
        } else {
            log.info("✅ Aucune action nécessaire");
        }
    }
}
