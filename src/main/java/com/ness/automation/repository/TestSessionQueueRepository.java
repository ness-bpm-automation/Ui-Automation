package com.ness.automation.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ness.automation.model.TestSessionQueue;

@Transactional
public interface TestSessionQueueRepository  extends CrudRepository<TestSessionQueue, Long>{
	
	@Query(value="select * from nessq.session_queue order by run_id desc limit 1", nativeQuery = true)
    public TestSessionQueue findLatestSessionQueue();

}
