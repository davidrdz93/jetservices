package com.davidrdz93.jetservices.repositories;

import com.davidrdz93.jetservices.entities.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor
{
    Optional<User> findByUsername(String username);
}
