package com.casestudy.authentication.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.casestudy.authentication.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

 // Import Jackson ObjectMapper
import com.fasterxml.jackson.core.JsonProcessingException; 

@Service
public class ConsumerKafka {

    private final ObjectMapper objectMapper; // Jackson ObjectMapper
    public ConsumerKafka(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
	
	@KafkaListener(topics = "kafka_json", groupId = "myGroup")
    public void consume(User user) {
		try {
            // Convert User object to JSON
            String userJson = objectMapper.writeValueAsString(user);
            
            // Print the JSON
            System.out.println("Received User JSON: " + userJson);
            
            // Process the received user object (e.g., save it to the database)
        } catch (JsonProcessingException e) {
            // Handle the exception if JSON conversion fails
            e.printStackTrace();
        }
    }
}
