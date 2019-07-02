package com.example.demo.repositories;

import com.example.demo.model.Events;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository  extends CrudRepository<Events, Long> {
}
