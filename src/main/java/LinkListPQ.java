import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.Queue;

public class LinkListPQ {
    private static class Node {
        int item;
        Node parent;
        Node left;
        Node right;

        public Node(int i)
        {
            item = i;
        }
    }

    private int size = 0;   // 优先队列大小

    private Queue<Node> leafs = new LinkedList<Node>();  // 用于保存所有可以添加孩子的节点的队列，按顺序排列
    private Node first;  // 有限队列首节点

    private Node deleteMin()     // 拿到最小节点并删除
    {
        size = size - 1;

        for (int i = 0; i < leafs.size() - 1; i++)   // 找出和最小节点交换的节点，也就是leafs队列中的第一个
        {
            Node leaf = leafs.remove();
            leafs.add(leaf);
        }
        Node removeItem = leafs.remove();
        // 若leafs队列无节点，则说明队列已空，直接返回首节点
        if (leafs.peek() == null)
        {
            return first;
        }
        // 删除相关的指针信息，包括子节点的parent指针和父节点的左子指针或者右子指针
        if (removeItem.parent.left == removeItem)
        {
            removeItem.parent.left = null;
        } else {
            removeItem.parent.right = null;
        }
        // 子节点被删除后，他的父节点有空位剩下，此时应该放进leafs队列的第一位
        if (leafs.peek() != removeItem.parent)
        {
            leafs.add(removeItem.parent);
            for (int i = 0; i < leafs.size() - 1; i++)
            {
                Node p1 = leafs.remove();
                leafs.add(p1);
            }
        }

        // 交换被删除的首节点和优先队列最后一个节点的值，并重新归位优先队列
        removeItem.parent = null;
        exch(removeItem, first);
        Node firstcp = first;
        sink(firstcp);
        return removeItem;
    }

    private void insert(Node n)
    {
        // 插入优先队列时直接找到leafs队列的第一个节点，然后将其左或者右子指针指向新节点
        if (first == null)
        {
            first = n;
            leafs.add(n);
        } else {
            if (leafs.peek().left == null)
                leafs.peek().left = n;
            else if (leafs.peek().right == null)
                leafs.peek().right = n;
            else {
                leafs.remove();
                leafs.peek().left = n;
            }
            n.parent = leafs.peek();
            leafs.add(n);
            swim(n);
        }
        if (leafs.peek().left != null && leafs.peek().right != null)
            leafs.remove();

        size = size + 1;
    }

    private void swim(Node n)
    {
        while (n.parent != null)
        {
            if (less(n, n.parent))
            {
                exch(n.parent, n);
                n = n.parent;
            } else
                break;
        }
    }

    private void sink(Node n)
    {
        while (n.left != null || n.right != null)
        {
            Node child;
            if (n.left == null)
                child = n.right;
            else if (n.right == null)
                child = n.left;
            else if (less(n.left, n.right))
                child = n.left;
            else
                child = n.right;
            if (less(n, child)) break;
            exch(n, child);
            n = child;
        }
    }

    private boolean less(Node a, Node b)
    {
        return a.item < b.item;
    }

    private void exch(Node a, Node b)
    {
        int t = a.item;
        a.item = b.item;
        b.item = t;
    }

    private boolean isEmpty()
    {
        return size == 0;
    }

    public static void main(String[] args)
    {
        LinkListPQ lstpq = new LinkListPQ();
        Node p1 = new Node(9);
        Node p2 = new Node(1);
        Node p3 = new Node(20);
        Node p4 = new Node(4);
        Node p5 = new Node(40);
        Node p6 = new Node(0);
        Node p7 = new Node(90);
        Node p8 = new Node(-10);
        Node[] a = {p1, p2, p3, p4, p5, p6, p7, p8};
        for (Node anA : a) {
            lstpq.insert(anA);
        }

        while (!lstpq.isEmpty())
        {
            Node minimal = lstpq.deleteMin();
            StdOut.println(minimal.item);
        }
    }
}
