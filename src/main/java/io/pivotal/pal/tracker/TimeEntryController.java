package io.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController
{
    protected TimeEntryRepository repo;

    public TimeEntryController( TimeEntryRepository repo )
    {
        this.repo = repo;
    }

    @PostMapping( "/time-entries")
    public ResponseEntity create( @RequestBody TimeEntry entry )
    {
        TimeEntry e = repo.create( entry );
       if ( e != null ) {
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
        if ( e != null )
            return new ResponseEntity( e, HttpStatus.OK );
        else
            return new ResponseEntity( HttpStatus.NOT_FOUND );
    }

    @DeleteMapping( "/time-entries/{id}" )
    public ResponseEntity delete( @PathVariable long id )
    {
        TimeEntry e = repo.delete( id );
        if ( e != null )
            return new ResponseEntity( e, HttpStatus.NO_CONTENT );
        else
            return new ResponseEntity( HttpStatus.NO_CONTENT );
    }

    @RequestMapping( path = "/time-entries/{id}", method=RequestMethod.GET )
    public ResponseEntity read( @PathVariable long id )
    {
        TimeEntry e = repo.find( id );
        if ( e != null )
            return new ResponseEntity( e, HttpStatus.OK );
        else
            return new ResponseEntity( HttpStatus.NOT_FOUND );
    }

    @RequestMapping( path = "/time-entries", method=RequestMethod.GET )
    public ResponseEntity list()
    {
        List<TimeEntry> l = repo.list();
        if ( l.isEmpty() )
            return new ResponseEntity( HttpStatus.NO_CONTENT );
        else
            return new ResponseEntity( l, HttpStatus.OK );
    }


}
