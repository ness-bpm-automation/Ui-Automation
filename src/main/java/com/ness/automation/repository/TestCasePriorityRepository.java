package com.ness.automation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ness.automation.model.TestCasePriority;

@Transactional
public interface TestCasePriorityRepository extends CrudRepository<TestCasePriority, Long> {
   
}
