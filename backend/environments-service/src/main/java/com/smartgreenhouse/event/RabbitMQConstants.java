package com.smartgreenhouse.event;

public final class RabbitMQConstants {

	private RabbitMQConstants() {
        // pour empêcher l’instanciation
    }
//Éviter la duplication et Faciliter la maintenance
public static final String EXCHANGE = "environment.exchange";
public static final String QUEUE = "environment.queue";
public static final String ROUTING_KEY = "environment.measurement";
}