package com.busraciftlik.business.dto.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequest { // Base / super class
    @NotBlank(message = "Kart numarası boş bırakılamaz") // Boş bırakılamaz
    @Length(min = 16, max = 16, message = "Kart numarası 16 haneden oluşmak zorundadır")
    private String cardNumber;
    @NotBlank
    @Length(min = 5)
    private String cardHolder;

    @Min((2023))
    private int cardExpirationYear;

    @Min(value = 1)
    @Max(value = 12)
    private int cardExpirationMonth;

    @NotBlank
    @Length(min = 3, max = 3)
    private String cardCvv;
}
