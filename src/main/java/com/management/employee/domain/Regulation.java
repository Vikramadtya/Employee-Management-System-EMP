package com.management.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Regulation {
    @NonNull String type;
    private Date created_at;
    private Date updated_at;
}
