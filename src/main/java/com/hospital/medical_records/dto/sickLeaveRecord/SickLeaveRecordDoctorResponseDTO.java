package com.hospital.medical_records.dto.sickLeaveRecord;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SickLeaveRecordDoctorResponseDTO {
    @NotBlank
    @Size(min = 5, max = 45)
    private String patientName;

    @PastOrPresent
    private LocalDate startDate;
    private LocalDate endDate;

    private LocalDate visitDate;

    @AssertTrue(message = "Start date must be after visit date")
    public boolean isStartDateValid() {
        return endDate == null || startDate == null || startDate.isAfter(visitDate);
    }
    @AssertTrue(message = "End date must be after start date")
    public boolean isEndDateValid() {
        return endDate == null || startDate == null || endDate.isAfter(startDate);
    }
}