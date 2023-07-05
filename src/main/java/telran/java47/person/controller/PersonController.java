package telran.java47.person.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import telran.java47.person.dto.AddressDto;
import telran.java47.person.dto.AllPersonInfoDto;
import telran.java47.person.dto.ChildDto;
import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.dto.EmployeeDto;
import telran.java47.person.dto.PersonDto;
import telran.java47.person.service.PersonService;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController{
	final PersonService personService;
	final ObjectMapper getObjectMapper;

	@PostMapping
	public Boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}

	@GetMapping("/{id}")
	public AllPersonInfoDto findPersonById(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}


	@PutMapping("/{id}/name/{name}")
	public AllPersonInfoDto updateName(@PathVariable Integer id, @PathVariable String name) {
		return personService.updateName(id, name);
	}

	@PutMapping("/{id}/address")
	public AllPersonInfoDto updateAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
		return personService.updateAddress(id, addressDto);
	}

	@DeleteMapping("/{id}")
	public AllPersonInfoDto deletePerson(@PathVariable Integer id) {
		return personService.deletePerson(id);
	}

	@GetMapping("/name/{name}")
	public List<AllPersonInfoDto> findPersonsByName(@PathVariable String name) {
		return personService.findPersonsByName(name);
	}

	@GetMapping("/city/{city}")
	public List<AllPersonInfoDto> findPersonsByCity(@PathVariable String city) {
		return personService.findPersonsByCity(city);
	}

	@GetMapping("/ages/{ageFrom}/{ageTo}")
	public List<AllPersonInfoDto> findPersonsByAges(@PathVariable Integer ageFrom, @PathVariable Integer ageTo) {
		return personService.findPersonsByAges(ageFrom, ageTo);
	}


	@GetMapping("/population/city")
	public List<CityPopulationDto> getCityPopulation() {
		return personService.getCityPopulation();
	}
	
	@GetMapping("/children")
	public List<ChildDto> findAllChildrens()
	{
		return personService.findAllChildrens();
	}
	
	@GetMapping("/salary/{minSalary}/{maxSalary}")
	public List<EmployeeDto> findEmployeeBySalary(@PathVariable Integer minSalary, @PathVariable Integer maxSalary){
		return personService.findEmployeeBySalary(minSalary, maxSalary);
	}
	
	

}
