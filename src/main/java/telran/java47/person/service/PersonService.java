package telran.java47.person.service;

import java.util.List;

import telran.java47.person.dto.AddressDto;
import telran.java47.person.dto.AllPersonInfoDto;
import telran.java47.person.dto.ChildDto;
import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.dto.EmployeeDto;
import telran.java47.person.dto.PersonDto;


public interface PersonService {
	
	Boolean addPerson(PersonDto personDto);
	
	AllPersonInfoDto findPersonById(Integer id);
	
	AllPersonInfoDto updateName(Integer id, String name);
	
	AllPersonInfoDto updateAddress(Integer id, AddressDto addressDto);
	
	AllPersonInfoDto deletePerson(Integer id);
	
	List<AllPersonInfoDto> findPersonsByName(String name);
	
	List<AllPersonInfoDto> findPersonsByCity(String city);
	
	List<AllPersonInfoDto> findPersonsByAges(Integer ageFrom, Integer ageTo);
	
	List<ChildDto> findAllChildrens();
	
	List<EmployeeDto> findEmployeeBySalary(Integer minSalary, Integer maxSalary);
	
	List<CityPopulationDto> getCityPopulation();
	

}
