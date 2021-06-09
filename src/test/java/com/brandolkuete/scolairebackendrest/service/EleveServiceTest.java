/*
 * BrandolKuete
 */

package com.brandolkuete.scolairebackendrest.service;

import com.brandolkuete.scolairebackendrest.dto.EleveDTO;
import com.brandolkuete.scolairebackendrest.dto.mapper.EleveMapper;
import com.brandolkuete.scolairebackendrest.entities.Eleve;
import com.brandolkuete.scolairebackendrest.repository.EleveRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class EleveServiceTest {

    private Add add;

    @Mock
    private EleveRepository eleveRepository;
    @Mock
    private EleveMapper eleveMapper;

    private EleveService underTest;

    @BeforeEach
    void setUp() {
        add= new Add();

        //initialiser les mock
        MockitoAnnotations.initMocks(this);
        underTest = new EleveService(eleveRepository,eleveMapper);
    }

    @Test
    public void should_find_student_by_matricule() {

        Eleve eleveEntity = new Eleve( 9L, "Master 1","Informatique");
        Mockito.doReturn(eleveEntity).when(eleveRepository).findByMatricule("15T2778");

        Eleve returnedEleve = underTest.findByMatricule("15T2778");

        Assertions.assertSame(eleveEntity,returnedEleve);
    }

    @Test
    public void shouldSaveStudent() throws ParseException {

        //Given
        EleveDTO eleveDto = new EleveDTO(null, "15T2778", "Kuete Melong", "Brandol", new  SimpleDateFormat("yyyy-MM-dd").parse("2021-05-04"), "Yaoundé", "Master 1","Informatique");
        Eleve eleve = eleveMapper.toEntity(eleveDto);

        //When
        underTest.save(eleveDto);

        //Then
        ArgumentCaptor<Eleve> eleveArgumentCaptor= ArgumentCaptor.forClass(Eleve.class);
        Mockito.verify(eleveRepository).save(eleveArgumentCaptor.capture());

        Eleve eleve1Captured = eleveArgumentCaptor.getValue();
        Assertions.assertEquals(eleve1Captured,eleve);
    }

    @Test
    void shouldFindAllStudents() throws ParseException {

        //Given
        Eleve eleve1 = new Eleve( 1l, "15T2778", "Kuete Melong", "Brandol", new  SimpleDateFormat("yyyy-MM-dd").parse("2021-05-04"), "Yaoundé", "Master 1","Informatique");
        Eleve eleve2= new Eleve( 2l, "12U777", "Atangana", "Paul", new  SimpleDateFormat("yyyy-MM-dd").parse("1998-05-04"), "Douala", "Niveau 1","Chimie");

        //When
        Mockito.doReturn(Arrays.asList(eleve1,eleve2)).when(eleveRepository).findAll();
        List<EleveDTO> returnedList = underTest.findAll();

        //Then
        Assertions.assertEquals(Arrays.asList(eleveMapper.toDto(eleve1),eleveMapper.toDto(eleve2)), returnedList);
    }

    @Test
    void shouldAddTwoNumbers(){
        //Given
        int a= 12;
        int b= 10;

        //Then
        Assertions.assertEquals(add.addition(a,b),22);
    }

    @Test
    void shoul_delete_student() throws ParseException {
        Eleve eleve = new Eleve( 1l, "15T2778", "Kuete Melong", "Brandol", new  SimpleDateFormat("yyyy-MM-dd").parse("2021-05-04"), "Yaoundé", "Master 1","Informatique");
        Mockito.doReturn(Optional.of(eleve)).when(eleveRepository).findById(eleve.getId());
        underTest.delete(eleve.getId());

        Mockito.verify(eleveRepository).deleteById(eleve.getId());
    }

    @Test
    void should_get_student_by_id() throws ParseException {
        Eleve eleve = new Eleve( 1l, "15T2778", "Kuete Melong", "Brandol", new  SimpleDateFormat("yyyy-MM-dd").parse("2021-05-04"), "Yaoundé", "Master 1","Informatique");

        Mockito.doReturn(Optional.of(eleve)).when(eleveRepository).findById(eleve.getId());

        Eleve eleve1= eleveMapper.toEntity(underTest.getOne(eleve.getId()));

        Assertions.assertEquals(eleve,eleve1);
    }

    public class Add{
        public int addition(int a, int b){
            return a+b;
        }
    }
}