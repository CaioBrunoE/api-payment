package com.caiobruno.payments.service;

import com.caiobruno.payments.domain.dto.PaymentDTO;
import com.caiobruno.payments.domain.dto.PaymentMethodDTO;
import com.caiobruno.payments.domain.dto.StatusPaymentDTO;
import com.caiobruno.payments.domain.enums.PaymentMethod;
import com.caiobruno.payments.domain.enums.StatusPayment;
import com.caiobruno.payments.domain.model.Payment;
import com.caiobruno.payments.exceptions.PaymentNotFoundException;
import com.caiobruno.payments.exceptions.ServiceException;
import com.caiobruno.payments.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository repository;

    @Transactional
    public PaymentDTO created(Payment entity) throws ServiceException {
        entity.setCreated(LocalDateTime.now().toString());
        Payment payment =  repository.save(entity);
        return new PaymentDTO(payment);
    }
    public List<PaymentDTO> findAll() throws ServiceException {
        List<Payment> list = repository.findAll();
        if (list.isEmpty()){

            throw new PaymentNotFoundException("Error ");
        }else {
            return list.stream().map(PaymentDTO::new).toList();
        }

    }

    public PaymentDTO findById(String id) throws ServiceException {
        return repository.findById(id)
                .map(PaymentDTO::new)
                .orElseThrow(() -> new PaymentNotFoundException("Client not found with ID: " + id));
    }

    @Transactional
    public PaymentDTO update(String id, PaymentDTO paymentDTO) throws ServiceException{

        Payment entity = repository.findById(id).get();

        entity.setIdUser(paymentDTO.getIdUser());
        entity.setValue(paymentDTO.getValue());
        entity.setPaymentMethod(paymentDTO.getPaymentMethod());
        entity.setUpdated(LocalDateTime.now().toString());
        entity.setStatus(paymentDTO.getStatus());
        repository.save(entity);
        return new PaymentDTO(entity);

    }
    public void delete(String id) throws ServiceException{
        if (!repository.existsById(id)) {
            throw new PaymentNotFoundException("Payment not found with ID: " + id);
        }
        repository.deleteById(id);
    }
    public List<PaymentDTO> findByStatus(String code) throws ServiceException{
        List<Payment> list = repository.findByStatus(code);
        if (list.isEmpty()) {
            throw new PaymentNotFoundException("Status not found!");
        }
        return list.stream().map(PaymentDTO::new).toList();
    }

    public List<PaymentDTO> findByMethod(String code) throws ServiceException {
        List<Payment> list = repository.findByPaymentMethod(code);
        if (list.isEmpty()) {
            throw new PaymentNotFoundException("Method not found!");
        }
        return list.stream().map(PaymentDTO::new).toList();
    }

    public List<StatusPaymentDTO> listStatusPayment() throws ServiceException{
        return Arrays.stream(StatusPayment.values())
                .map(StatusPaymentDTO::new)
                .collect(Collectors.toList());
    }

    public List<PaymentMethodDTO> listMethodPayment() throws ServiceException{
        return Arrays.stream(PaymentMethod.values())
                .map(PaymentMethodDTO::new)
                .collect(Collectors.toList());
    }
}
