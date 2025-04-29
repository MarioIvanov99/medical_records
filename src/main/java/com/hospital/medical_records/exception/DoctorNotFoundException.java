package com.hospital.medical_records.exception;

public class DoctorNotFoundException extends ObjectNotFoundException {
    public DoctorNotFoundException(String message, long id) {
        super(message, id);
    }
}
