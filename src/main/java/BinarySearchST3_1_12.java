import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdOut;

public class BinarySearchST3_1_12<Item extends Comparable<Item>> {
    private Item[] items;
    private int n;
    private int cap;

    // 用一个数组存放所有数据，[0..cap-1] 存放key，[cap..n-1] 存放value
    public BinarySearchST3_1_12(int capacity)
    {
        items = (Item[]) new Comparable[2*capacity];
        cap = capacity;
    }

    public BinarySearchST3_1_12(Comparable[] keys)
    {
        n = keys.length;
        cap = keys.length;
        items = (Item[]) new Comparable[2*cap];
        Merge.sort(keys);   // 归并所有key后放入数组前半部分
        for (int i = 0; i < keys.length; i++)
            items[i] = (Item)keys[i];
    }

    public int size()
    {
        return n;
    }

    private int rank(Item key)
    {
        int lo = 0;
        int hi = n - 1;
        while (lo <= hi)
        {
            int mid = lo + (hi-lo) / 2;
            int cmp = key.compareTo(items[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }

        return lo;
    }

    public boolean isEmpty()
    {
        return n == 0;
    }

    public Item get(Item key)
    {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && items[i].compareTo(key) == 0) return items[i+cap];
        else  return null;
    }

    public void put(Item key, Item val)
    {
        int i = rank(key);
        if (i < n && items[i].compareTo(key) == 0)
        {
            items[i+cap] = val;
            return;
        }

        for (int j = n; j > i; j--)
        {
            items[j] = items[j-1];
            items[j+cap] = items[j+cap-1];
        }
        items[i] = key;
        items[i+cap] = val;
        n++;
    }

    public Item min() {return items[0];}
    public Item max() {return items[n-1];}

    public static void main(String[] args)
    {
        BinarySearchST3_1_12<Event> st = new BinarySearchST3_1_12<Event>(9);
        Event e1 = new Event("Chica");
        Event e2 = new Event("Phoenix");
        Event e3 = new Event("Houstonhah");
        Event e4 = new Event("Sea");

        st.put(e1, e1);
        st.put(e2, e2);
        st.put(e3, e3);
        st.put(e4, e4);

        // 测试最大最小，因为Event类中compare比的是字符串的大小，所以max应该是e3，min应该是e4
        StdOut.println(st.max() + ", " + st.get(st.max()));
        StdOut.println(st.min() + ", " + st.get(st.min()));

        Event[] events = {e1, e2, e3, e4};
        BinarySearchST3_1_12<Event> st2 = new BinarySearchST3_1_12<Event>(events);
        StdOut.println(st.max());
        StdOut.println(st.min());
    }
}
