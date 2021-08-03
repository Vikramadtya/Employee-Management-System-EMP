package com.management.employee.domain;

import lombok.*;

import java.sql.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    int id;
    @NonNull  int report_id;
    @NonNull String comment;
    @NonNull int author;

    private Date created_at;
    private Date updated_at;
}
