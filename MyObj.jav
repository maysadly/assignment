public class MyList<T> {

    private Object[] elements;
    private int size;

    public MyList() {
        elements = new Object[10]; 
        size = 0;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            Object[] newElements = new Object[elements.length * 2];
            System.arraycopy(elements, 0, newElements, 0, size);
            elements = newElements;
        }
    }

    public void add(T item) {
        ensureCapacity();
        elements[size++] = item;
    }

    public void set(int index, T item) {
        if (index >= 0 && index < size) {
            elements[index] = item;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity();

        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = item;
        size++;
    }

    public void addFirst(T item) {
        add(0, item);
    }

    public void addLast(T item) {
        add(item);
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index >= 0 && index < size) {
            return (T) elements[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public T getFirst() {
        return get(0);
    }

    public T getLast() {
        return get(size - 1);
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
    }

    public void removeFirst() {
        remove(0);
    }

    public void removeLast() {
        remove(size - 1);
    }

    public void sort() {
        //бубле сорте
        boolean sorted;
        for (int i = 0; i < size - 1; i++) {
            sorted = true;
            for (int j = 0; j < size - i - 1; j++) {
                @SuppressWarnings("unchecked")
                Comparable<T> cj = (Comparable<T>) elements[j];
                if (cj.compareTo((T) elements[j + 1]) > 0) {
                    T temp = (T) elements[j];
                    elements[j] = elements[j + 1];
                    elements[j + 1] = temp;
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
    }

    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object object) {
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(object)) {
                return i;
            }
        }
        return -1;
    }

    public boolean exists(Object object) {
        return indexOf(object) >= 0;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(elements, 0, result, 0, size);
        return result;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    public int size() {
        return size;
    }
    
}


public class MyLinkedList<E> implements MyList<E> {

    private class MyNode {
        E element;
        MyNode next;
        MyNode prev;

        MyNode(E element) {
            this.element = element;
            this.next = null;
            this.prev = null;
        }
    }

    private MyNode head;
    private MyNode tail;
    private int size;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void add(E item) {
        MyNode newNode = new MyNode(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        MyNode newNode = new MyNode(item);
        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            addLast(item);
        } else {
            MyNode current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next.prev = newNode;
            current.next = newNode;
            newNode.prev = current;
            size++;
        }
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        MyNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.element;
    }

    public void set(int index, E item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        MyNode current = node(index);
        current.element = item;
    }

    public void addFirst(E item) {
        MyNode newNode = new MyNode(item);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void addLast(E item) {
        MyNode newNode = new MyNode(item);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        size++;
    }

    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.element;
    }

    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.element;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        MyNode current = node(index);
        E element = current.element;
        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            head = current.next;
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }
        size--;
        return element;
    }

    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return remove(0);
    }

    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return remove(size - 1);
    }

    private MyNode node(int index) {
        MyNode x;
        if (index < (size >> 1)) {
            x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }


    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (MyNode x = head; x != null; x = x.next) {
                if (x.element == null) return index;
                index++;
            }
        } else {
            for (MyNode x = head; x != null; x = x.next) {
                if (o.equals(x.element)) return index;
                index++;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (MyNode x = tail; x != null; x = x.prev) {
                index--;
                if (x.element == null) return index;
            }
        } else {
            for (MyNode x = tail; x != null; x = x.prev) {
                index--;
                if (o.equals(x.element)) return index;
            }
        }
        return -1;
    }

    public boolean exists(Object o) {
        return indexOf(o) >= 0;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (MyNode x = head; x != null; x = x.next)
            result[i++] = x.element;
        return result;
    }

    public void sort() {
        public void sort() {
            if (size > 1) {
                boolean wasChanged;
        
                do {
                    MyNode current = head;
                    MyNode previous = null;
                    MyNode next = head.next;
                    wasChanged = false;
        
                    while (next != null) {
                        if (((Comparable<E>) current.element).compareTo(next.element) > 0) {
                            wasChanged = true;
        
                            if (previous != null) {
                                MyNode sig = next.next;
        
                                previous.next = next;
                                next.next = current;
                                current.next = sig;
        
                                if (sig != null) {
                                    sig.prev = current;
                                }
                                next.prev = previous;
                                current.prev = next;
        
                                previous = next;
                                next = current.next;
                            } else {
                                MyNode sig = next.next;
        
                                head = next;
                                next.next = current;
                                current.next = sig;
        
                                if (sig != null) {
                                    sig.prev = current;
                                }
                                next.prev = null;
                                current.prev = next;
        
                                previous = head;
                                next = current.next;
                            }
                        } else {
                            previous = current;
                            current = next;
                            next = next.next;
                        }
                    }
                } while (wasChanged);
            }
        }
        
    }

    public void clear() {
        MyNode x = head;
        while (x != null) {
            MyNode next = x.next;
            x.element = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        head = tail = null;
        size = 0;
    }
    
    public int size() {
        return size;
    }
}


public class MyStack<E> {
    private MyArrayList<E> list = new MyArrayList<>();

    public void push(E item) {
        list.add(item);
    }

    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.remove(list.size() - 1);
    }

    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(list.size() - 1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}


public class MyQueue<E> {
    private MyLinkedList<E> list = new MyLinkedList<>();

    public void enqueue(E item) {
        list.addLast(item);
    }

    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return list.removeFirst();
    }

    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return list.getFirst();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }
}
