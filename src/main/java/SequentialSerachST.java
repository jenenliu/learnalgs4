public class SequentialSerachST<Key, Value>
{
    private Node first;    // first node in the linked list
    private Node recentNode;  // 用户保存最新获取的节点

    private class Node
    {
        // linked-list node
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next)
        {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key)
    {
        if (recentNode.key != key)   // 如果要get的节点不是最新保存的节点，则更新最新保存的节点
            recentNode.key = key;
        else
            return recentNode.val;  // 如果get的节点是最新保存的节点，则直接拿数据，不用再次寻找
        // Search for key, return associated value
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.val;     // search hit
        return null;            // search miss
    }

    public void put(Key key, Value val)
    {
        if (recentNode.key != null)  // 和上面一样的操作，其实如果遇到像FrequencyCounter那种
            recentNode.key = key;    // 搜索到节点后，又更新节点的，就不需要再次搜索，直接用
        else                         // recentkey的值
            recentNode.val = val;
        // Search for keys. Update value if found; grow table if new.
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
            {
                x.val = val;                // Search hit: update val
                return;
            }
        first = new Node(key, val, first);  // Search miss: add new node
    }
}
