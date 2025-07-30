public class GenericLinkedList<T> {
    
    // Node class to represent individual elements
    private class Node {
        T data;
        Node next;
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    private int size;
    
    // Constructor
    public LinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    // Insert at the beginning of the list
    public void insertAtHead(T data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }
    
    // Insert at the end of the list
    public void insertAtTail(T data) {
        Node newNode = new Node(data);
        
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    // Insert at specific index
    public void insertAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        if (index == 0) {
            insertAtHead(data);
            return;
        }
        
        Node newNode = new Node(data);
        Node current = head;
        
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }
    
    // Delete from the beginning
    public T deleteFromHead() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        
        T data = head.data;
        head = head.next;
        size--;
        return data;
    }
    
    // Delete from the end
    public T deleteFromTail() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        
        if (head.next == null) {
            T data = head.data;
            head = null;
            size--;
            return data;
        }
        
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        
        T data = current.next.data;
        current.next = null;
        size--;
        return data;
    }
    
    // Delete from specific index
    public T deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        if (index == 0) {
            return deleteFromHead();
        }
        
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        
        T data = current.next.data;
        current.next = current.next.next;
        size--;
        return data;
    }
    
    // Delete by value (first occurrence)
    public boolean deleteByValue(T value) {
        if (head == null) {
            return false;
        }
        
        if (head.data.equals(value)) {
            head = head.next;
            size--;
            return true;
        }
        
        Node current = head;
        while (current.next != null && !current.next.data.equals(value)) {
            current = current.next;
        }
        
        if (current.next != null) {
            current.next = current.next.next;
            size--;
            return true;
        }
        
        return false;
    }
    
    // Search for a value
    public int search(T value) {
        Node current = head;
        int index = 0;
        
        while (current != null) {
            if (current.data.equals(value)) {
                return index;
            }
            current = current.next;
            index++;
        }
        
        return -1; // Not found
    }
    
    // Get element at specific index
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        return current.data;
    }
    
    // Update element at specific index
    public void set(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        current.data = data;
    }
    
    // Check if list contains a value
    public boolean contains(T value) {
        return search(value) != -1;
    }
    
    // Get the size of the list
    public int size() {
        return size;
    }
    
    // Check if list is empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Clear the entire list
    public void clear() {
        head = null;
        size = 0;
    }
    
    // Reverse the linked list
    public void reverse() {
        Node prev = null;
        Node current = head;
        Node next = null;
        
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        head = prev;
    }
    
    // Get the first element
    public T getFirst() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        return head.data;
    }
    
    // Get the last element
    public T getLast() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        
        return current.data;
    }
    
    // Convert to array
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node current = head;
        int index = 0;
        
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        
        return array;
    }
    
    // Display the list
    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        
        Node current = head;
        System.out.print("LinkedList: ");
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println(" -> null");
    }
    
    // Override toString method
    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = head;
        
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        
        sb.append("]");
        return sb.toString();
    }
    
    // Main method for testing
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        
        // Test insertions
        list.insertAtHead(10);
        list.insertAtHead(5);
        list.insertAtTail(20);
        list.insertAtTail(30);
        list.insertAtIndex(2, 15);
        
        System.out.println("After insertions: " + list);
        System.out.println("Size: " + list.size());
        
        // Test search
        System.out.println("Search 15: " + list.search(15));
        System.out.println("Contains 25: " + list.contains(25));
        
        // Test get/set
        System.out.println("Element at index 2: " + list.get(2));
        list.set(2, 25);
        System.out.println("After setting index 2 to 25: " + list);
        
        // Test deletions
        System.out.println("Deleted from head: " + list.deleteFromHead());
        System.out.println("Deleted from tail: " + list.deleteFromTail());
        System.out.println("After deletions: " + list);
        
        // Test reverse
        list.reverse();
        System.out.println("After reverse: " + list);
        
        // Test first and last
        System.out.println("First element: " + list.getFirst());
        System.out.println("Last element: " + list.getLast());
        
        // Display final state
        list.display();
    }
}
