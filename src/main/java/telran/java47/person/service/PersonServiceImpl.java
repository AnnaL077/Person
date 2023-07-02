package telran.java47.person.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java47.person.dao.PersonRepository;
import telran.java47.person.dto.AddressDto;
import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.dto.PersonDto;
import telran.java47.person.exception.PersonNotFoundException;
import telran.java47.person.model.Address;
import telran.java47.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
	
	final PersonRepository personRepository;
	final ModelMapper modelMapper;

	@Override
	public Boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}else {
			personRepository.save(modelMapper.map(personDto, Person.class));
			return true;
		}
		
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto updateName(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setName(name);
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto updateAddress(Integer id, AddressDto addressDto) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setAddress(modelMapper.map(addressDto, Address.class));
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto deletePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public List<PersonDto> findPersonsByName(String name) {
		return personRepository.findAll().stream()
				.filter(p -> p.getName().equalsIgnoreCase(name))
				.map(p ->  modelMapper.map(p, PersonDto.class))
				.toList();
	}

	@Override
	public List<PersonDto> findPersonsByCity(String city) {
		return personRepository.findAll().stream()
				.filter(p -> p.getAddress().getCity().equalsIgnoreCase(city))
				.map(p ->  modelMapper.map(p, PersonDto.class))
				.toList();
	}

	@Override
	public List<PersonDto> findPersonsByAges(Integer ageFrom, Integer ageTo) {

		return personRepository.findAll().stream()
				.filter(p -> (Period.between(p.getBirthDate(), LocalDate.now()).getYears() >= ageFrom && Period.between(p.getBirthDate(), LocalDate.now()).getYears() < ageTo))
				.map(p ->  modelMapper.map(p, PersonDto.class))
				.toList();

	}

	@Override
	public List<CityPopulationDto> getCityPopulation() {

		return personRepository.findAll().stream()
					.map(p -> p.getAddress())
					.collect(Collectors.groupingBy(Address::getCity, Collectors.counting()))
					.entrySet().stream()
					.map(e -> new CityPopulationDto(e.getKey(), e.getValue()))
		                .collect(Collectors.toList());

	}

}
