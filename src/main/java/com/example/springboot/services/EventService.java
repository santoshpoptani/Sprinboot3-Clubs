package com.example.springboot.services;

import com.example.springboot.dto.EventDto;

import java.util.List;

public interface EventService {
    void createEvent(Long cludId, EventDto eventDto);

    List<EventDto> findAllEvents();

    EventDto findEventById(long eventId);

    void updateEvent(EventDto eventDto);

    void deleteEvent(long eventId);
}
