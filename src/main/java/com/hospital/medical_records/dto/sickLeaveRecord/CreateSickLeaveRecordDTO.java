package com.hospital.medical_records.dto.sickLeaveRecord;

import jakarta.validation.constraints.AssertTrue;
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
public class CreateSickLeaveRecordDTO {
    @NotNull
    private Long visitId;

    @NotNull
    @PastOrPresent
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @AssertTrue(message = "End date must be strictly after start date")
    public boolean isEndDateValid() {
        return startDate != null && endDate != null && endDate.isAfter(startDate);
    }
}
