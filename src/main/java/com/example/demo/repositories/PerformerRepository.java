package com.example.demo.repositories;


import com.example.demo.model.Performer;
import org.springframework.data.repository.CrudRepository;

public interface PerformerRepository extends CrudRepository<Performer, Long> {

}
