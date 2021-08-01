package com.management.employee.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	private Integer id;

	@NonNull private String first_name;

	@NonNull private String last_name;

	@NonNull private Date date_of_birth;

	@NonNull private String email;

	@NonNull private Integer department_id;

	private Date created_at;
	private Date updated_at;
}
