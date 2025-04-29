package com.hospital.medical_records.dto.sickLeaveRecord;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UpdateSickLeaveRecordDTO {
    @PastOrPresent
    private LocalDate startDate;

    private LocalDate endDate;
    @AssertTrue(message = "If both start and end dates are provided in the update, end date must be after start date")
    public boolean isEndDateValidIfBothPresent() {
        if (startDate != null && endDate != null) {
            return endDate.isAfter(startDate);
        }
        return true;
    }
}
