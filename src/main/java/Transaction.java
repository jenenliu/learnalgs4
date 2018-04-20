import java.awt.font.TransformAttribute;
import java.util.Comparator;
import java.util.Date;

public class Transaction {
    private final String who;
    private final Date when;
    private final double amount;
    private int index;

    public Transaction(String name, Date trantime, double tranamount, int i)
    {
        who = name;
        when = trantime;
        amount = tranamount;
        index = i;
    }

    public String toString() {
        return who + ", " + when + ", " + amount;
    }

    public static class WhoOrder implements Comparator<Transaction>
    {
        public int compare(Transaction v, Transaction w)
        {
            if (v.who.compareTo(w.who) != 0)
                return v.who.compareTo(w.who);
            return 0;
        }
    }

    public static class WhoOrderWithIndex implements Comparator<Transaction>
    {
        public int compare(Transaction v, Transaction w)
        {
            if (v.who.compareTo(w.who) != 0)
                return v.who.compareTo(w.who);
            if (v.index < w.index) return -1;
            if (v.index > w.index) return 1;
            return 0;
        }
    }

    public static class WhenOrder implements Comparator<Transaction>
    {
        public int compare(Transaction v, Transaction w)
        {
            return v.when.compareTo(w.when);
        }
    }

    public static class HowMuchOrder implements Comparator<Transaction>
    {
        public int compare(Transaction v, Transaction w)
        {
            if (v.amount < w.amount) return -1;
            if (v.amount > w.amount) return 1;
            if (v.amount == w.amount && v.index < w.index) return -1;
            if (v.amount == w.amount && v.index > w.index) return 1;
            return 0;
        }
    }
}
