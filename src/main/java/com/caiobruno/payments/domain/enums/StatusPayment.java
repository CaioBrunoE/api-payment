package com.caiobruno.payments.domain.enums;

public enum StatusPayment {
    WAITING_PAYMENT("1", "Aguardando pagamento"),
    PAID("2", "Pago"),
    CANCELED("3", "Cancelado");

    private  String id;
    private  String name;

    StatusPayment(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}