package com.hospital.medical_records.service;

import com.hospital.medical_records.data.entity.Visit;
import com.hospital.medical_records.data.mapper.VisitMapper;
import com.hospital.medical_records.data.repo.VisitRepository;
import com.hospital.medical_records.dto.visit.*;
import com.hospital.medical_records.exception.InvalidDateRangeException;
import com.hospital.medical_records.exception.VisitNotFoundException;
import com.hospital.medical_records.service.impl.VisitServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Configuration
class VisitServiceImplTest {
    @Mock
    VisitRepository visitRepository;

    @Mock
    VisitMapper visitMapper;

    @InjectMocks
    VisitServiceImpl visitService;

    @Test
    void testGetVisitsReturnsAllVisitsReturnsCorrectNumberOfVisits() {
        List<Visit> visits = new ArrayList<>();
        List<VisitResponseDTO> visitResponseDTO = new ArrayList<>();

        when(visitRepository.findAll()).thenReturn(visits);
        when(visitMapper.toResponseDtoList(visits)).thenReturn(visitResponseDTO);

        assertEquals(0, visitService.getVisits().size());

        verify(visitRepository).findAll();
        verify(visitMapper).toResponseDtoList(visits);
    }

    @Test
    void testGetVisitsByPatientIdReturnsCorrectNumberOfVisits() {
        List<Visit> visits = new ArrayList<>();
        List<VisitPatientResponseDTO> visitResponseDTO = new ArrayList<>();

        when(visitRepository.findByPatient_IdOrderByVisitDateDesc(1L)).thenReturn(visits);
        when(visitMapper.toPatientResponseDtoList(visits)).thenReturn(visitResponseDTO);

        assertEquals(0, visitService.getVisitsByPatientId(1L).size());

        verify(visitRepository).findByPatient_IdOrderByVisitDateDesc(1L);
        verify(visitMapper).toPatientResponseDtoList(visits);
    }

    @Test
    void testGetTreatmentsByPatientIdReturnsCorrectNumberOfTreatments() {
        List<Visit> visits = new ArrayList<>();
        List<VisitPatientTreatmentResponseDTO> visitResponseDTO = new ArrayList<>();

        when(visitRepository.findDistinctByPatient_IdAndTreatmentsIsNotEmptyOrderByVisitDateDesc(1L)).thenReturn(visits);
        when(visitMapper.toPatientTreatmentResponseDtoList(visits)).thenReturn(visitResponseDTO);

        assertEquals(0, visitService.getTreatmentsByPatientId(1L).size());

        verify(visitRepository).findDistinctByPatient_IdAndTreatmentsIsNotEmptyOrderByVisitDateDesc(1L);
        verify(visitMapper).toPatientTreatmentResponseDtoList(visits);
    }

    @Test
    void testGetDignosesByPatientIdReturnsCorrectNumberOfDiagnoses() {
        List<Visit> visits = new ArrayList<>();
        List<VisitPatientDiagnosisResponseDTO> visitResponseDTO = new ArrayList<>();

        when(visitRepository.findDistinctByPatient_IdAndDiagnosesIsNotEmptyOrderByVisitDateDesc(1L)).thenReturn(visits);
        when(visitMapper.toPatientDiagnosisResponseDtoList(visits)).thenReturn(visitResponseDTO);

        assertEquals(0, visitService.getDiagnosesByPatientId(1L).size());

        verify(visitRepository).findDistinctByPatient_IdAndDiagnosesIsNotEmptyOrderByVisitDateDesc(1L);
        verify(visitMapper).toPatientDiagnosisResponseDtoList(visits);
    }

    @Test
    void testGetVisitsByDoctorIdReturnsCorrectNumberOfVisits() {
        List<Visit> visits = new ArrayList<>();
        List<VisitDoctorResponseDTO> visitResponseDTO = new ArrayList<>();

        when(visitRepository.findByDoctor_IdOrderByVisitDateDesc(1L)).thenReturn(visits);
        when(visitMapper.toDoctorResponseDtoList(visits)).thenReturn(visitResponseDTO);

        assertEquals(0, visitService.getVisitsByDoctorId(1L).size());

        verify(visitRepository).findByDoctor_IdOrderByVisitDateDesc(1L);
        verify(visitMapper).toDoctorResponseDtoList(visits);
    }

    @Test
    void testGetTreatmentsByDoctorIdReturnsCorrectNumberOfTreatments() {
        List<Visit> visits = new ArrayList<>();
        List<VisitDoctorTreatmentResponseDTO> visitResponseDTO = new ArrayList<>();

        when(visitRepository.findDistinctByDoctor_IdAndTreatmentsIsNotEmptyOrderByVisitDateDesc(1L)).thenReturn(visits);
        when(visitMapper.toDoctorTreatmentResponseDtoList(visits)).thenReturn(visitResponseDTO);

        assertEquals(0, visitService.getTreatmentsByDoctorId(1L).size());

        verify(visitRepository).findDistinctByDoctor_IdAndTreatmentsIsNotEmptyOrderByVisitDateDesc(1L);
        verify(visitMapper).toDoctorTreatmentResponseDtoList(visits);
    }

