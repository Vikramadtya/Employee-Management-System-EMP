package com.management.employee.domain;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@RequiredArgsConstructor
public class Compliance {

    int id;
    @NonNull String regulation;
    @NonNull String detail;
    @NonNull int department;

    private Date created_at;
    private Date updated_at;
}
