/*
 * BrandolKuete
 */

package com.brandolkuete.scolairebackendrest.rest;

import com.brandolkuete.scolairebackendrest.dto.EleveDTO;
import com.brandolkuete.scolairebackendrest.entities.Eleve;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "brandol", authorities = { "ADMIN", "USER" })
class ScolarRessourcesIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    static String objectToStringJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static  <T> T stringJsonToObject(String json, Class<T> clazz) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    void enregistrerEleve() throws Exception {
        EleveDTO eleveDto = new EleveDTO("15T2700","Kuete Melong", "Brandol","Yaoundé","Master 1","Informatique");
        String eleveJson = objectToStringJson(eleveDto);

        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post("/scolairerest/api/enregistrerEleve")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(eleveJson)).andReturn();
        Assertions.assertEquals(201,mvcResult.getResponse().getStatus());
    }

    @Test
    void listeElves() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/scolairerest/api/listeEleve"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void should_get_eleve_by_id() throws Exception {
        EleveDTO eleveDto = new EleveDTO("15T27007","Kuete Melong", "Brandol","Yaoundé","Master 1","Informatique");
        String eleveJson = objectToStringJson(eleveDto);

        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post("/scolairerest/api/enregistrerEleve")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(eleveJson)).andReturn();
        Eleve content= stringJsonToObject(mvcResult.getResponse().getContentAsString(),Eleve.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/scolairerest/api/findEleve/{id}",content.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.matricule",Matchers.is("15T27007")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nom",Matchers.is("Kuete Melong")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.prenom",Matchers.is("Brandol")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.addresse",Matchers.is("Yaoundé")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.niveau",Matchers.is("Master 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.filiere",Matchers.is("Informatique")));
    }

    @Test
    void should_delete_student() throws Exception {
        EleveDTO eleveDto = new EleveDTO("15T27007","Kuete Melong", "Brandol","Yaoundé","Master 1","Informatique");
        String eleveJson = objectToStringJson(eleveDto);

        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.post("/scolairerest/api/enregistrerEleve")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(eleveJson)).andReturn();

        Eleve content= stringJsonToObject(mvcResult.getResponse().getContentAsString(),Eleve.class);

        mockMvc.perform(MockMvcRequestBuilders.delete("/scolairerest/api/supprimerEleve/{id}",content.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}