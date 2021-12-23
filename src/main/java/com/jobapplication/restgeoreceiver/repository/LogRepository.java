package com.jobapplication.restgeoreceiver.repository;

import com.jobapplication.restgeoreceiver.domain.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface LogRepository extends CrudRepository<Log, Long> {
    List<Log> findAll();
    Log save(Log log);
}
