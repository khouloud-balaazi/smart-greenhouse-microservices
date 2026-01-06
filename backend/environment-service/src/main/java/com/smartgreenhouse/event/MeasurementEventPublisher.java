package com.smartgreenhouse.event;

import com.smartgreenhouse.entities.Mesure;

public interface MeasurementEventPublisher {

	void publish(Mesure mesure, boolean alert);
}
