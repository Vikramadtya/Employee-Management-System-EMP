package com.management.employee.domain;

import lombok.*;

import java.sql.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Compliance {

    int id;
    @NonNull String regulation_type;
    @NonNull String detail;
    @NonNull int department_id;

    private Date created_at;
    private Date updated_at;
}
