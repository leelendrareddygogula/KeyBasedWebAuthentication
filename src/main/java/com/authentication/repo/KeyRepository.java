package com.authentication.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.authentication.entity.Key;

@Repository
public interface KeyRepository extends CrudRepository<Key, String>
{
	
}
