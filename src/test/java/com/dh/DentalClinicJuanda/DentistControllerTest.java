package com.dh.DentalClinicJuanda;// src/test/java/com/dh/DentalClinicJuanda/controller/DentistControllerTest.java
import com.dh.DentalClinicJuanda.entity.Dentist;
import com.dh.DentalClinicJuanda.repository.IDentistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class DentistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IDentistRepository dentistRepository;

    @Test
    public void testCreateDentist() throws Exception {
        String dentistJson = "{\"registration\": \"12541333\", \"name\": \"Carlos\", \"lastName\": \"Odonto\"}";

        mockMvc.perform(post("/dentists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dentistJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Carlos"));
    }

    @Test
    public void testGetAllDentists() throws Exception {
        Dentist dentist = new Dentist();
        dentist.setRegistration(12541333);
        dentist.setName("Carlos");
        dentist.setLastName("Odonto");
        dentistRepository.save(dentist);

        mockMvc.perform(get("/dentists"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Carlos"));
    }
}