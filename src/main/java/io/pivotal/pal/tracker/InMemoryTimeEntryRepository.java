package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository
{

/*    public InMemoryTimeEntryRepository() {
        TimeEntry.resetCounter();
    }*/

    Map entryMap = new HashMap<Long, Object>();

    private long counter = 0;

    public TimeEntry create(TimeEntry entry )
    {
        counter++;
        entry.setId( counter );
        entryMap.put( counter, entry );
        return (TimeEntry) entryMap.get( entry.getId() );
    }

    public TimeEntry find( long id )
    {
        return (TimeEntry) entryMap.get( id );
    }

    public TimeEntry update( long id, TimeEntry entry )
    {
        entry.setId( id );
        entryMap.put( id, entry );
        return ( TimeEntry ) entryMap.get( id );
    }

    public TimeEntry delete( long id )
    {
        return (TimeEntry) entryMap.remove( id );
    }

    public List<TimeEntry> list()
    {
        List<TimeEntry> list = new ArrayList<>();
        try {
            list = new ArrayList( entryMap.values() );
        }
        catch ( Exception e )
        {

        }
        return list;
    }


}
