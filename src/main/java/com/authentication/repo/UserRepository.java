package com.authentication.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.authentication.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> 
{
	
}
