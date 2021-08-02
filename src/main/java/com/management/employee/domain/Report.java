package com.management.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Report {
    int id;
    @NonNull int compliance_id;
    @NonNull int employee_id;
    private Date created_at;
    private Date updated_at;
}
