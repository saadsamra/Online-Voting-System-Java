import java.util.*;
public class VoteHistory {
    class Node {
        String action;
        Node prev, next;

        Node(String action) {
            this.action = action;
        }
    }

    private Node head, tail;

    public void log(String action) {
        Node node = new Node(action);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }
    
        public void add(String voteRecord) {
    Node newNode = new Node(voteRecord);
    if (head == null) {
        head = tail = newNode;
    } else {
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }
}

    public List<String> getHistoryAsList() {
        List<String> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.action);
            current = current.next;
        }
        return list;
    }

    public void printHistory() {
        Node current = head;
        while (current != null) {
            System.out.println(current.action);
            current = current.next;
        }
    }

    public void printReverseHistory() {
        Node current = tail;
        while (current != null) {
            System.out.println(current.action);
            current = current.prev;
        }
    }
public void removeLast() {
        if (tail == null) return;
        if (tail == head) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
    }
    
}
