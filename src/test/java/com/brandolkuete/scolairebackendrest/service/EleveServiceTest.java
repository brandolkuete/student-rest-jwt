package com.brandolkuete.scolairebackendrest.service;

import com.brandolkuete.scolairebackendrest.dto.EleveDTO;
import com.brandolkuete.scolairebackendrest.dto.mapper.EleveMapper;
import com.brandolkuete.scolairebackendrest.entities.Eleve;
import com.brandolkuete.scolairebackendrest.repository.EleveRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    public void findByMatricule() {

        Eleve eleveEntity = new Eleve((long) 9, "Master 1","Informatique");

        Mockito.when(eleveRepository.findByMatricule("15T2778")).thenReturn(eleveEntity);
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

        Assertions.assertThat(eleve1Captured).isEqualTo(eleve);
    }

    @Test
    void shouldFindAllStudents() {
        //When
        underTest.findAll();

        //Then
        Mockito.verify(eleveRepository).findAll();
    }

    @Test
    void shouldAddTwoNumbers(){
        //Given
        int a= 12;
        int b= 10;

        //Then
        Assertions.assertThat(add.addition(a,b)).isEqualTo(22);
    }

    public class Add{
        public int addition(int a, int b){
            return a+b;
        }
    }
}