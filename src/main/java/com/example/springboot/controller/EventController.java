package com.example.springboot.controller;

import com.example.springboot.dto.EventDto;
import com.example.springboot.models.Event;
import com.example.springboot.services.EventService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
/**
 * Binnding result should be after @Valid Attributr order is important
 * **/
@Controller
public class EventController {
    private EventService eventService;
    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") long clubId, Model model){
        Event event = new Event();
        model.addAttribute("clubId",clubId);
        model.addAttribute("event",event);
        return "events-create";
    }

    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable("clubId") long clubId,
                              @Valid @ModelAttribute("event")EventDto eventDto,
                              BindingResult result,
                              Model model){

        if(result.hasErrors())
            return "events-create";

        eventService.createEvent(clubId,eventDto);
        return "redirect:/clubs/"+clubId;
    }

    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId") long eventId,Model model){
        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event",eventDto);
        return "events-details";
    }

    @GetMapping("/events")
    public String eventList(Model model){
        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("event",events);
        return "events-list";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEvents(@PathVariable("eventId") long eventId,Model model){
        EventDto dto  = eventService.findEventById(eventId);
        model.addAttribute("event",dto);
        return "events-edit";
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateEvents(@PathVariable("eventId") long eventId,
                               @Valid @ModelAttribute("event") EventDto eventDto,
                               BindingResult result){

        if(result.hasErrors())
            return "events-edit";
        EventDto eventDto1 = eventService.findEventById(eventId);
        eventDto.setId(eventId);
        eventDto.setClub(eventDto1.getClub());
        eventService.updateEvent(eventDto);
        return "redirect:/events";
    }

    @GetMapping("/events/{eventId}/delete")
    public String DeleteEvent(@PathVariable("eventId") long eventId){
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }

}
