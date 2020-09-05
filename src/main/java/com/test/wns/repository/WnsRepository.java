package com.test.wns.repository;

import com.test.wns.model.MigrateTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is repository interface for final criteria ext 
 * 
 * @author charusingla
 *
 */
@Repository
public interface WnsRepository extends JpaRepository<MigrateTest, Integer> {
	
		

}
