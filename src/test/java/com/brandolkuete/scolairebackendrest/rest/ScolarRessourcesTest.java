/*
 * BrandolKuete
 */

package com.brandolkuete.scolairebackendrest.rest;

import com.brandolkuete.scolairebackendrest.dto.EleveDTO;
import com.brandolkuete.scolairebackendrest.entities.Eleve;
import com.brandolkuete.scolairebackendrest.service.EleveService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
@SpringBootTest
@AutoConfigureMockMvc
class ScolarRessourcesTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EleveService eleveService;

    static String stringToJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void should_save_a_student() throws Exception {

        Eleve eleve = new Eleve(11l,"15T27007","Kuete Melong", "Brandol","Yaoundé","Master 1","Informatique");
        EleveDTO eleveDto = new EleveDTO("15T27007","Kuete Melong", "Brandol","Yaoundé","Master 1","Informatique");

        Mockito.doReturn(eleve).when(eleveService).save(eleveDto);
        String eleveJson = stringToJson(eleveDto);

        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post("/scolairerest/api/enregistrerEleve")
                                                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                                                                    .content(eleveJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assertions.assertEquals(201, status);
    }

    @Test
    void listeElves() throws Exception {
        //Given
        Eleve eleve1 = new Eleve( 1l, "15T2778", "Kuete Melong", "Brandol", new  SimpleDateFormat("yyyy-MM-dd").parse("2021-05-04"), "Yaoundé", "Master 1","Informatique");
        Eleve eleve2= new Eleve( 2l, "12U777", "Atangana", "Paul", new  SimpleDateFormat("yyyy-MM-dd").parse("1998-05-04"), "Douala", "Niveau 1","Chimie");

        Mockito.doReturn(Arrays.asList(eleve1,eleve2)).when(eleveService).findAll();

        mockMvc.perform(MockMvcRequestBuilders.get("/scolairerest/api/listeEleve"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$",Matchers.hasSize(2)));
    }

    @Test
    void should_get_eleveByIdFound() throws Exception {
        EleveDTO eleve = new EleveDTO(  "15T2778", "Kuete Melong", "Brandol", "Yaoundé", "Master 1","Informatique");

        Mockito.doReturn(eleve).when(eleveService).getOne(1l);

        mockMvc.perform(MockMvcRequestBuilders.get("/scolairerest/api/findEleve/{id}",1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.prenom",Matchers.is("Brandol")));
    }

    @Test
    void should_get_eleveByIdNotFound() throws Exception {

        Mockito.doReturn(null).when(eleveService).getOne(1l);

        mockMvc.perform(MockMvcRequestBuilders.get("/scolairerest/api/findEleve/{id}",1))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void should_delete_student() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/scolairerest/api/supprimerEleve/{id}",1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}