public class LinkedListDeque<T> {
    private static class Node<T> {
        Node prevNode;
        T item;
        Node nextNode;
        Node(T item, Node pNode, Node nNode) {
            this.item = item;
            this.nextNode = nNode;
            this.prevNode = pNode;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        size  = 0;
        sentinel = new Node(null, null, null);
        sentinel.nextNode = sentinel.prevNode = sentinel;
    }

    public LinkedListDeque(T item) {
        this();
        addFirst(item);
    }

    public void addFirst(T item) {
        size++;
        sentinel.nextNode = new Node(item, sentinel, sentinel.nextNode);
        sentinel.nextNode.nextNode.prevNode = sentinel.nextNode;
    }

    public void addLast(T item) {
        size++;
        sentinel.prevNode = new Node(item, sentinel.prevNode, sentinel);
        sentinel.prevNode.prevNode.nextNode = sentinel.prevNode;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return this.size;
    }

    private String printDeque(Node p) {
        if (p.nextNode == sentinel) {
            return p.item.toString();
        }
        return p.item + " " + printDeque(p.nextNode);
    }

    public void printDeque() {
        System.out.println(printDeque(sentinel.nextNode));
    }

    public T removeFirst() {
        if (size != 0) {
            size--;
        }
        sentinel.nextNode = sentinel.nextNode.nextNode;
        sentinel.nextNode.prevNode = sentinel;
        return (T) sentinel.nextNode.item;
    }

    public T removeLast() {
        if (size != 0) {
            size--;
        }
        sentinel.prevNode = sentinel.prevNode.prevNode;
        sentinel.prevNode.nextNode = sentinel;
        return (T) sentinel.prevNode.item;
    }

    public T get(int index) {
        if (index > size || index < 0) {
            return null;
        }
        Node pointerNode = sentinel;
        for (int i = 0; i <= index; i++) {
            pointerNode = pointerNode.nextNode;
        }
        return (T) pointerNode.item;
    }

    private T getRecursive(Node node, int index) {
        if (index == 0) {
            return (T) node.item;
        }
        return getRecursive(node.nextNode, index - 1);
    }

    public T getRecursive(int index) {
        if (index > size || index < 0) {
            return null;
        }
        return getRecursive(sentinel.nextNode, index);
    }

    public static void main(String[] args) {
        LinkedListDeque<String> list = new LinkedListDeque<>();
        list.addFirst("Zero");
        list.addLast("One");
        list.addLast("Two");
        list.addLast("Three");
        list.addLast("Four");
        list.addLast("Five");
        list.addLast("Six");
        System.out.println(list.getRecursive(5));
        list.printDeque();
    }
}
