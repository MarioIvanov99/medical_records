package com.hospital.medical_records.exception;

public class SickLeaveRecordNotFoundException extends ObjectNotFoundException {
    public SickLeaveRecordNotFoundException(String message, long id) {
        super(message, id);
    }
}
