# Assignment 3 – Linked Lists

In this assignment you will write Java methods that carry out a series of tasks on linked lists. Make sure you're caught up with the lecture material on linked lists before you begin. You'll be applying the concepts we've learned and practiced in a more extensive example.

You are free to design your solution however you choose, as long as it meets the specifications below. There are several valid ways to solve each task.

**Before you write any code, complete `worksheet.md`.** It walks you through a short design document for the linked list: the problem, the operations, the invariant, and the edge cases you expect. Read the rest of this README first, since the worksheet draws on it.

## Overview of the Java files

You are given three Java classes (`Main`, `LinkedList`, and `NodeImpl`) and one interface (`Node`).

### `Main.java`

The main user program. It shows how to create linked lists and manipulate them using existing `LinkedList` methods such as `add`, `toString`, `size`, `contains`, and `remove`. The `toString` method makes it easy to display the contents of a list. It also calls each of the methods you will write, so you can check your work.

**Run `Main` first** and make sure you understand the results of the methods that already exist.

Once all six tasks are complete, the task lines of the output should be:

```
list1 = 1 -> 2
Task 1: list after simpleMerge with list1 = 1 -> 2 -> 10 -> 10 -> 30
Task 2: list after removing element at index 1 = 1 -> 10 -> 10 -> 30
list2 = 100 -> 200
Task 3: list == list2 ?: false
list3 = 1 -> 10 -> 10 -> 30
Task 3: list == list3 ?: true
list before removing repeats = 1 -> 10 -> 10 -> 30
Task 4: list after removing repeats = 1 -> 10 -> 30
list before reversing = 1 -> 10 -> 30 -> 40 -> 50 -> 60
Task 5: list after reversing = 60 -> 50 -> 40 -> 30 -> 10 -> 1
list before merging = 60 -> 50 -> 40 -> 30 -> 10 -> 1
list2 before merging = 100 -> 200
Task 6: list after merging = 100 -> 60 -> 200 -> 50 -> 40 -> 30 -> 10 -> 1
```

### `LinkedList.java`

The linked list class, built on `NodeImpl`.

**Methods that are already implemented:**

| | | |
|---|---|---|
| `size` | `isEmpty` | `clear` |
| `contains` | `toArray` | `add(int element)` |
| `remove` | `get` | `set` |
| `add(int index, int element)` | `indexOf` | `lastIndexOf` |
| `validIndex` | `gethead` | `toString` |

**Methods you will implement:**

- `simpleMerge`
- `removeAtIndex`
- `isEqual`
- `removeRepeats`
- `reverse`
- `merge`

### `Node.java`

The interface for nodes. `Node` represents an individual node in the linked list.

### `NodeImpl.java`

An implementation of the `Node` interface. All of its methods are already implemented:

- `getValue` / `setValue`
- `getNext` / `setNext`
- `hasNext`

## Rules

- Write your code in the required methods at the **top** of `LinkedList.java`. If you want helper methods, add them as `private` methods in the same area, above the "Do not modify below this line" comment.
- **Do not change** any of the completed methods at the bottom of `LinkedList.java`.
- **Do not modify** `Node.java` or `NodeImpl.java`.
- You may change `Main.java` however you like to test your code. It is not graded, but it must still compile.
- Do not return a new list. Modify the list object that the method is called on.
- Keep `_head`, `_tail`, and `_size` accurate. After each of your methods runs, `size()` must return the correct size and `add` must still append to the end of the list. The autograder checks both.
- Because your methods are inside the `LinkedList` class, you can use another list's private fields directly (for example, `list2._head`), or you can use its public methods such as `gethead()`, `get`, and `size`.
- You can assume `list2` is never `null`.

Before you start, read through the existing methods and understand how they work. You can reuse them to avoid writing unnecessary code.

## Tasks

The tasks generally increase in difficulty. An example of input and expected output is given for each one.

### Task 1: Simple Merge

