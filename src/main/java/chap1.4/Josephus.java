import edu.princeton.cs.algs4.StdOut;

public class Josephus {
    private Node first;
    private Node last;
    private int N;

    private class Node {
        int item;
        Node next;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void killWho(int people) {
        first = new Node();
        first.item = 0;
        last = new Node();
        last.item = 1;
        first.next = last;
        last.next = first;
        for (int i = 2; i < people; i++) {
            Node oldlast = last;
            last = new Node();
            last.item = i;
            oldlast.next = last;
            last.next = first;
            N++;
        }
    }

    public void killOrder(int people, int killNum) {
        Node prefirst = new Node();
        prefirst.next = first;
        first = prefirst;
        for (int i = 0; i < people;  i++) {
            for (int j = 0; j < killNum-1; j++) {
                first = first.next;
            }

            Node current = first.next;
            StdOut.print(current.item);
            first.next = current.next;
            current.next = null;
            first = first.next;
        }
    }

    public static void main(String[] args) {
        Josephus josephus = new Josephus();
        josephus.killWho(7);
        josephus.killOrder(7, 5);  //大于4就错了
    }
}
