public class IntigerLinkedList {
    
    // Node class to represent individual elements
    private class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    private int size;
    
    // Constructor
    public IntLinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    // Insert at the beginning of the list
    public void insertAtHead(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
        size++;
    }
    
    // Insert at the end of the list
    public void insertAtTail(int data) {
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
    public void insertAtIndex(int index, int data) {
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
    public int deleteFromHead() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        
        int data = head.data;
        head = head.next;
        size--;
        return data;
    }
    
    // Delete from the end
    public int deleteFromTail() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        
        if (head.next == null) {
            int data = head.data;
            head = null;
            size--;
            return data;
        }
        
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        
        int data = current.next.data;
        current.next = null;
        size--;
        return data;
    }
    
    // Delete from specific index
    public int deleteAtIndex(int index) {
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
        
        int data = current.next.data;
        current.next = current.next.next;
        size--;
        return data;
    }
    
    // Delete by value (first occurrence)
    public boolean deleteByValue(int value) {
        if (head == null) {
            return false;
        }
        
        if (head.data == value) {
            head = head.next;
            size--;
            return true;
        }
        
        Node current = head;
        while (current.next != null && current.next.data != value) {
            current = current.next;
        }
        
        if (current.next != null) {
            current.next = current.next.next;
            size--;
            return true;
        }
        
        return false;
    }
    
    
    // Search for a value and return its index
    public int search(int value) {
        Node current = head;
        int index = 0;
        
        while (current != null) {
            if (current.data == value) {
                return index;
            }
            current = current.next;
            index++;
        }
        
        return -1; // Not found
    }
    
    // Get element at specific index
    public int get(int index) {
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
    public void set(int index, int data) {
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
    public boolean contains(int value) {
        return search(value) != -1;
    }
    
    // Count occurrences of a value
    public int countOccurrences(int value) {
        int count = 0;
        Node current = head;
        
        while (current != null) {
            if (current.data == value) {
                count++;
            }
            current = current.next;
        }
        
        return count;
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
    public int getFirst() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        return head.data;
    }
    
    // Get the last element
    public int getLast() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        
        return current.data;
    }
    
    // Find minimum value
    public int findMin() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        
        int min = head.data;
        Node current = head.next;
        
        while (current != null) {
            if (current.data < min) {
                min = current.data;
            }
            current = current.next;
        }
        
        return min;
    }
    
    // Find maximum value
    public int findMax() {
        if (head == null) {
            throw new RuntimeException("List is empty");
        }
        
        int max = head.data;
        Node current = head.next;
        
        while (current != null) {
            if (current.data > max) {
                max = current.data;
            }
            current = current.next;
        }
        
        return max;
    }
    
    // Calculate sum of all elements
    public int sum() {
        int sum = 0;
        Node current = head;
        
        while (current != null) {
            sum += current.data;
            current = current.next;
        }
        
        return sum;
    }
    
    // Calculate average of all elements
    public double average() {
        if (size == 0) {
            throw new RuntimeException("List is empty");
        }
        
        return (double) sum() / size;
    }
    
    // Convert to array
    public int[] toArray() {
        int[] array = new int[size];
        Node current = head;
        int index = 0;
        
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        
        return array;
    }
    
    // Sort the list (bubble sort)
    public void sort() {
        if (head == null || head.next == null) {
            return;
        }
        
        boolean swapped;
        do {
            swapped = false;
            Node current = head;
            
            while (current.next != null) {
                if (current.data > current.next.data) {
                    // Swap data
                    int temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
    }
    
    // Remove duplicates
    public void removeDuplicates() {
        if (head == null) {
            return;
        }
        
        Node current = head;
        while (current != null && current.next != null) {
            if (current.data == current.next.data) {
                current.next = current.next.next;
                size--;
            } else {
                current = current.next;
            }
        }
    }
    
    // Display the list
    public void display() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        
        Node current = head;
        System.out.print("IntLinkedList: ");
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
        IntLinkedList list = new IntLinkedList();
        
        // Test insertions
        list.insertAtHead(10);
        list.insertAtHead(5);
        list.insertAtTail(20);
        list.insertAtTail(30);
        list.insertAtIndex(2, 15);
        list.insertAtTail(5); // Duplicate for testing
        
        System.out.println("After insertions: " + list);
        System.out.println("Size: " + list.size());
        
        // Test mathematical operations
        System.out.println("Sum: " + list.sum());
        System.out.println("Average: " + list.average());
        System.out.println("Min: " + list.findMin());
        System.out.println("Max: " + list.findMax());
        
        // Test search and count
        System.out.println("Search 15: " + list.search(15));
        System.out.println("Contains 25: " + list.contains(25));
        System.out.println("Count of 5: " + list.countOccurrences(5));
        
        // Test get/set
        System.out.println("Element at index 2: " + list.get(2));
        list.set(2, 25);
        System.out.println("After setting index 2 to 25: " + list);
        
        // Test sorting
        list.sort();
        System.out.println("After sorting: " + list);
        
        // Test remove duplicates
        list.removeDuplicates();
        System.out.println("After removing duplicates: " + list);
        
        // Test deletions
        System.out.println("Deleted from head: " + list.deleteFromHead());
        System.out.println("Deleted from tail: " + list.deleteFromTail());
        System.out.println("After deletions: " + list);
        
        // Test reverse
        list.reverse();
        System.out.println("After reverse: " + list);
        
        // Test array conversion
        int[] array = list.toArray();
        System.out.print("As array: [");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        
        // Display final state
        list.display();
    }
}
