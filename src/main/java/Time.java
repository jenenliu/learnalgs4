import edu.princeton.cs.algs4.StdOut;

public class Time implements Comparable<Time> {
    private int hour;
    private int min;
    private int second;

    public Time(int h, int m, int s)
    {
        if (h < 0 || h >= 24)
            throw new IllegalArgumentException("hour should be in 0 to 23");
        if (m < 0 || m >= 60)
            throw new IllegalArgumentException("minutes should be in 0 to 59");
        if (s < 0 || s >= 60)
            throw new IllegalArgumentException("second should be in 0 to 59");

        this.hour = h;
        this.min = m;
        this.second = s;
    }

    // build Time by timestamp "09:08:10" string
    public Time(String timestamp)
    {
        String delim = ":";
        String[] time = timestamp.split(delim);
        if (time.length != 3)
            throw new IllegalArgumentException("timestamp format should be like 09:08:10");
        this.hour = Integer.parseInt(time[0]);
        this.min = Integer.parseInt(time[1]);
        this.second = Integer.parseInt(time[2]);
    }

    public void print()
    {
        StdOut.println(hour + ":" + min + ":" + second);
    }

    public int compareTo(Time t)
    {
        if (this.hour > t.hour) return 1;
        if (this.hour < t.hour) return -1;
        if (this.min > t.min) return 1;
        if (this.min < t.min) return -1;
        if (this.second > t.second) return 1;
        if (this.second < t.second) return -1;
        return 0;
    }

    public String toString()
    {
        return this.hour + ":" + this.min + ":" + this.second;
    }

    public static void main(String[] args)
    {
        Time t = new Time(9, 8, 10);
        Time t1 = new Time("09:08:10");
        t.print();
        t1.print();
        Time invalidt = new Time(60, 61, 90);
        invalidt.print();
    }
}
