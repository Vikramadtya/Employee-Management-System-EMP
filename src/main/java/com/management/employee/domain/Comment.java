package com.management.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Comment {
    int id;
    @NonNull  int report_id;
    @NonNull String comment;
    @NonNull int author;

    private Date created_at;
    private Date updated_at;
}
