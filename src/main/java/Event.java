public class Event implements Comparable<Event> {
    private String eventstr;

    public Event(String event)
    {
        this.eventstr = event;
    }

    public String toString()
    {
        return eventstr;
    }

    public int compareTo(Event e)
    {
        if (this.eventstr.length() < e.eventstr.length()) return -1;
        if (this.eventstr.length() > e.eventstr.length()) return 1;
        return 0;
    }
}
