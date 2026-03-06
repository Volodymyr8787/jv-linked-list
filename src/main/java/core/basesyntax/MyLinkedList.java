package core.basesyntax;

import java.util.List;

public class MyLinkedList<T> implements MyLinkedListInterface<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public Node<T> getLast() {
        return last;
    }

    public Node<T> getFirst() {
        return first;
    }

    public int getSize() {
        return size;
    }

    public void setFirst(Node<T> first) {
        this.first = first;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setLast(Node<T> last) {
        this.last = last;
    }

    private class Node<E> {
        private item;
        private <E>next;
        private <E>prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(T value) {
        Node<T> newNode = new Node<>(last, value, null);

        if (last == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }

        last = newNode;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {
            add(value);
            return;
        }

        Node<T> nextNode = node(index);
        Node<T> prevNode = nextNode.prev;

        Node<T> newNode = new Node<>(prevNode, value, nextNode);

        nextNode.prev = newNode;

        if (prevNode == null) {
            first = newNode;
        } else {
            prevNode.next = newNode;
        }

        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    @Override
    public T set(T value, int index) {
        checkElementIndex(index);

        Node<T> node = node(index);

        T oldValue = node.item;

        node.item = value;

        return oldValue;

    }

    @Override
    public T remove(int index) {
        checkElementIndex(index);

        Node<T> node = node(index);

        Node<T> prev = node.prev;
        Node<T> next = node.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
        }

        size--;
        return node.item;
    }

    @Override
    public boolean remove(T object) {
        Node<T> current = first;

        while (current != null) {

            if (current.item.equals(object)) {
                unlink(current);
                return true;
            }

            current = current.next;
        }

        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<T> node(int index) {
        Node<T> current = first;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    private T unlink(Node<T> node) {
        Node<T> next = node.next;
        Node<T> prev = node.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
        }

        size--;
        final T element = node.item;
        return element;
    }
}

