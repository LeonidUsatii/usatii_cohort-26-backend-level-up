package de.ait.event.services;

import de.ait.event.models.Event;

import java.util.List;

public interface EventsService {
    Event addEvent(String title, String startDate, String expirationDate);

    Event getEvent(String date);

    Event updateEvent(Long id, String startDate, String expirationDate);

    Event deleteEvent(Long id);

}
