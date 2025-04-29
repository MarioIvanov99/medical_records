package com.hospital.medical_records.exception;

public class SpecialtyNotFoundException extends ObjectNotFoundException {
    public SpecialtyNotFoundException(String message, long id) {
        super(message, id);
    }
}
