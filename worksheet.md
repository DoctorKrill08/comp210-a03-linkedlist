# A3 Worksheet: Design Document for the Linked List

**Name:**
**Onyen:**

Six sections, 15 points. Fill this in **before** you write any code. It is a design document, so it says what your methods must do and what must stay true, not how you will write them. Everything you need is in `README.md`. Keep it short: the whole document should fit on about one page. Write your answers directly under each prompt.

---

## 1. The problem in your own words (2 points)

In two or three sentences, describe the problem this assignment asks you to solve. Say what the six new methods let a program do with a list of whole numbers, and whether they build new lists or change the ones they are given.

```
Traditional arrays are fixed in size and making a custom list using purely arrays can be computationally expensive as adding or removing items requires many items to be shifted left or right. This assignment wants to solve this by utilizing a different type of list: a linked list. The six methods will let a program organize numbers such that you can remove items at certain indexes, remove repeats, reverse, and compare and merge other linked lists. All of these methods should not need to build a new list rather they will only modify the existing ones.
```

---

## 2. Operations (3 points)

For each method you will write, describe in a few words what it is responsible for. Then say which of the list's **first node**, **last node**, and **size** the method can change, and in what situation. If it can change none of them, write "none". For the two merge methods, also say what state `list2` is left in.

| Method | What it is responsible for                                                                                                                  | Which of first node / last node / size it can change, and when                                                                                                                                                                                                                                                                                                                                           |
|---|---------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `simpleMerge` | Attaching the head of one linked list to the tail of another.                                                                               | The head of list2 will become the head of list1. The next node value of the tail of list2 will be the head of list1. At the end, list1 will be the size of list1 and list2 combined before merging, while list2 will no longer have any nodes. The first and last node of the original list1 will not be any bigger, but the first and last node of list2 will have gained size (however big list1 was). |
| `removeAtIndex` | Removing a node at a certain index while making sure the next nodes of the removed node are appended onto the removed node's previous node. | The removed node will change and the previous node to the removed node will also change. The previous node of the removed node's next node will be the removed node's next node. Because of this, the first node will be smaller but the last node will not be effected unless it is the node being removed.                                                                                             |
| `isEqual` | Checking whether or not 2 linked lists have the same amount and values of nodes.                                                            | No size is changed.                                                                                                                                                                                                                                                                                                                                                                                      |
| `removeRepeats` | Remove the nodes that repeat.                                                                                                               | It will change the size of the first node by however many repeats there are. The last node will not be affected unless it is a repeat.                                                                                                                                                                                                                                                                   |
| `reverse` | Each node's previous node will become said node's next node.                                                                                | The first node size will change to be 0 as it will have no next values and the last node will be whatever size the first node was before the reversal.                                                                                                                                                                                                                                                   |
| `merge` | Merging to linked lists where their nodes are interwoven with on another.                                                                   | The first node of list2 will become the size of list1 and list2 combined. The first of list1 will become the size of list1 and list2 combined but minus 1. The size of the tail of list2 will be one because now the last of list1 is the tail. The size of last of list1 does not change and remains no nodes.                                                                                          |

---

## 3. Data structure and justification (2 points)

This assignment uses a singly linked list that keeps a reference to both its first node and its last node. In one sentence, justify a linked list over an array-backed list (like the dynamic array from L11) for the work in Tasks 1 and 6. In a second sentence, explain what keeping a reference to the **last** node gives the class, and name one method, either provided or one of yours, that would have to do more work without it.

```
Array-backed lists are not computationally efficient when having to add or remove data as the values in the array all have to be shifted left or right, while in a linked list, adding a node or removing a node only effects the neighboring nodes. 
Keeping a reference to the last node allows another node to easily be added to the end of the linked list, which is very useful for the simpleMerge method, as without it, the program would have to iterate through every node until it reaches the tail.
```

---

## 4. Class invariant (3 points)

State the invariant the `LinkedList` class must maintain: what is always true about `_head`, `_tail`, and `_size` whenever no method is in the middle of running. Your answer should cover an empty list and a non-empty list, and it should say how `_size` relates to the nodes actually in the list.

```
Head must always be the first node of the linked list. If the linked list is empty, there will be no head or tail node. 
The tail must always be the last node of the linked list and therefore must have no next node.
Size must always be positive and represent the number of nodes in the linked list. If the linked list is empty the size would be 0 and if the linked list is 1 node then head and tail are the same node and the size is 1.
```

---

## 5. Three edge cases (3 points)

List three edge cases where a first attempt at one of your methods is likely to go wrong. Use at least two different methods, and don't reuse the main examples from the README. For each one, give the exact input (the list, plus `list2` or the index where one applies) and the correct result, including anything that must change about the first node, the last node, or the size.

| Method           | Input                         | Correct result                                                                                     |
|------------------|-------------------------------|----------------------------------------------------------------------------------------------------|
| simpleMerge      | list2 as a empty linked list  | Nothing changes                                                                                    |
| removeAtIndex    | i = 0                         | Head becomes its next node and is returned and the size is decreases by 1                          |
| removeDuplicates | The tail being a repeat value | The tail is removed and the new tail of the linked list is whatever the last non repeated node was |

---

## 6. Test strategy (2 points)

In two or three sentences, describe how you will check each method before you submit to the autograder. Say what you will look at after each call besides the printed contents of the list, and where your edge cases from section 5 come in.

```
I will print the before and after values for list1 to make sure the operation succeeded. For methods involving multiple lists I will print the values of list1 and list2 before and after so I can verify it worked. I will also likely modify main or write my own test class to test edge cases on the methods. 
```

---

## Submitting

Turn this in with your answers as a `.md` file on Gradescope. The code goes to Gradescope separately; see `README.md`.
