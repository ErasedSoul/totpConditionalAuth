package com.authentication.mfa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HashRepository extends JpaRepository<Hash,Integer> {
    
	
	Optional<Hash> findHashByUserName(String userName);
	
}