    @Test
    void testGetTreatmentsByDoctorNameReturnsCorrectNumberOfVisits() {
        List<Visit> visits = new ArrayList<>();
        List<VisitDoctorTreatmentResponseDTO> visitResponseDTO = new ArrayList<>();

        when(visitRepository.findDistinctByDoctor_NameAndTreatmentsIsNotEmptyOrderByVisitDateDesc("name")).thenReturn(visits);
        when(visitMapper.toDoctorTreatmentResponseDtoList(visits)).thenReturn(visitResponseDTO);

        assertEquals(0, visitService.getTreatmentsByDoctorName("name").size());

        verify(visitRepository).findDistinctByDoctor_NameAndTreatmentsIsNotEmptyOrderByVisitDateDesc("name");
        verify(visitMapper).toDoctorTreatmentResponseDtoList(visits);
    }

    @Test
    void testGetDiagnosesByDoctorIdReturnsCorrectNumberOfDiagnoses() {
        List<Visit> visits = new ArrayList<>();
        List<VisitDoctorDiagnosisResponseDTO> visitResponseDTO = new ArrayList<>();

        when(visitRepository.findDistinctByDoctor_IdAndDiagnosesIsNotEmptyOrderByVisitDateDesc(1L)).thenReturn(visits);
        when(visitMapper.toDoctorDiagnosisResponseDtoList(visits)).thenReturn(visitResponseDTO);

        assertEquals(0, visitService.getDiagnosesByDoctorId(1L).size());

        verify(visitRepository).findDistinctByDoctor_IdAndDiagnosesIsNotEmptyOrderByVisitDateDesc(1L);
        verify(visitMapper).toDoctorDiagnosisResponseDtoList(visits);
    }

    @Test
    void testGetDiagnosesByDoctorNameReturnsCorrectNumberOfVisits() {
        List<Visit> visits = new ArrayList<>();
        List<VisitDoctorDiagnosisResponseDTO> visitResponseDTO = new ArrayList<>();

        when(visitRepository.findDistinctByDoctor_NameAndDiagnosesIsNotEmptyOrderByVisitDateDesc("name")).thenReturn(visits);
        when(visitMapper.toDoctorDiagnosisResponseDtoList(visits)).thenReturn(visitResponseDTO);

        assertEquals(0, visitService.getDiagnosesByDoctorName("name").size());

        verify(visitRepository).findDistinctByDoctor_NameAndDiagnosesIsNotEmptyOrderByVisitDateDesc("name");
        verify(visitMapper).toDoctorDiagnosisResponseDtoList(visits);
    }

    @Test
    void testGetVisitEntityByIdThrowsVisitNotFoundExceptionWhenVisitDoesNotExist() {
        when(visitRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(VisitNotFoundException.class, () -> visitService.getVisitEntityById(1L));

        verify(visitRepository).findById(1L);
    }

    @Test
    void testGetVisitEntityByIdReturnsVisitWhenVisitExists() {
        Visit visit = new Visit();
        when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));

        Visit result = visitService.getVisitEntityById(1L);

        assertEquals(visit, result);

