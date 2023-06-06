package com.keybank.payments.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.keybank.payments.model.PaymentStatus;

@Component
public class KafkaMessagePublisher {
@Autowired
KafkaTemplate<String, Object> kafkaTemplate;

public void pushPaymentStatus(PaymentStatus status) {
	// TODO: write the kafka Publisher code to push the message into  kafka
	
	kafkaTemplate.send("",null);
	
}
}
