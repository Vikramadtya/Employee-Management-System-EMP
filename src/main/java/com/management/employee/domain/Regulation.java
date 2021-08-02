package com.management.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Regulation {
    @NonNull String type;
    private Date created_at;
    private Date updated_at;
}
