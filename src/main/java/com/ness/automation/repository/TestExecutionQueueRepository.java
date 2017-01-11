package com.ness.automation.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ness.automation.model.TestExecutionQueue;

@Transactional
public interface TestExecutionQueueRepository extends CrudRepository<TestExecutionQueue, Long> {

}
