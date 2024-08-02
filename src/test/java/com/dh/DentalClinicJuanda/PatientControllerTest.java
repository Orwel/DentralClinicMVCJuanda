package com.dh.DentalClinicJuanda;



import com.dh.DentalClinicJuanda.controller.PatientController;
import com.dh.DentalClinicJuanda.entity.Patient;
import com.dh.DentalClinicJuanda.service.IPatientService;
import com.dh.DentalClinicJuanda.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPatientService patientService;

    private Patient patient1;
    private Patient patient2;

    @BeforeEach
    void setUp() {
        patient1 = new Patient(1L, "John", "Doe", "john@example.com", 123456789, LocalDate.now(), null);
        patient2 = new Patient(2L, "Jane", "Doe", "jane@example.com", 987654321, LocalDate.now(), null);
    }

    @Test
    public void testFindAll() throws Exception {
        List<Patient> patients = Arrays.asList(patient1, patient2);
        Mockito.when(patientService.findAll()).thenReturn(patients);

        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(patients.size())))
                .andExpect(jsonPath("$[0].id", is(patient1.getId().intValue())))
                .andExpect(jsonPath("$[0].name", is(patient1.getName())))
                .andExpect(jsonPath("$[0].lastName", is(patient1.getLastName())))
                .andExpect(jsonPath("$[1].id", is(patient2.getId().intValue())))
                .andExpect(jsonPath("$[1].name", is(patient2.getName())))
                .andExpect(jsonPath("$[1].lastName", is(patient2.getLastName())));
    }

    @Test
    public void testSave() throws Exception {
        Mockito.when(patientService.save(Mockito.any(Patient.class))).thenReturn(patient1);

        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@example.com\",\"cardIdentity\":123456789,\"admissionOfDate\":\"2023-08-02\",\"address\":null}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(patient1.getId().intValue())))
                .andExpect(jsonPath("$.name", is(patient1.getName())))
                .andExpect(jsonPath("$.lastName", is(patient1.getLastName())));
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(put("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@example.com\",\"cardIdentity\":123456789,\"admissionOfDate\":\"2023-08-02\",\"address\":null}"))
                .andExpect(status().isOk());

        Mockito.verify(patientService, Mockito.times(1)).update(Mockito.any(Patient.class));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/patients/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Se eliminó el paciente con id: 1"));

        Mockito.verify(patientService, Mockito.times(1)).delete(1L);
    }
}