        verify(visitRepository).findById(1L);
    }

    @Test
    void testGetVisitsInPeriodThrowsExceptionWhenPeriodIsInvalid() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().minusDays(1);

        assertThrows(InvalidDateRangeException.class, () -> visitService.getVisitsInPeriod(startDate, endDate));
    }

    @Test
    void testGetVisitsInPeriodReturnsCorrectNumberOfVisits() {
        List<Visit> visits = new ArrayList<>();
        List<VisitResponseDTO> visitResponseDTO = new ArrayList<>();

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(1);

        when(visitRepository.findByVisitDateBetweenOrderByVisitDateDesc(startDate, endDate)).thenReturn(visits);
        when(visitMapper.toResponseDtoList(visits)).thenReturn(visitResponseDTO);

        assertEquals(0, visitService.getVisitsInPeriod(startDate, endDate).size());

        verify(visitRepository).findByVisitDateBetweenOrderByVisitDateDesc(startDate, endDate);
        verify(visitMapper).toResponseDtoList(visits);
    }

    @Test
    void testGetVisitsInPeriodByDoctorIdThrowsExceptionWhenPeriodIsInvalid() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().minusDays(1);

        assertThrows(InvalidDateRangeException.class, () -> visitService.getVisitsInPeriodByDoctorId(1L, startDate, endDate));
    }

    @Test
    void testGetVisitsInPeriodByDoctorIdReturnsCorrectNumberOfVisits() {
        List<Visit> visits = new ArrayList<>();
        List<VisitDoctorResponseDTO> visitResponseDTO = new ArrayList<>();

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(1);

        when(visitRepository.findDistinctByDoctor_IdAndVisitDateBetweenOrderByVisitDateDesc(1L, startDate, endDate)).thenReturn(visits);
        when(visitMapper.toDoctorResponseDtoList(visits)).thenReturn(visitResponseDTO);

        assertEquals(0, visitService.getVisitsInPeriodByDoctorId(1L, startDate, endDate).size());

        verify(visitRepository).findDistinctByDoctor_IdAndVisitDateBetweenOrderByVisitDateDesc(1L, startDate, endDate);
        verify(visitMapper).toDoctorResponseDtoList(visits);
    }

    @Test
    void testGetVisitsInPeriodByDoctorNameThrowsExceptionWhenPeriodIsInvalid() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().minusDays(1);

        assertThrows(InvalidDateRangeException.class, () -> visitService.getVisitsInPeriodByDoctorName("name", startDate, endDate));
    }

    @Test
    void testGetVisitsInPeriodByDoctorNameReturnsCorrectNumberOfVisits() {
        List<Visit> visits = new ArrayList<>();
        List<VisitDoctorResponseDTO> visitResponseDTO = new ArrayList<>();

        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(1);

        when(visitRepository.findDistinctByDoctor_NameAndVisitDateBetweenOrderByVisitDateDesc("name", startDate, endDate)).thenReturn(visits);
        when(visitMapper.toDoctorResponseDtoList(visits)).thenReturn(visitResponseDTO);

        assertEquals(0, visitService.getVisitsInPeriodByDoctorName("name", startDate, endDate).size());

        verify(visitRepository).findDistinctByDoctor_NameAndVisitDateBetweenOrderByVisitDateDesc("name", startDate, endDate);
        verify(visitMapper).toDoctorResponseDtoList(visits);
    }

    @Test
    void testCreateVisitDTOCorrectly() {
        CreateVisitDTO dto = new CreateVisitDTO();
        Visit visit = new Visit();
        List<Visit> visitList = new ArrayList<Visit>();

        when(visitMapper.toEntity(dto)).thenReturn(visit);
        when(visitRepository.save(visit)).thenAnswer(invocation -> {
            Visit savedVisit = invocation.getArgument(0);
            visitList.add(savedVisit);
            return savedVisit;
        });

        CreateVisitDTO result = visitService.createVisit(dto);

        assertEquals(1, visitList.size());
        assertNotNull(result);

        verify(visitMapper).toEntity(dto);
        verify(visitRepository).save(visit);
    }

    @Test
    void testUpdateVisitTreatmentThrowsVisitNotFoundException() {
        UpdateVisitTreatmentDTO dto = new UpdateVisitTreatmentDTO();

        when(visitRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(VisitNotFoundException.class, () -> visitService.updateVisit(1L, dto));

        verify(visitRepository).findById(1L);
    }

    @Test
    void testUpdateVisitTreatmentUpdatesVisit() {
        long visitId = 1L;
        UpdateVisitTreatmentDTO dto = new UpdateVisitTreatmentDTO();
        Visit existingVisit = new Visit();

        when(visitRepository.findById(visitId)).thenReturn(Optional.of(existingVisit));
        when(visitRepository.save(existingVisit)).thenReturn(existingVisit);

        UpdateVisitTreatmentDTO result = visitService.updateVisit(visitId, dto);

        assertNotNull(result);
        assertEquals(dto, result);

        verify(visitMapper).updateFromDto(dto, existingVisit);
        verify(visitRepository).save(existingVisit);
    }

    @Test
    void testUpdateVisitDiagnosisThrowsVisitNotFoundException() {
        UpdateVisitDiagnosisDTO dto = new UpdateVisitDiagnosisDTO();

        when(visitRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(VisitNotFoundException.class, () -> visitService.updateVisit(1L, dto));

        verify(visitRepository).findById(1L);
    }

    @Test
    void testUpdateVisitDiagnosisUpdatesVisit() {
        long visitId = 1L;
        UpdateVisitDiagnosisDTO dto = new UpdateVisitDiagnosisDTO();
        Visit existingVisit = new Visit();

        when(visitRepository.findById(visitId)).thenReturn(Optional.of(existingVisit));
        when(visitRepository.save(existingVisit)).thenReturn(existingVisit);

        UpdateVisitDiagnosisDTO result = visitService.updateVisit(visitId, dto);

        assertNotNull(result);
        assertEquals(dto, result);
        verify(visitMapper).updateFromDto(dto, existingVisit);
        verify(visitRepository).save(existingVisit);
    }

    @Test
    void testDeleteVisitThrowsVisitNotFoundException() {
        when(visitRepository.existsById(1L)).thenReturn(false);

        assertThrows(VisitNotFoundException.class, () -> visitService.deleteVisit(1L));

        verify(visitRepository).existsById(1L);
    }

    @Test
    void testDeleteVisitDeletesVisit() {
        List<Visit> visitList = new ArrayList<Visit>();
        visitList.add(new Visit());

        when(visitRepository.existsById(1L)).thenReturn(true);
        Mockito.doAnswer(invocation -> visitList.remove(0)).when(visitRepository).deleteById(1L);

        visitService.deleteVisit(1L);

        assertEquals(0, visitList.size());

        verify(visitRepository).existsById(1L);
        verify(visitRepository).deleteById(1L);
    }
}