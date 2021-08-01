package com.management.employee.domain;

import lombok.*;

import java.sql.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private Integer id;
    @NonNull private String name;

    private Date created_at;
    private Date updated_at;

}
