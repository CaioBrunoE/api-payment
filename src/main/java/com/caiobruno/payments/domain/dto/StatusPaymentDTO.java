package com.caiobruno.payments.domain.dto;

import com.caiobruno.payments.domain.enums.StatusPayment;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusPaymentDTO {

    private  String id;
    private  String name;

    public StatusPaymentDTO(StatusPayment statusPayment) {
        this.id = statusPayment.getId();
        this.name = statusPayment.getName();
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
