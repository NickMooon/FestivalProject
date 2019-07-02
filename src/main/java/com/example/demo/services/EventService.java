package com.example.demo.services;

import com.example.demo.model.Events;
import com.example.demo.model.Login;
import com.example.demo.model.Performer;
import com.example.demo.model.Users;
import com.example.demo.repositories.EventsRepository;
import com.example.demo.repositories.LoginRepository;
import com.example.demo.repositories.PerformerRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class EventService {

    private EventsRepository eventsRepository;
    private UserRepository userRepository;
    private LoginRepository loginRepository;
    private PerformerRepository performerRepository;

    public EventService(EventsRepository eventsRepository, UserRepository userRepository, LoginRepository loginRepository, PerformerRepository performerRepository) {
        super();
        this.eventsRepository = eventsRepository;
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
        this.performerRepository = performerRepository;
    }

    public Iterable<Events> getEvents() {
        return eventsRepository.findAll();
    }

    public Events getEvents(String id) {
        return eventsRepository.findById(Long.parseLong(id)).get();
    }



    public void addUser(String festId) {
        Events event = eventsRepository.findById(Long.parseLong(festId)).get();
        if (event.getUsers().size() == event.getAll_places()) {
            throw new NumberFormatException("No places left.");
        }
        UserDetails userdetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Login login = loginRepository.findByUsername(userdetails.getUsername());
        Users part = userRepository.findByUser_id(login.getUser_id());
        event.getUsers().add(part);
        part.getEvents().add(event);
        eventsRepository.save(event);
        userRepository.save(part);
    }

    public Iterable<Events> getUsersEvent() {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (obj instanceof UserDetails) {
                UserDetails userdetails = (UserDetails) obj;
                Login login = loginRepository.findByUsername(userdetails.getUsername());
                Users part = userRepository.findByUser_id(login.getUser_id());
                return part.getEvents();
            }
        }
        return new ArrayList<Events>();
    }

    public void saveNewPerformer(String eventId, Performer perf) {
        Events event = eventsRepository.findById(Long.parseLong(eventId)).get();
        event.getPerformers().add(perf);
        perf.getEvents_perf().add(event);
        eventsRepository.save(event);
        performerRepository.save(perf);

    }

    public void saveNewPerformertoEvent(String id, Events event) {
        Events saved = eventsRepository.findById(Long.parseLong(id)).get();
        Set<Performer> setFest = new HashSet<>(event.getPerformers());

        for (Performer perf : setFest) {
            Performer loaded = performerRepository.findById(perf.getPerformer_id()).get();
            loaded.getEvents_perf().add(saved);
            performerRepository.save(loaded);
        }
        saved.getPerformers().addAll(setFest);
        eventsRepository.save(saved);
    }

    public Events saveNewEvent(Events event) {
        Events saved = eventsRepository.save(event);
        Set<Performer> setFest = new HashSet<>(event.getPerformers());

        for (Performer perf : setFest) {
            Performer loaded = performerRepository.findById(perf.getPerformer_id()).get();
            loaded.getEvents_perf().add(saved);
            performerRepository.save(loaded);
        }

        return saved;
    }
    public void deleteEvent(String eventId) {
        Events event = eventsRepository.findById(Long.parseLong(eventId)).get();
        for (Performer perf : performerRepository.findAll()) {
            perf.getEvents_perf().remove(event);
            performerRepository.save(perf);
        }

        for (Users part : userRepository.findAll()) {
            part.getEvents().remove(event);
           userRepository.save(part);
        }

        eventsRepository.deleteById(Long.parseLong(eventId));
    }

    public void deletePerformer(String festId, String perfId) {
        Events event = eventsRepository.findById(Long.parseLong(festId)).get();
        Performer perf = performerRepository.findById(Long.parseLong(perfId)).get();
        event.getPerformers().remove(perf);
        perf.getEvents_perf().remove(event);
        performerRepository.save(perf);
        eventsRepository.save(event);
    }

}
