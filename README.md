# Data Structure Implementations

This repository contains Java implementations of three fundamental data structures: stack, queue, and min-heap, built on top of custom array list and linked list implementations.

## MyStack

A generic stack implementation (`MyStack`) using a dynamic array structure for storage. The stack operates on the LIFO (Last In, First Out) principle.

### Features

- `push(E item)`: Insert an element at the top of the stack.
- `pop()`: Remove the element at the top of the stack and return it.
- `peek()`: Look at the element at the top of the stack without removing it.
- `isEmpty()`: Check if the stack is empty.
- `size()`: Get the number of elements in the stack.

## MyQueue

A generic queue implementation (`MyQueue`) using a doubly linked list structure for storage. The queue operates on the FIFO (First In, First Out) principle.

### Features

- `enqueue(E item)`: Add an element to the end of the queue.
- `dequeue()`: Remove the element from the beginning of the queue and return it.
- `peek()`: Look at the element at the beginning of the queue without removing it.
- `isEmpty()`: Check if the queue is empty.
- `size()`: Get the number of elements in the queue.

## MyMinHeap

A generic min-heap implementation (`MyMinHeap`) using a dynamic array to represent the heap-ordered complete binary tree. The min-heap ensures the smallest key is always at the front.

### Features

- `add(E item)`: Add an element to the heap, maintaining the heap invariant.
- `remove()`: Remove the smallest element from the heap and return it, maintaining the heap invariant.
- `peek()`: Look at the smallest element without removing it.
- `isEmpty()`: Check if the heap is empty.
- `size()`: Get the number of elements in the heap.

## Usage

To use any of these data structures, instantiate the class with the type of elements you intend to store. For example:

```java
MyStack<Integer> stack = new MyStack<>();
MyQueue<String> queue = new MyQueue<>();
MyMinHeap<Double> minHeap = new MyMinHeap<>();
```


# Custom Data Structures in Java

This repository contains Java implementations of classic data structures built from scratch. It includes `MyArrayList`, `MyLinkedList`, `MyStack`, `MyQueue`, and `MyMinHeap`.

## MyArrayList

A generic dynamic array implementation that allows elements to be added and removed efficiently from the end of the list and ensures access to elements in constant time.

### Features

- Dynamic resizing for efficient use of space
- Constant-time access to elements
- Methods for adding, removing, and getting elements

## MyLinkedList

A generic doubly linked list implementation allowing constant-time insertions and deletions at both ends of the list, and efficient iteration.

### Features

- Constant-time insertions and deletions
- Ability to access elements sequentially
- Methods for adding, removing, and getting elements from both ends

## MyStack

A LIFO (Last In, First Out) structure implemented using `MyArrayList`, optimized for push and pop operations.

## MyQueue

A FIFO (First In, First Out) structure implemented using `MyLinkedList`, optimized for enqueue and dequeue operations.

## MyMinHeap

A min-heap structure implemented using `MyArrayList`, providing efficient access to the smallest element.

## Usage

Each data structure can be instantiated and used with any Object type:

```java
MyArrayList<Integer> arrayList = new MyArrayList<>();
MyLinkedList<String> linkedList = new MyLinkedList<>();
MyStack<Double> stack = new MyStack<>();
MyQueue<Character> queue = new MyQueue<>();
MyMinHeap<Person> minHeap = new MyMinHeap<>();
