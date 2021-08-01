package com.management.employee.domain;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	public static enum Department {
		ONE,TWO,THREE,FOUR
	};

	@NotBlank
	private int employee_id;

	@NotBlank
	@Size(min=3,message = "First name must be atleast 3 character long")
	private String first_name;

	@NotBlank
	@Size(min=3,message = "Last name must be atleast 3 character long")
	private String last_name;

	@NotBlank
	private String date_of_birth;

	@NotBlank
	private String email;

	@NotNull
	private Department department_id;
}
