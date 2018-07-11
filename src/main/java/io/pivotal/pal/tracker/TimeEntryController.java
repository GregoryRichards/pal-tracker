package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController
{
    protected TimeEntryRepository repo;

    private final CounterService counter;

    private final GaugeService gauge;


    public TimeEntryController( TimeEntryRepository timeEntryRepository,CounterService counter,
                                GaugeService gauge )
    {
        this.repo = timeEntryRepository;
        this.gauge = gauge;
        this.counter = counter;
    }

    @PostMapping( "/time-entries")
    public ResponseEntity create( @RequestBody TimeEntry entry )
    {
        TimeEntry e = repo.create( entry );
       if ( e != null ) {
           counter.increment("TimeEntry.created");
           gauge.submit("timeEntries.count", repo.list().size());
           return new ResponseEntity( e, HttpStatus.CREATED);
       }
       else {
           return new ResponseEntity( HttpStatus.EXPECTATION_FAILED);
       }
    }

    @PutMapping( "/time-entries/{id}" )
    public ResponseEntity update( @PathVariable long id, @RequestBody TimeEntry entry )
    {
        TimeEntry e = repo.update( id, entry );
        if ( e != null ) {
            counter.increment("TimeEntry.updated");
            return new ResponseEntity(e, HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping( "/time-entries/{id}" )
    public ResponseEntity delete( @PathVariable long id )
    {
        TimeEntry e = repo.delete( id );
        if ( e != null ) {
            counter.increment("TimeEntry.deleted");
            gauge.submit("timeEntries.count", repo.list().size());
            return new ResponseEntity(e, HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping( path = "/time-entries/{id}", method=RequestMethod.GET )
    public ResponseEntity read( @PathVariable long id )
    {
        TimeEntry e = repo.find( id );
        if ( e != null ) {
            counter.increment("TimeEntry.read");
            return new ResponseEntity(e, HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping( path = "/time-entries" )
    public ResponseEntity list()
    {
        List<TimeEntry> l = repo.list();
        counter.increment("TimeEntry.listed");
        return new ResponseEntity( l, HttpStatus.OK );
    }


}
