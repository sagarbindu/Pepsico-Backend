package com.casestudy.authentication.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.casestudy.authentication.model.User;
import com.casestudy.authentication.service.UserService;


@Service
public class ProducerKafka {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProducerKafka.class);

	private final KafkaTemplate<String, User> kafkaTemplate;
	
	@Autowired
	UserService userService;
	private static final String TOPIC = "kafka_json";

	
	
	  public ProducerKafka(KafkaTemplate<String, User> kafkaTemplate) {
	        this.kafkaTemplate = kafkaTemplate;
	    }

		/*
		 * public void sendMessage(User data) {
		 * 
		 * LOGGER.info(String.format("Message set ->%s", data.toString()));
		 * Message<User> message =
		 * MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC,
		 * "kafka_json").build(); kafkaTemplate.send(TOPIC, message); }
		 */
	  public void sendMessage(User user) {
	        kafkaTemplate.send(TOPIC, user);
	    }

}
