package com.hospital.medical_records.exception;

public class AfflictionNotFoundException extends ObjectNotFoundException {
    public AfflictionNotFoundException(String message, long id) {
        super(message, id);
    }
}
