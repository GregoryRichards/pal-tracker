package io.pivotal.pal.tracker;

public class TimeEntryMetrics {

    private long created =0l;

    private long deleted =0l;

    private long updated =0l;

    private long read =0l;
    private long listed=0l;

    public long getCreated() {
        return created;
    }

    public void Created() {
       created++;
    }

    public long getDeleted() {
        return deleted;
    }

    public void incrementDeleted(long deleted) {
        deleted++;
    }

    public long getUpdated() {
        return updated;
    }

    public void incrementUpdated(long updated) {
        updated++;
    }

    public long getRead() {
        return read;
    }

    public void incrementRead(long read) {
        read++;
    }

    public long getListed() {
        return listed;
    }

    public void incrementListed() {
       listed++;
    }




}
