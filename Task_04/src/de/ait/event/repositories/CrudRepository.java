package de.ait.event.repositories;

import java.util.List;

public interface CrudRepository<T> {
    List<T> findAll();

    void save(T model);

    T getAllEventsByDate(String date);

    void updateEvent(T model);

    T findById(Long id);

    void deleteById(Long id);

}
