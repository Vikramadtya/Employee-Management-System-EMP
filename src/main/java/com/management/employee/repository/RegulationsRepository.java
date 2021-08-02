package com.management.employee.repository;

import com.management.employee.domain.Regulation;

public interface RegulationsRepository {
    int add(Regulation regulation);
    int delete(String type);
    Iterable<Regulation> listAll();
}
