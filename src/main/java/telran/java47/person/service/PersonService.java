package telran.java47.person.service;

import java.util.List;

import telran.java47.person.dto.AddressDto;
import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.dto.PersonDto;

public interface PersonService {
	
	Boolean addPerson(PersonDto personDto);
	
	PersonDto findPersonById(Integer id);
	
	PersonDto updateName(Integer id, String name);
	
	PersonDto updateAddress(Integer id, AddressDto addressDto);
	
	PersonDto deletePerson(Integer id);
	
	List<PersonDto> findPersonsByName(String name);
	
	List<PersonDto> findPersonsByCity(String city);
	
	List<PersonDto> findPersonsByAges(Integer ageFrom, Integer ageTo);
	
	List<CityPopulationDto> getCityPopulation();
	

}
