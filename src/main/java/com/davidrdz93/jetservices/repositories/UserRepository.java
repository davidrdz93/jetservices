package com.davidrdz93.jetservices.repositories;

import com.davidrdz93.jetservices.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{ }
