package com.example.demo.Controllers;

import com.example.demo.model.Events;
import com.example.demo.model.Performer;
import com.example.demo.services.EventService;
import com.example.demo.services.PerformerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/events")
@Profile("thyme")
public class EventController {

    private EventService service;
    private PerformerService performerService;

    public EventController(EventService service, PerformerService performerService) {
        super();
        this.service = service;
        this.performerService = performerService;
    }

    @RequestMapping({"", "/"})
    public String getEvents(Model model) {
        model.addAttribute("events", service.getEvents());
        return "Events";
    }


    @RequestMapping(value = "/{id}",method =  RequestMethod.GET)
    public String getPerformers(@PathVariable() String id, Model model) {
        Events event = service.getEvents(id);
        model.addAttribute("event", event);
        model.addAttribute("performers", event.getPerformers());
        model.addAttribute("performer",performerService.getPerformers());

        return "EventPage";
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String savePerftoEvent(@PathVariable String id, @ModelAttribute("performer") Performer performer, Model model, final RedirectAttributes redirectAttributes) {

        try {
            service.saveNewPerformer(id, performer);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "EventPage";
        }

        Events event = service.getEvents(id);
        redirectAttributes.addFlashAttribute("event", event);
        redirectAttributes.addFlashAttribute("performs", event.getPerformers());
        return "redirect:/events/{id}";
    }

    @RequestMapping(value = "/{id}/signup")
    public String addUser(@PathVariable String id, Model model) {
        try {
            service.addUser(id);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            Events event = service.getEvents(id);
            model.addAttribute("event", event);
            model.addAttribute("performers", event.getPerformers());

            return "EventPage";
        }
        model.addAttribute("events", service.getEvents());
        return "Events";
    }


    @RequestMapping(value = "/{id}/delete")
    public String deleteEvent(@PathVariable String id, Model model, final RedirectAttributes redirectAttributes) {
        service.deleteEvent(id);

        redirectAttributes.addFlashAttribute("events", service.getEvents());
        return "redirect:/events";
    }

    @RequestMapping(value = "/{id}/addperf", method = RequestMethod.GET)
    public String addPerformer(Model model) {
        Performer perf = new Performer();
        model.addAttribute("performer", perf);
        return "addPerformer";
    }

    @RequestMapping(value = "/{id}/addperf", method = RequestMethod.POST)
    public String savePerformer(@PathVariable String id, @ModelAttribute("performer") Performer performer, Model model, final RedirectAttributes redirectAttributes) {

        try {
            service.saveNewPerformer(id, performer);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "addPerformer";
        }

        Events event = service.getEvents(id);
        redirectAttributes.addFlashAttribute("event", event);
        redirectAttributes.addFlashAttribute("performers", event.getPerformers());
        return "redirect:/events/{id}";
    }

    @RequestMapping(value = "/{id}/addperftoevent", method = RequestMethod.GET)
    public String addPerformerToEvent(Model model) {
        Events event = new Events();
        model.addAttribute("event", event);
        model.addAttribute("performs", performerService.getPerformers());
        return "addPerftoEvent";
    }

    @RequestMapping(value = "/{id}/addperftoevent", method = RequestMethod.POST)
    public String savePerformerToEvent(@PathVariable String id, Model model, @ModelAttribute("event") Events event, final RedirectAttributes redirectAttributes) {

            try {
                service.saveNewPerformertoEvent(id ,event);
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("errorMessage", "Error: " + e.getMessage());
                return "addPerftoEvent";
            }
            redirectAttributes.addFlashAttribute("events", service.getEvents());
        return "redirect:/events/{id}";
    }

    @RequestMapping(value = "/addevent", method = RequestMethod.GET)
    public String addEvent(Model model) {
        Events event = new Events();
        model.addAttribute("event", event);
        model.addAttribute("performs", performerService.getPerformers());
        return "addEvent";
    }

    @RequestMapping(value = "/addevent", method = RequestMethod.POST)
    public String saveEvent(Model model, @ModelAttribute("event") Events event, final RedirectAttributes redirectAttributes) {

        try {
            service.saveNewEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "addEvent";
        }
        redirectAttributes.addFlashAttribute("events", service.getEvents());

        return "redirect:/events";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editEvent(@PathVariable String id, Model model) {
        Events event = service.getEvents(id);
        model.addAttribute("event", event);

        return "editEvent";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String saveChanges(@PathVariable String id, Model model, @ModelAttribute("event") Events event, final RedirectAttributes redirectAttributes) {
        try {
            service.saveNewEvent(event);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "editEvent";
        }

        redirectAttributes.addFlashAttribute("events", service.getEvents());

        return "redirect:/events";

    }

    @RequestMapping(value = "/{id}/deleteperf/{perfid}")
    public String removePerformer(@PathVariable String id, Model model, @PathVariable String perfid, final RedirectAttributes redirectAttributes) {
        service.deletePerformer(id, perfid);
        Events event = service.getEvents(id);
        redirectAttributes.addFlashAttribute("event", event);
        redirectAttributes.addFlashAttribute("performers", event.getPerformers());
        return "redirect:/events/{id}";
    }

}
