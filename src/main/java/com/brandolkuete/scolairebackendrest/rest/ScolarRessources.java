package com.brandolkuete.scolairebackendrest.rest;

import com.brandolkuete.scolairebackendrest.dto.EleveDTO;
import com.brandolkuete.scolairebackendrest.dto.mapper.AbstractMapper;
import com.brandolkuete.scolairebackendrest.entities.Eleve;
import com.brandolkuete.scolairebackendrest.service.EleveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/scolairerest/api")
@CrossOrigin("http://localhost:3000")
public class ScolarRessources {

	private final EleveService eleveService;
	AbstractMapper<Eleve,EleveDTO> eleveMapper;


	public ScolarRessources(EleveService eleveService) {
		this.eleveService = eleveService;
	}

	@PostMapping(value= "/enregistrerEleve")
    public ResponseEntity<Object> enregistrerEleve(@RequestBody EleveDTO eleveDto) {
    	
    	if(eleveService.findByMatricule(eleveDto.getMatricule())!=null) {
			return new ResponseEntity<>("un eleve existe déja avec ce matricule",HttpStatus.BAD_REQUEST);
    	}else {
    		EleveDTO eleve = eleveService.save(eleveDto);
    		if (eleve==null){
				return new ResponseEntity<>("une erreur est survenue lors de l'enregistrement",HttpStatus.INTERNAL_SERVER_ERROR);
			}else {
				return new ResponseEntity<>(eleve,HttpStatus.CREATED);
			}
    	}
    }
    
    @RequestMapping(value= "/listeEleve")
    public ResponseEntity<List<EleveDTO>> listeElves(){
    	return ResponseEntity.ok().body(eleveService.findAll());
    }
    
    @RequestMapping(value= "/rechercherEleve/{matricule}", method= RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public EleveDTO rechercherEleve(@PathVariable String matricule) {
    	
    	Eleve eleve= eleveService.findByMatricule(matricule);
    	
    	EleveDTO eleveDto= new EleveDTO();
    	
    	if(eleve==null) {
    		return eleveDto;
    	}else {

    		eleveDto = eleveMapper.toDto(eleve);
    		return eleveDto;
    	}
    	
    }

	@GetMapping(value= "/findEleve/{id}")
	public ResponseEntity<EleveDTO> getEleve(@PathVariable("id") Long id) {
		EleveDTO eleveDTO= eleveService.getOne(id);

		return new ResponseEntity<>(eleveDTO,HttpStatus.OK);
	}

	@PutMapping(value = "/modifierEleve/{id}")
	public ResponseEntity<Object> modifierEleve(@RequestBody EleveDTO eleveDto, @PathVariable("id") Long id){

		EleveDTO eleveDTO= eleveService.update(eleveDto,id);

		return new ResponseEntity<>(eleveDTO,HttpStatus.OK);
	}

	@DeleteMapping(value = "/supprimerEleve/{id}")
	public ResponseEntity<Object> supprimerEleve(@PathVariable("id") Long id){

		eleveService.delete(id);
		return new ResponseEntity<>("l'eleve a été supprimé",HttpStatus.OK);
	}
}