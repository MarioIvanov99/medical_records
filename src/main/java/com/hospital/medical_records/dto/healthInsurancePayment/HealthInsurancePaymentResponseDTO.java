package com.hospital.medical_records.dto.healthInsurancePayment;

import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class HealthInsurancePaymentResponseDTO {
    @PastOrPresent
    private LocalDate paymentDate;
}
