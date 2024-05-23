package com.caiobruno.payments.exceptions;

public class PaymentNotFoundException extends RuntimeException {
    public PaymentNotFoundException(String message){
        super(message);
    }
}
