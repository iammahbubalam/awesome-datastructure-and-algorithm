# Big O Notation and Algorithm Complexity Analysis

Welcome to the comprehensive guide on algorithm complexity analysis! This guide will teach you everything you need to know about analyzing the efficiency of algorithms.

## 📚 Table of Contents
- [Big O Notation](#big-o-notation)
- [Understanding O, Ω, Θ Notations](#understanding-o-ω-θ-notations)
- [Best, Average, Worst Case Analysis](#best-average-worst-case-analysis)
- [Amortized Analysis](#amortized-analysis)
- [Space Complexity](#space-complexity)
- [Common Complexity Classes](#common-complexity-classes)

## 🎯 Big O Notation

Big O notation is a mathematical way to describe how the runtime or space requirements of an algorithm grow as the input size increases. Think of it as a way to measure algorithm efficiency.

### Why Do We Need Big O?

Imagine you're comparing two recipes:
- Recipe A: Takes 5 minutes for 1 person, 10 minutes for 2 people, 15 minutes for 3 people
- Recipe B: Takes 2 minutes for 1 person, 4 minutes for 2 people, 8 minutes for 3 people

Which is better for a large dinner party? Recipe A grows linearly, Recipe B grows exponentially. Big O helps us express this mathematically.

### Key Principles

1. **Focus on the dominant term**: For `3n² + 2n + 1`, we only care about `n²`
2. **Ignore constants**: `5n` and `100n` are both `O(n)`
3. **Consider worst-case scenarios** (unless specified otherwise)

```
ALGORITHM FindMaximum(array):
    max_value ← array[0]                    // O(1)
    FOR i ← 1 to length(array) - 1 DO      // O(n)
        IF array[i] > max_value THEN       // O(1)
            max_value ← array[i]           // O(1)
    RETURN max_value                       // O(1)

// Overall: O(1) + O(n) × O(1) = O(n)
```

## 🔍 Understanding O, Ω, Θ Notations

These three notations give us different perspectives on algorithm performance:

### Big O (O) - Upper Bound
"At most this fast" - describes the worst-case scenario.

```
f(n) = O(g(n)) means f(n) ≤ c × g(n) for large n
```

**Example**: Linear search in an array
- Best case: Item is first → O(1)
- Big O: O(n) (represents worst case)

### Big Omega (Ω) - Lower Bound
"At least this fast" - describes the best-case scenario.

```
f(n) = Ω(g(n)) means f(n) ≥ c × g(n) for large n
```

**Example**: Linear search
- Ω(1) - best case when item is found first

### Big Theta (Θ) - Tight Bound
"Exactly this fast" - when upper and lower bounds are the same.

```
f(n) = Θ(g(n)) means c₁ × g(n) ≤ f(n) ≤ c₂ × g(n)
```

**Example**: Printing all elements in an array
- Always takes exactly n operations → Θ(n)

### Visual Comparison

```
      Time
        ↑
        |     O(g(n)) ← Upper bound
        |   ∙∙∙∙∙∙∙∙∙
        | ∙∙∙ f(n) ∙∙∙  ← Actual function
        |∙∙∙∙∙∙∙∙∙∙∙∙
    Ω(g(n)) ← Lower bound
        |________________→ Input size (n)
```

## 📊 Best, Average, Worst Case Analysis

Understanding different scenarios helps us make informed decisions about algorithm choice.

### Example: Quick Sort Analysis

```
ALGORITHM QuickSort(array):
    IF length(array) ≤ 1 THEN
        RETURN array
    
    pivot ← array[middle_index]
    left ← all elements < pivot
    middle ← all elements = pivot  
    right ← all elements > pivot
    
    RETURN QuickSort(left) + middle + QuickSort(right)
```

**Best Case**: Pivot always divides array equally
- Time: O(n log n)
- Happens when pivot is always the median

**Average Case**: Random pivot selection
- Time: O(n log n)
- Most practical scenarios

**Worst Case**: Pivot is always smallest/largest
- Time: O(n²)
- Happens with already sorted arrays (poor pivot choice)

### When Each Case Matters

| Scenario | Use Case |
|----------|----------|
| **Best Case** | Optimistic planning, best possible performance |
| **Average Case** | Real-world performance expectations |
| **Worst Case** | Critical systems, guarantees needed |

## ⚡ Amortized Analysis

Amortized analysis looks at the average performance over a sequence of operations, not just individual operations.

### Dynamic Array Example

```
CLASS DynamicArray:
    capacity ← 1
    size ← 0
    data ← array of size capacity

    OPERATION Append(item):
        IF size = capacity THEN
            Resize()                    // Expensive operation
        
        data[size] ← item
        size ← size + 1
    
    OPERATION Resize():
        old_capacity ← capacity
        capacity ← capacity × 2         // Double the size
        new_data ← array of size capacity
        
        FOR i ← 0 to old_capacity - 1 DO    // O(n)
            new_data[i] ← data[i]
        
        data ← new_data
```

**Individual Operations**:
- Normal append: O(1)
- Resize append: O(n)

**Amortized Analysis**:
- Over n operations, resizing happens at: 1, 2, 4, 8, 16, ..., n
- Total resize cost: 1 + 2 + 4 + ... + n ≈ 2n
- Amortized cost per operation: 2n/n = O(1)

### Types of Amortized Analysis

1. **Aggregate Method**: Total cost ÷ number of operations
2. **Accounting Method**: Assign costs to operations
3. **Potential Method**: Use potential function to smooth costs

## 💾 Space Complexity

Space complexity measures how much extra memory an algorithm uses relative to input size.

### Types of Space Usage

1. **Input Space**: Memory for input data (usually not counted)
2. **Auxiliary Space**: Extra memory used by algorithm
3. **Total Space**: Input + Auxiliary space

### Examples

```
// O(1) Space - Constant extra memory
ALGORITHM SumArray(array):
    total ← 0                      // One variable regardless of input size
    FOR each num IN array DO
        total ← total + num
    RETURN total

// O(n) Space - Linear extra memory
ALGORITHM ReverseArray(array):
    result ← empty array           // New array of same size as input
    FOR i ← length(array) - 1 down to 0 DO
        result.append(array[i])
    RETURN result

// O(log n) Space - Logarithmic (recursive call stack)
ALGORITHM BinarySearch(array, target, left, right):
    IF left > right THEN
        RETURN -1
    
    mid ← (left + right) / 2
    IF array[mid] = target THEN
        RETURN mid
    ELSE IF array[mid] < target THEN
        RETURN BinarySearch(array, target, mid + 1, right)
    ELSE
        RETURN BinarySearch(array, target, left, mid - 1)
```

### Space-Time Tradeoffs

Often we can trade space for time or vice versa:

```
// Time: O(n²), Space: O(1)
ALGORITHM HasDuplicateSlow(array):
    FOR i ← 0 to length(array) - 1 DO
        FOR j ← i + 1 to length(array) - 1 DO
            IF array[i] = array[j] THEN
                RETURN true
    RETURN false

// Time: O(n), Space: O(n)
ALGORITHM HasDuplicateFast(array):
    seen ← empty set               // Extra space
    FOR each num IN array DO
        IF num IN seen THEN
            RETURN true
        seen.add(num)
    RETURN false
```

## 🚀 Common Complexity Classes

Let's explore the most common complexity classes you'll encounter, from fastest to slowest.

### O(1) - Constant Time

**Definition**: Performance doesn't change with input size.

**Characteristics**:
- Always takes the same time
- Most efficient possible
- Independent of input size

```
// Array access
ALGORITHM GetFirstElement(array):
    RETURN array[0]                // Always one operation

// Hash table operations (average case)
ALGORITHM HashLookup(hash_table, key):
    RETURN hash_table[key]         // Direct access

// Mathematical operations
ALGORITHM AddNumbers(a, b):
    RETURN a + b                   // Single operation
```

**Real-world examples**:
- Accessing array element by index
- Push/pop from stack
- Hash table lookup (average case)

### O(log n) - Logarithmic Time

**Definition**: Performance grows logarithmically with input size.

**Characteristics**:
- Very efficient for large datasets
- Often involves dividing problem in half
- Common in tree/search operations

```
// Binary search
ALGORITHM BinarySearch(sorted_array, target):
    left ← 0
    right ← length(sorted_array) - 1
    
    WHILE left ≤ right DO
        mid ← (left + right) / 2
        IF sorted_array[mid] = target THEN
            RETURN mid
        ELSE IF sorted_array[mid] < target THEN
            left ← mid + 1
        ELSE
            right ← mid - 1
    
    RETURN -1

// Tree height in balanced binary tree
// Height = log₂(n) where n is number of nodes
```

**Growth pattern**:
```
n = 1    → log n = 0
n = 10   → log n ≈ 3.3
n = 100  → log n ≈ 6.6
n = 1000 → log n ≈ 10
```

**Real-world examples**:
- Binary search in sorted array
- Operations in balanced binary search tree
- Finding element in heap

### O(n) - Linear Time

**Definition**: Performance grows linearly with input size.

**Characteristics**:
- Most common complexity
- Usually involves examining each element once
- Reasonable for most practical purposes

```
// Linear search
ALGORITHM LinearSearch(array, target):
    FOR i ← 0 to length(array) - 1 DO
        IF array[i] = target THEN
            RETURN i
    RETURN -1

// Sum all elements
ALGORITHM SumArray(array):
    total ← 0
    FOR each num IN array DO
        total ← total + num
    RETURN total

// Find maximum
ALGORITHM FindMax(array):
    max_val ← array[0]
    FOR each num IN array[1..n] DO
        IF num > max_val THEN
            max_val ← num
    RETURN max_val
```

**Real-world examples**:
- Reading through a list once
- Simple array operations
- Linear search

### O(n log n) - Linearithmic Time

**Definition**: Performance grows as n times log n.

**Characteristics**:
- Common in efficient sorting algorithms
- Often involves divide-and-conquer
- Good balance of efficiency and simplicity

```
// Merge sort
ALGORITHM MergeSort(array):
    IF length(array) ≤ 1 THEN
        RETURN array
    
    mid ← length(array) / 2
    left ← MergeSort(array[0..mid-1])     // Divide: log n levels
    right ← MergeSort(array[mid..n-1])    // Divide: log n levels
    
    RETURN Merge(left, right)             // Conquer: O(n) at each level

ALGORITHM Merge(left, right):
    result ← empty array
    i ← 0, j ← 0
    
    WHILE i < length(left) AND j < length(right) DO
        IF left[i] ≤ right[j] THEN
            result.append(left[i])
            i ← i + 1
        ELSE
            result.append(right[j])
            j ← j + 1
    
    // Add remaining elements
    result.append(left[i..])
    result.append(right[j..])
    RETURN result
```

**Real-world examples**:
- Merge sort, heap sort, quick sort (average case)
- Building search indexes
- Fast Fourier Transform

### O(n²) - Quadratic Time

**Definition**: Performance grows quadratically with input size.

**Characteristics**:
- Often involves nested loops
- Can become slow for large inputs
- Sometimes unavoidable for certain problems

```
// Bubble sort
ALGORITHM BubbleSort(array):
    n ← length(array)
    FOR i ← 0 to n - 1 DO              // Outer loop: n times
        FOR j ← 0 to n - i - 2 DO      // Inner loop: n times
            IF array[j] > array[j + 1] THEN
                SWAP array[j] and array[j + 1]

// Finding all pairs
ALGORITHM FindAllPairs(array):
    pairs ← empty list
    FOR i ← 0 to length(array) - 1 DO     // n times
        FOR j ← i + 1 to length(array) - 1 DO  // n times
            pairs.append((array[i], array[j]))
    RETURN pairs
```

**Growth impact**:
```
n = 10   → n² = 100
n = 100  → n² = 10,000
n = 1000 → n² = 1,000,000
```

**Real-world examples**:
- Simple sorting algorithms (bubble, insertion, selection)
- Comparing all pairs in a dataset
- Some graph algorithms

### O(n³) - Cubic Time

**Definition**: Performance grows cubically with input size.

**Characteristics**:
- Often involves three nested loops
- Quickly becomes impractical for large inputs
- Sometimes necessary for complex problems

```
// Floyd-Warshall algorithm (shortest paths)
ALGORITHM FloydWarshall(graph):
    n ← number of vertices
    dist ← copy of graph
    
    FOR k ← 0 to n - 1 DO
        FOR i ← 0 to n - 1 DO
            FOR j ← 0 to n - 1 DO
                dist[i][j] ← min(dist[i][j], dist[i][k] + dist[k][j])
    
    RETURN dist

// Matrix multiplication (naive approach)
ALGORITHM MatrixMultiply(A, B):
    n ← size of matrices
    C ← matrix of size n×n filled with zeros
    
    FOR i ← 0 to n - 1 DO
        FOR j ← 0 to n - 1 DO
            FOR k ← 0 to n - 1 DO      // Triple nested loop = O(n³)
                C[i][j] ← C[i][j] + A[i][k] × B[k][j]
    
    RETURN C
```

**Real-world examples**:
- Matrix chain multiplication
- All-pairs shortest path algorithms
- Some dynamic programming solutions

### O(2ⁿ) - Exponential Time

**Definition**: Performance doubles with each additional input element.

**Characteristics**:
- Extremely slow growth
- Usually indicates brute force approach
- Often solvable with dynamic programming

```
// Fibonacci (naive recursive)
ALGORITHM FibonacciSlow(n):
    IF n ≤ 1 THEN
        RETURN n
    RETURN FibonacciSlow(n - 1) + FibonacciSlow(n - 2)

// Subset generation
ALGORITHM GenerateSubsets(array):
    IF array is empty THEN
        RETURN [[]]
    
    subsets_without_first ← GenerateSubsets(array[1..n])
    subsets_with_first ← []
    
    FOR each subset IN subsets_without_first DO
        subsets_with_first.append([array[0]] + subset)
    
    RETURN subsets_without_first + subsets_with_first

// Tower of Hanoi
ALGORITHM HanoiMoves(n):
    IF n = 1 THEN
        RETURN 1
    RETURN 2 × HanoiMoves(n - 1) + 1      // 2^n - 1 total moves
```

**Growth explosion**:
```
n = 10 → 2^n = 1,024
n = 20 → 2^n = 1,048,576
n = 30 → 2^n = 1,073,741,824
```

**Real-world examples**:
- Naive recursive solutions
- Brute force password cracking
- Generating all possible combinations

### O(n!) - Factorial Time

**Definition**: Performance grows factorially with input size.

**Characteristics**:
- Extremely slow, even for small inputs
- Often involves generating all permutations
- Usually indicates need for optimization

```
// Generate all permutations
ALGORITHM GeneratePermutations(array):
    IF length(array) ≤ 1 THEN
        RETURN [array]
    
    permutations ← empty list
    FOR i ← 0 to length(array) - 1 DO
        element ← array[i]
        remaining ← array without element at index i
        
        FOR each perm IN GeneratePermutations(remaining) DO
            permutations.append([element] + perm)
    
    RETURN permutations

// Traveling Salesman Problem (brute force)
ALGORITHM TSPBruteForce(cities, distances):
    min_distance ← infinity
    best_path ← null
    
    // Try all possible permutations of cities
    FOR each path IN GeneratePermutations(cities[1..n]) DO  // O(n!)
        distance ← CalculatePathDistance([cities[0]] + path, distances)
        IF distance < min_distance THEN
            min_distance ← distance
            best_path ← path
    
    RETURN best_path, min_distance
```

**Growth catastrophe**:
```
n = 5  → n! = 120
n = 10 → n! = 3,628,800
n = 15 → n! = 1,307,674,368,000
```

## 📈 Complexity Comparison Chart

| Input Size | O(1) | O(log n) | O(n) | O(n log n) | O(n²) | O(2ⁿ) | O(n!) |
|------------|------|----------|------|------------|-------|--------|-------|
| 1          | 1    | 0        | 1    | 0          | 1     | 2      | 1     |
| 10         | 1    | 3        | 10   | 33         | 100   | 1,024  | 3.6M  |
| 100        | 1    | 7        | 100  | 664        | 10K   | 1.2×10³⁰ | 9.3×10¹⁵⁷ |
| 1,000      | 1    | 10       | 1K   | 9,966      | 1M    | ∞      | ∞     |

## 🎯 Key Takeaways

1. **O(1), O(log n), O(n)**: Generally acceptable for most applications
2. **O(n log n)**: Good for sorting and divide-and-conquer algorithms
3. **O(n²), O(n³)**: Acceptable for small datasets, concerning for large ones
4. **O(2ⁿ), O(n!)**: Usually indicate need for optimization

## 🔗 Next Steps

Now that you understand complexity analysis, you can:
- Analyze your own algorithms
- Make informed decisions about algorithm choice
- Optimize code based on performance requirements
- Understand why certain data structures are preferred

Remember: The best algorithm depends on your specific use case, data size, and performance requirements!
4. **O(2ⁿ), O(n!)**: Usually indicate need for optimization

## 🔗 Next Steps

Now that you understand complexity analysis, you can:
- Analyze your own algorithms
- Make informed decisions about algorithm choice
- Optimize code based on performance requirements
- Understand why certain data structures are preferred

Remember: The best algorithm depends on your specific use case, data size, and performance requirements!
