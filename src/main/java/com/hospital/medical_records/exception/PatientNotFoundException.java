package com.hospital.medical_records.exception;

public class PatientNotFoundException extends ObjectNotFoundException {
    public PatientNotFoundException(String message, long id) {
        super(message, id);
    }
}
