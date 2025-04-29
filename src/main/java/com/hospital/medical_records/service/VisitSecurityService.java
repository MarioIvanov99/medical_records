package com.hospital.medical_records.service;

public interface VisitSecurityService {
    boolean isDoctorOwnerOfVisit(long visitId);
}
