package com.example.springboot.services.Impl;

import com.example.springboot.dto.ClubDto;
import com.example.springboot.dto.EventDto;
import com.example.springboot.models.Club;
import com.example.springboot.models.Event;
import com.example.springboot.repository.ClubRepository;
import com.example.springboot.repository.EventRepository;
import com.example.springboot.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.springboot.mapper.EventMapper.mapToEvent;
import static com.example.springboot.mapper.EventMapper.mapToEventDto;

@Service
public class EvnetServiceImpl implements EventService {
    private EventRepository eventRepository;
    private ClubRepository clubRepository;

    @Autowired
    public EvnetServiceImpl(EventRepository eventRepository, ClubRepository clubRepository) {
        this.eventRepository = eventRepository;
        this.clubRepository = clubRepository;
    }

    @Override
    public void createEvent(Long cludId, EventDto eventDto) {
        Club club= clubRepository.findById(cludId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public EventDto findEventById(long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(long eventId) {
        eventRepository.deleteById(eventId);
    }


}
