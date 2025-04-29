package com.hospital.medical_records.dto.sickLeaveRecord;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SickLeaveRecordMonthCountDTO {
    private int year;
    private int month;
    private long count;
}
