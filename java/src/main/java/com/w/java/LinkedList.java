package com.w.java;


public class LinkedList<T> {

    private Node<T> head = null;

    public void add(T newItem) {
        Node newNode = new Node(newItem);
        if (null == head) {
            head = newNode;
        } else {
            Node<T> tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = newNode;
        }
    }

    public void display() {
        Node<T> node = head;
        int i = 0;
        while (node != null) {
            System.out.print(String.format("(%d=%d)->", i++, node.item));
            node = node.next;
        }
        System.out.println();
    }

    /**
     * 循环方式转置
     */
    public void reverse() {
        Node curr = head;
        Node reve = null;
        while (curr != null) {
            Node temp = curr;
            curr = curr.next;
            temp.next = reve;
            reve = temp;
        }
        head = reve;
    }

    /**
     * 递归的方式转置
     */
    public Node<T> reverse(Node<T> head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node<T> tail = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return tail;
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(3);
        linkedList.add(7);
        linkedList.add(6);

        System.out.println("添加元素后");
        linkedList.display();

        System.out.println("循环方式转置后");
        linkedList.reverse();
        linkedList.display();

        System.out.println("递归方式转置后");
        linkedList.head = linkedList.reverse(linkedList.head);
        linkedList.display();
    }

    class Node<T> {
        public T item;
        public Node<T> next;

        public Node(T item) {
            this.item = item;
        }
    }
}
