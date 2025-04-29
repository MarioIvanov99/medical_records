package com.hospital.medical_records.exception;

public class DiagnosisNotFoundException extends ObjectNotFoundException {
    public DiagnosisNotFoundException(String message, long id) {
        super(message, id);
    }
}
