package com.apppedal.agendador.resources;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.apppedal.agendador.repositories.PedalRepository;

import com.apppedal.agendador.models.Pedal;

@RestController
@RequestMapping("/pedal")
public class PedalResource {
	
	@Autowired
	private PedalRepository pedalRepository;
	
	@PostMapping
	public void save(@RequestBody Pedal pedal) {
		pedalRepository.save(pedal);
	}
	
	@GetMapping
	public ResponseEntity<List<Pedal>> listing(){
		List<Pedal> pedais = pedalRepository.findAll();
		return ResponseEntity.ok().body(pedais);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedal> listingById(@PathVariable Long id){
		Pedal pedal = pedalRepository.findById(id).get();
		return ResponseEntity.ok().body(pedal);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletingById(@PathVariable Long id){
		pedalRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pedal> updatingById(@PathVariable Long id,@RequestBody Pedal pedal){
		Pedal pedalCurrent = pedalRepository.findById(id).get();
		BeanUtils.copyProperties(pedal, pedalCurrent,"id");
		pedalRepository.save(pedalCurrent);
		return ResponseEntity.ok().body(pedalCurrent);
	}

}