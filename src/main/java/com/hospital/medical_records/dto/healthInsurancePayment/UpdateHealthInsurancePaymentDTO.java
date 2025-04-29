package com.hospital.medical_records.dto.healthInsurancePayment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UpdateHealthInsurancePaymentDTO {
    @NotNull
    @PastOrPresent
    private LocalDate paymentDate;
}
