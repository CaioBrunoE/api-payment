package com.caiobruno.payments.config;

import com.caiobruno.payments.domain.model.Payment;
import com.caiobruno.payments.repository.PaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private  PaymentRepository repository;
    @Autowired
    private  ResourceLoader resourceLoader;
    
    @Override
    public void run(String... args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Resource resource = resourceLoader.getResource("classpath:payments.json");
        try (InputStream inputStream = resource.getInputStream()) {
            Payment[] payments = mapper.readValue(inputStream, Payment[].class);

            for (Payment payment : payments) {
                repository.save(payment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}