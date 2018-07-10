package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.logging.Logger;

public class TimeEntry {

    private static final Logger LOGGER = Logger.getLogger(TimeEntry.class.getName());

    public static final long NOT_SET = 0000;

    protected long id;
    protected long projectId;
    protected long userId;
    protected LocalDate date;
    protected int hours;

    public TimeEntry()
    {
        this.id = 0;
        this.projectId = NOT_SET;
        this.userId = NOT_SET;
        this.date = LocalDate.parse("2000-01-01");
        this.hours = 0;
    }

    public TimeEntry( long id, long projectId, long userId, LocalDate date, int hours )
    {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public TimeEntry( long projectId, long userId, LocalDate date, int hours )
    {
        this.id = 0;
        this.projectId = projectId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    @Override
    public boolean equals( Object compareTo )
    {
        return ( compareTo instanceof TimeEntry
                && this.id == ((TimeEntry)compareTo).getId()
                && this.projectId == ((TimeEntry)compareTo).getProjectId()
                && this.userId == ((TimeEntry)compareTo).getUserId()
                && this.date.equals( ((TimeEntry)compareTo).getDate() )
                && this.hours == ((TimeEntry)compareTo).getHours() );
    }
}
