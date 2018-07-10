package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public interface TimeEntryRepository {

    public TimeEntry create(TimeEntry entry );

    public TimeEntry find( long id );

    public TimeEntry update( long id, TimeEntry entry );

    public TimeEntry delete( long id );

    public List<TimeEntry> list();
}
