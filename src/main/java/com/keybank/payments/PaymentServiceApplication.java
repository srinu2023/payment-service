package com.keybank.payments;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;

@SpringBootApplication
public class PaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}
@Bean
	public Map<String,Object> producerConfigs(){
		Map<String,Object> props=new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		//See https://kafka.apache.org/documentation/#properties for more properties
		return props;
	}
@Bean
public ProducerFactory<String,String> producerFactory(){
	return new DefaultKafkaProducerFactory<>(producerConfigs());
}
@Bean
public KafkaTemplate<String,String> kafkaTemplate(){
return new KafkaTemplate<String,String>(producerFactory());
}
}
