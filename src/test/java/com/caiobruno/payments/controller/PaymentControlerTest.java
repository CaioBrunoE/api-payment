package com.caiobruno.payments.controller;


import com.caiobruno.payments.domain.dto.PaymentDTO;
import com.caiobruno.payments.domain.enums.PaymentMethod;
import com.caiobruno.payments.domain.enums.StatusPayment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;


@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentControlerTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private MockMvc mockMvc;

    @Autowired
    private PaymentControler controller;

    PaymentDTO paymentsDTO = new PaymentDTO("2","1",new BigDecimal("700.99"), PaymentMethod.TICKET.getId(), null, null ,StatusPayment.CANCELED.getId());


    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @Order(1)
    public  void testCreatePayment() throws Exception {
        log.info("testCreatePayment");
        this.mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(paymentsDTO))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    @Order(2)
    public void testFindAllPayment() throws Exception {
        log.info("testFindAllPayment");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/payments"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    @Order(3)
    public void testFindByIdPayment() throws Exception {
        log.info("testFindByIdPayment");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/payments/" + paymentsDTO.getId()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());;
    }
    @Test
    @Order(4)
    public void testUpdatePayment() throws Exception {
        log.info("testUpdatePayment");
        this.mockMvc.perform( MockMvcRequestBuilders
                        .put("/api/payments/" + paymentsDTO.getId())
                        .content(asJsonString(new PaymentDTO(null,"65f49a996c7faa406961ae8c",new BigDecimal("1699.99"),PaymentMethod.CARD.getId(), null, null,StatusPayment.PAID.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }



    @Test
    @Order(5)
    public void testStatusPayment() throws Exception {
        log.info("testFindAllPayment");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/payments/statuspayment/2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    @Order(6)
    public void testMethodPayment() throws Exception {
        log.info("testFindAllPayment");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/payments/methodpayment/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    @Order(7)
    public void testEnumsStatusPayment() throws Exception {
        log.info("testFindAllPayment");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/payments/liststauspayments"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    @Order(8)
    public void testEnumsPaymentMethod() throws Exception {
        log.info("testFindAllPayment");
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/payments/listpaymentMethod"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
    }

    @Test
    @Order(9)
    public void testDelete() throws Exception {
        log.info("testDeletePayment");
        this.mockMvc.perform( MockMvcRequestBuilders
                        .delete("/api/payments/" + paymentsDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
