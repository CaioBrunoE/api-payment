package com.caiobruno.payments.domain.enums;

public enum PaymentMethod {

    CARD("1", "Cartão de crédito"),
    PIX("2", "PIX"),
    TICKET("3", "Boleto"),
    CASH("4", "Dinheiro");



    private  String id;
    private  String name;

    PaymentMethod(String id, String name) {
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
