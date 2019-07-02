package com.example.demo.repositories;

import com.example.demo.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {

    @Query("select p from Users p where p.id = ?1")
    Users findByUser_id(Long id);

    Users findByEmail(String email);
}
