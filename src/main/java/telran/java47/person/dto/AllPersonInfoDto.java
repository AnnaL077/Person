package telran.java47.person.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
public class AllPersonInfoDto {
	Integer id;
    String name;
    LocalDate birthDate;
    AddressDto address;
	@JsonInclude(JsonInclude.Include.NON_NULL)
    String company;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	Integer salary;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	String kindergarden;
}
