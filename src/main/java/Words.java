import java.util.Comparator;

public class Words {
    private String word;
    private int count;

    public Words(String w, int c)
    {
        word = w;
        count = c;
    }

    public String getWord()
    {
        return word;
    }

    public int getCount()
    {
        return count;
    }

    public void addOne()
    {
        count = count + 1;
    }

    public static class WordsOrder implements Comparator<Words> {
        public int compare(Words a, Words b)
        {
            if (a.count < b.count) return -1;
            if (a.count > b.count) return 1;
            return 0;
        }
    }
}
