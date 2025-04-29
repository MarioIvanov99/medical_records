package com.hospital.medical_records.exception;

public class VisitNotFoundException extends ObjectNotFoundException {
    public VisitNotFoundException(String message, long id) {
        super(message, id);
    }
}
