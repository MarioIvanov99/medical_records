package com.hospital.medical_records.exception;

public class HealthInsurancePaymentNotFoundException extends ObjectNotFoundException {
    public HealthInsurancePaymentNotFoundException(String message, long id) {
        super(message, id);
    }
}
