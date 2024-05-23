package com.caiobruno.payments.repository;

import com.caiobruno.payments.domain.enums.StatusPayment;
import com.caiobruno.payments.domain.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, String> {
    List<Payment> findByStatus(String code);

    List<Payment> findByPaymentMethod(String code);
}
