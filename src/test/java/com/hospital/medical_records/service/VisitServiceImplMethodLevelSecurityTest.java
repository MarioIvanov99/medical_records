package com.hospital.medical_records.service;

import com.hospital.medical_records.data.entity.Visit;
import com.hospital.medical_records.data.mapper.VisitMapper;
import com.hospital.medical_records.data.repo.VisitRepository;
import com.hospital.medical_records.dto.visit.UpdateVisitDiagnosisDTO;
import com.hospital.medical_records.dto.visit.VisitResponseDTO;
import com.hospital.medical_records.service.impl.VisitSecurityServiceImpl;
import com.hospital.medical_records.service.impl.VisitServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class VisitServiceImplMethodLevelSecurityTest {
    @MockitoBean
    VisitRepository visitRepository;

    @MockitoBean
    VisitMapper visitMapper;

    @MockitoBean
    VisitSecurityServiceImpl visitSecurityService;

    @Autowired
    VisitServiceImpl visitService;

    @Test
    @WithAnonymousUser
    void testGetVisitsThrowsAccessDeniedWhenNotAuthorized() {
        assertThrows(AuthorizationDeniedException.class, () -> visitService.getVisits());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetVisitsGetsCorrectNumberOfVisitsWithAuthorizedUser() {
        List<Visit> visits = new ArrayList<>();
        List<VisitResponseDTO> visitResponseDTO = new ArrayList<>();

        when(visitRepository.findAll()).thenReturn(visits);
        when(visitMapper.toResponseDtoList(visits)).thenReturn(visitResponseDTO);

        assertEquals(0, visitService.getVisits().size());
        assertDoesNotThrow(() -> visitService.getVisits());

        verify(visitRepository, times(2)).findAll();
        verify(visitMapper, times(2)).toResponseDtoList(visits);
    }

    @Test
    void testGetVisitsThrowsExceptionWhenUserIsNotAuthenticated() {
        assertThrows(AuthenticationCredentialsNotFoundException.class, () -> visitService.getVisitsInPeriod(null, null));
    }

    @Test
    @WithMockUser(roles = "DOCTOR")
    void testUpdateVisitAsDoctorThrowsAccessDeniedException() {
        long visitId = 1L;
        UpdateVisitDiagnosisDTO dto = new UpdateVisitDiagnosisDTO();

        when(visitSecurityService.isDoctorOwnerOfVisit(visitId)).thenReturn(false);

        assertThrows(AuthorizationDeniedException.class, () -> visitService.updateVisit(visitId, dto));

        verify(visitSecurityService).isDoctorOwnerOfVisit(visitId);
    }

    @Test
    @WithMockUser(roles = "DOCTOR")
    void testUpdateVisitAsDoctorUpdatesVisit() {
        long visitId = 1L;
        UpdateVisitDiagnosisDTO dto = new UpdateVisitDiagnosisDTO();
        Visit existingVisit = new Visit();

        when(visitSecurityService.isDoctorOwnerOfVisit(visitId)).thenReturn(true);
        when(visitRepository.findById(visitId)).thenReturn(Optional.of(existingVisit));
        when(visitRepository.save(existingVisit)).thenReturn(existingVisit);

        UpdateVisitDiagnosisDTO result = visitService.updateVisit(visitId, dto);

        assertNotNull(result);
        assertEquals(dto, result);

        verify(visitSecurityService).isDoctorOwnerOfVisit(visitId);
        verify(visitMapper).updateFromDto(dto, existingVisit);
        verify(visitRepository).save(existingVisit);
    }
}
