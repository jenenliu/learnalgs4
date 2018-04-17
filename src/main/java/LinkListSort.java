// sort link list using natural merge sort

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class LinkListSort {
    private class Node {
        int item;
        Node next;
    }

    private Node randomlink() {
        Node first = null;
        Node tmp;
        int[] a = {10, 8, 6, 4, 1, 90, 70, 7, 5, 2};
        for (int i = 0; i < 10; i++) {
            tmp = new Node();
//            tmp.item = StdRandom.uniform(10);
            tmp.item = a[i];
            tmp.next = first;
            first = tmp;
        }
        return first;
    }

    private void showLink(Node node) {
        Node p = node;
        while (p != null) {
            StdOut.print(p.item + ", ");
            p = p.next;
        }
        StdOut.println();
    }

    private Node merge2(Node lo, Node mid, Node hi)
    {
        Node p1start = lo;
        Node p2start = mid.next;

        Node pcurrent = new Node();
        Node pfirst = null;

        int count = 0;
        for (Node p = lo; p != hi.next; p = p.next)
            ++count;

        for (int i = 0; i < count; i++) {
            if (p1start == mid.next) {
                pcurrent.next = p2start;
                break;
            }

            if (p2start == hi.next)
            {
                mid.next = hi.next;
                pcurrent.next = p1start;
                break;
            }

            if (p1start.item <= p2start.item)
            {
                pcurrent.next = p1start;
                if (pfirst == null)
                    pfirst = p1start;
                pcurrent = pcurrent.next;
                p1start = p1start.next;
            } else {
                pcurrent.next = p2start;
                if (pfirst == null)
                    pfirst = p2start;
                pcurrent = pcurrent.next;
                p2start = p2start.next;
            }
        }
        return pfirst;
    }

    private Node merge(Node lo, Node mid, Node hi)
    {
        Node p1start = lo;
        Node p2start = mid.next;

        Node pcurrent = null;
        Node pfirst = null;

        int count = 0;
        for (Node p = lo; p != hi.next; p = p.next)
            ++count;

        for (int i = 0; i < count; i++) {
            if (p1start == mid.next) {
                if (pcurrent != null)
                    pcurrent.next = p2start;
                break;
            }

            if (p2start == hi.next)
            {
                if (pcurrent != null)
                {
                    mid.next = hi.next;
                    pcurrent.next = p1start;
                }
                break;
            }

            if (p1start.item <= p2start.item)
            {
                if (pcurrent != null)
                {
                    pcurrent.next = p1start;
                    pcurrent = pcurrent.next;
                } else {
                    pcurrent = p1start;
                    pfirst = pcurrent;
                }
                p1start = p1start.next;
            } else {
                if (pcurrent != null)
                {
                    pcurrent.next = p2start;
                    pcurrent = pcurrent.next;
                } else {
                    pcurrent = p2start;
                    pfirst = pcurrent;
                }
                p2start = p2start.next;
            }
        }
        return pfirst;
    }

    public void sort(Node node)
    {
    }

    public static void main(String[] args)
    {
        LinkListSort ls = new LinkListSort();
        Node p = ls.randomlink();
        ls.showLink(p);
        Node mid = p.next.next.next.next;
        StdOut.println(mid.item);
        Node hi = mid.next.next.next.next.next;
        StdOut.println(hi.item);
        Node pfirst = ls.merge2(p, mid, hi);
        ls.showLink(pfirst);
    }
}
