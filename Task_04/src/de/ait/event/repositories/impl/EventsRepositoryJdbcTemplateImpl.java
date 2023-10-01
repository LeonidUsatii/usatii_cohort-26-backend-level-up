package de.ait.event.repositories.impl;

import de.ait.event.models.Event;
import de.ait.event.repositories.EventsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class EventsRepositoryJdbcTemplateImpl implements EventsRepository {
    //language=SQL
    private static final String SQL_SELECT_BY_ID = "select * from event where id = ?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "select * from event";

    //language=SQL

    private static final String SQL_SELECT_BY_DATE = "select * from event where start_date = ?";


    //language=SQL
    private static final String SQL_UPDATE_BY_ID = "update event set start_date = ?, expiration_date = ? where id = ?";

    //language=SQL
    private static final String SQL_DELETE_EVENT_BY_ID = "delete from event where id = ?";


    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<Event> EVENT_ROW_MAPPER = (row, rowNumber) -> {

        Long id = row.getLong("id");
        String title = row.getString("title");
        String startDate = row.getString("start_date");
        String expirationDate= row.getString("expiration_date");

        return Event.builder()
                .id(id)
                .title(title)
                .startDate(startDate)
                .expirationDate(expirationDate)
                .build();
    };

    @Override
    public List<Event> findAll() {

        return jdbcTemplate.query(SQL_SELECT_ALL, EVENT_ROW_MAPPER);
    }


    @Override
    public void save(Event model) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .usingGeneratedKeyColumns("id");
        jdbcInsert.withTableName("event");
        Map<String, Object> row = new HashMap<>();
        row.put("title", model.getTitle());
        row.put("start_date", model.getStartDate());
        row.put("expiration_date", model.getExpirationDate());

        long generatedId = jdbcInsert.executeAndReturnKey(row).longValue();
        model.setId(generatedId);
    }

    @Override
    public Event getAllEventsByDate(String date) {
        return jdbcTemplate.queryForObject(SQL_SELECT_BY_DATE, EVENT_ROW_MAPPER, date);
    }

    @Override
    public void updateEvent(Event model) {
        jdbcTemplate.update(SQL_UPDATE_BY_ID, model.getStartDate(), model.getExpirationDate(), model.getId());
    }

    @Override
    public Event findById(Long id) {

        return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, EVENT_ROW_MAPPER, id);
    }

    @Override
    public void deleteById(Long id) {

        jdbcTemplate.update(SQL_DELETE_EVENT_BY_ID, id);
    }

}
