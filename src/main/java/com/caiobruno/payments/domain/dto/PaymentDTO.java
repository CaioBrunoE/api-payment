package com.caiobruno.payments.domain.dto;

import com.caiobruno.payments.domain.model.Payment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaymentDTO {

    private String id;

    @NotBlank
    private String idUser;

    @NotNull
    private BigDecimal value;

    private String paymentMethod;

    private String created;
    private String updated;

    private String status;

    public PaymentDTO (Payment entity){
        BeanUtils.copyProperties(entity, this);
    }

}
