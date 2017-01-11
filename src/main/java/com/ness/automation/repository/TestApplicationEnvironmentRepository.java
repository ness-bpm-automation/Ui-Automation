package com.ness.automation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ness.automation.model.TestApplicationEnvironment;

@Transactional
public interface TestApplicationEnvironmentRepository extends CrudRepository<TestApplicationEnvironment, Long> {
   
}
