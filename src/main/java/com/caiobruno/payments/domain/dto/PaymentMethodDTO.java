package com.caiobruno.payments.domain.dto;

import com.caiobruno.payments.domain.enums.PaymentMethod;

public class PaymentMethodDTO {

    private  String id;
    private  String name;

    public PaymentMethodDTO(PaymentMethod paymentMethod) {
        this.id = paymentMethod.getId();
        this.name = paymentMethod.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
