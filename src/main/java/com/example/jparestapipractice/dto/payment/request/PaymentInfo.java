package com.example.jparestapipractice.dto.payment.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentInfo {

    @NotBlank
    private String cardNumber; // 카드번호
    @NotBlank
    private String expirationDate; //유효 기간(MM/YY)
    @NotBlank
    private String cvv; //보안코드
    @NotBlank
    private String cardHolderName; //카드소유자 이름
    @NotNull
    private Long amount; //결제금액
    @NotBlank
    private String currency; //통화
    @NotBlank
    private String paymentMethod; // 결제수단

}
