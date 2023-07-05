package telran.java47.person.service;

import java.time.LocalDate;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java47.person.dao.PersonRepository;
import telran.java47.person.dto.AddressDto;
import telran.java47.person.dto.AllPersonInfoDto;
import telran.java47.person.dto.ChildDto;
import telran.java47.person.dto.CityPopulationDto;
import telran.java47.person.dto.EmployeeDto;
import telran.java47.person.dto.PersonDto;
import telran.java47.person.exception.PersonNotFoundException;
import telran.java47.person.model.Address;
import telran.java47.person.model.Child;
import telran.java47.person.model.Employee;
import telran.java47.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {
	
	final PersonRepository personRepository;
	final ModelMapper modelMapper;


	@Override
	@Transactional
	public Boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}else {
			personRepository.save(modelMapper.map(personDto, Person.class));
			return true;
		}
		
	}

	@Override
	public AllPersonInfoDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		
		return modelMapper.map(person, AllPersonInfoDto.class);
	}

	@Override
	@Transactional
	public AllPersonInfoDto updateName(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setName(name);
		//personRepository.save(person);
		return modelMapper.map(person, AllPersonInfoDto.class);
	}

	@Override
	@Transactional
	public AllPersonInfoDto updateAddress(Integer id, AddressDto addressDto) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setAddress(modelMapper.map(addressDto, Address.class));
		//personRepository.save(person);
		return modelMapper.map(person, AllPersonInfoDto.class);
	}

	@Override
	@Transactional
	public AllPersonInfoDto deletePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		personRepository.delete(person);
		return modelMapper.map(person, AllPersonInfoDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AllPersonInfoDto> findPersonsByName(String name) {
		return personRepository.findByNameIgnoreCase(name)
				.map(p ->  modelMapper.map(p, AllPersonInfoDto.class))
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<AllPersonInfoDto> findPersonsByCity(String city) {
		return personRepository.findByAddressCityIgnoreCase(city)
				.map(p ->  modelMapper.map(p, AllPersonInfoDto.class))
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<AllPersonInfoDto> findPersonsByAges(Integer ageFrom, Integer ageTo) {
		LocalDate from = LocalDate.now().minusYears(ageTo);
		LocalDate to = LocalDate.now().minusYears(ageFrom);
		return personRepository.findByBirthDateBetween(from, to)
				.map(p ->  modelMapper.map(p, AllPersonInfoDto
						.class))
				.toList();

	}

	@Override
	public List<CityPopulationDto> getCityPopulation() {

		return personRepository.getCitiesPopulation();

	}

	@Override
	public void run(String... args) throws Exception {
		if (personRepository.count() == 0) {
			Person person = new Person(1000, "John", LocalDate.of(1983, 7, 7), new Address("TA", "BenG", 87));
			Child child = new Child(2000, "Moshe", LocalDate.of(2018, 7, 7), new Address("Ashkelon", "BenG", 21), "Shalom");
			Employee employee = new Employee(3000, "Sarah", LocalDate.of(1995, 11, 23), new Address("Rehovot", "Herzl", 9), "Motorolla", 20000);
			personRepository.save(person);
			personRepository.save(child);
			personRepository.save(employee);
		}
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<ChildDto> findAllChildrens() {
		return personRepository.getAllChildren()
				.map(c -> modelMapper.map(c, ChildDto.class))
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmployeeDto> findEmployeeBySalary(Integer minSalary, Integer maxSalary) {

		return personRepository.findEmployeeBySalary(minSalary, maxSalary)
				.map(e -> modelMapper.map(e, EmployeeDto.class))
				.toList();
	}

}
