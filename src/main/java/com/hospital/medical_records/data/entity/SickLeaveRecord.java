package com.hospital.medical_records.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "sick_leave_records")
public class SickLeaveRecord extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    private Visit visit;

    @Column(nullable = false)
    @PastOrPresent
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @AssertTrue(message = "End date must be after start date")
    public boolean isEndDateValid() {
        return endDate == null || startDate == null || endDate.isAfter(startDate);
    }
}
