package com.hospital.medical_records.exception;

public class TreatmentNotFoundException extends ObjectNotFoundException {
    public TreatmentNotFoundException(String message, long id) {
        super(message, id);
    }
}
