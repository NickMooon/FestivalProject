package com.example.demo.services;


import com.example.demo.model.Performer;
import com.example.demo.repositories.PerformerRepository;
import org.springframework.stereotype.Service;


@Service
public class PerformerService {

    private PerformerRepository performerRepository;

    public PerformerService(PerformerRepository performerRepository) {
        super();
        this.performerRepository = performerRepository;
    }

    public void createNewPerformer(Performer perf){
        Performer performer = new Performer();
        performer.setName(perf.getName());
        Performer savePerformer = performerRepository.save(performer);
        performer.setPerformer_id(savePerformer.getPerformer_id());
    }

    public Iterable<Performer> getPerformers() {

        return performerRepository.findAll();
    }

}