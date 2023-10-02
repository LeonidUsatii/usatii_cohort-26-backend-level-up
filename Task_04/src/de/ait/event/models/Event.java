package de.ait.event.models;

import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Event {
    private Long id;
    private String title;
    private String startDate;
    private String expirationDate;

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startDate='" + startDate + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
