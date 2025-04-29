package com.hospital.medical_records.service.impl;
import com.hospital.medical_records.data.entity.User;
import com.hospital.medical_records.data.entity.Visit;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.hospital.medical_records.data.repo.VisitRepository;
import com.hospital.medical_records.service.VisitSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("visitSecurityService")
@RequiredArgsConstructor
public class VisitSecurityServiceImpl implements VisitSecurityService {
    private final VisitRepository visitRepository;

    @Override
    public boolean isDoctorOwnerOfVisit(long visitId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof User)) {
            return false;
        }

        long currentDoctorId = ((User) principal).getId();
        Optional<Visit> visitOptional = visitRepository.findById(visitId);

        if (visitOptional.isEmpty()) {
            return false;
        }


        Visit visit = visitOptional.get();

        Long visitDoctorId = (visit.getDoctor() != null) ? visit.getDoctor().getId() : null;

        return visitDoctorId != null && visitDoctorId.equals(currentDoctorId);
    }
}