Complete `simpleMerge(LinkedList list2)`. It merges `list2` into the current list by adding it to the **start** of the current list.

After merging, `list2` should be **empty**. Its nodes now belong to the current list, so call `list2.clear()` when you're done.

Watch out for these cases:

- If `list2` is empty, the current list doesn't change.
- If the current list is empty, the result is just the elements of `list2`. Make sure `_tail` points at the last node.

```
List:  1 -> 2 -> 3
List2: 4 -> 5 -> 6
simpleMerge(List2)
Result: 4 -> 5 -> 6 -> 1 -> 2 -> 3
List2 afterward: (empty)
```

### Task 2: Remove the Node at a Given Index

Complete `removeAtIndex(int i)`. It deletes the node at the given index of the list.

If the index is not a valid index in the list (less than 0, or equal to or greater than the size of the list), throw an exception:

```java
throw new IndexOutOfBoundsException();
```

**Hints:** You can traverse the list the way methods such as `contains` and `get` do, or you can use the existing `get` and `remove` methods. Make sure boundary conditions work, such as removing the first element at index 0 or the last element.

```
List: 1 -> 4 -> 2
removeAtIndex(1)
Result: 1 -> 2

List: 1 -> 4 -> 2
removeAtIndex(0)
Result: 4 -> 2

List: 1 -> 4 -> 2
removeAtIndex(3)
Result: IndexOutOfBoundsException
```

### Task 3: Are Two Linked Lists Equal?

Complete `isEqual(LinkedList list2)`. Return `true` if this list is equal to `list2`, and `false` otherwise. Two empty lists are equal.

**Hints:** Two lists are equal if they have the same size and the same elements in the same order. Use `size` to get the size of a list and `get` to get a value. At this stage, the complexity of your algorithm does not matter.

```
List:  1 -> 4 -> 2
List2: 1 -> 4 -> 2
isEqual(List2)
Result: true

List:  1 -> 5
List2: 2 -> 5
isEqual(List2)
Result: false
```

### Task 4: Find Redundant Nodes and Remove Them

Complete `removeRepeats()`. It removes duplicate values from a **sorted** linked list. You can assume the list is always sorted.

**Tip:** Because the list is sorted, duplicate nodes will be next to each other. Move forward along the list, and whenever two adjacent nodes have equal values, remove the redundant node. Watch out for repeats at the end of the list, which change which node is last.

```
List: 1 -> 2 -> 2 -> 2 -> 2 -> 3 -> 3 -> 3 -> 4
removeRepeats()
Result: 1 -> 2 -> 3 -> 4
```

### Task 5: Reverse

Complete `reverse()`. It reverses the linked list.

**Tip:** You can use three node pointers to change the order of adjacent nodes and achieve the reversal. Remember that the old first node becomes the new last node.

```
List: 1 -> 2 -> 3
reverse()
Result: 3 -> 2 -> 1
```

### Task 6: Merge and Interleave Two Lists

Complete `merge(LinkedList list2)`. It merges `list2` into the current list by interleaving the two lists, starting with the first node of `list2`.

- `list2` will always be the same size as the current list or shorter. You don't need to handle a longer `list2`.
- If `list2` is empty, the current list doesn't change.
- After merging, `list2` should be **empty**, just as in Task 1.

**Tip:** Walk through both lists together and connect their nodes in turn. Stop once you've visited the tail node of `list2`.

```
List:  1 -> 2 -> 3
List2: 4 -> 5 -> 6
merge(List2)
Result: 4 -> 1 -> 5 -> 2 -> 6 -> 3
List2 afterward: (empty)

List:  1 -> 2 -> 3 -> 4
List2: 5 -> 6
merge(List2)
Result: 5 -> 1 -> 6 -> 2 -> 3 -> 4
List2 afterward: (empty)
```

## Grading Note

When you are finished, submit your code to the gradescope autograder. Submit your completed `worksheet.md` to its own Gradescope assignment.  