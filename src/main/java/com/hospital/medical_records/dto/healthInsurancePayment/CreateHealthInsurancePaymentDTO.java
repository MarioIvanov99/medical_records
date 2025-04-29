package com.hospital.medical_records.dto.healthInsurancePayment;

import com.hospital.medical_records.data.entity.Patient;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CreateHealthInsurancePaymentDTO {
    @PastOrPresent
    private LocalDate paymentDate;

    private long patientId;
}
