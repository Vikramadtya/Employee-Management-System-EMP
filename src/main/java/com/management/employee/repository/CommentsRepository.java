package com.management.employee.repository;

import com.management.employee.domain.Comment;

public interface CommentsRepository {
    int add(Comment comment);
    Comment findById(int id);
    Iterable<Comment> listAll();
    Iterable<Comment> findByReportId(int id);
    Iterable<Comment> findByEmployeeId(int id);
}
