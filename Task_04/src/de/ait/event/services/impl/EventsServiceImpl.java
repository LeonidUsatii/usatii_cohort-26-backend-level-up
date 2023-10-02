package de.ait.event.services.impl;

import de.ait.event.models.Event;
import de.ait.event.repositories.EventsRepository;
import de.ait.event.repositories.impl.EventsRepositoryJdbcTemplateImpl;
import de.ait.event.services.EventsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class EventsServiceImpl implements EventsService {
    private  final EventsRepository eventsRepositoryJdbcTemplateImpl;
    @Override
    public Event addEvent(String title, String startDate, String expirationDate) {

        Event event = Event.builder()
                .title(title)
                .startDate(startDate)
                .expirationDate(expirationDate)
                .build();

        eventsRepositoryJdbcTemplateImpl.save(event);

        return event;
    }


    @Override
    public Event getEvent(String date) {
        return eventsRepositoryJdbcTemplateImpl.getAllEventsByDate(date);
    }

    @Override
    public Event updateEvent(Long id, String startDate, String expirationDate) {
        List<Event> events = eventsRepositoryJdbcTemplateImpl.findAll();
        Event eventForUpdate = null;
        for (Event event : events) {
            if (event.getId().equals(id)) {
                eventForUpdate = new Event(id, event.getTitle(), startDate, expirationDate);
                break;
            }
        }
        if (eventForUpdate == null) {
            throw new IllegalArgumentException("User with id <" + id + "> not found");
        }
        eventsRepositoryJdbcTemplateImpl.updateEvent(eventForUpdate);
        return eventForUpdate;
    }

    @Override
    public Event deleteEvent(Long id) {
        Event EventForDelete = eventsRepositoryJdbcTemplateImpl.findById(id);
        eventsRepositoryJdbcTemplateImpl.findById(id);
        eventsRepositoryJdbcTemplateImpl.deleteById(id);
        return EventForDelete;
    }
}
