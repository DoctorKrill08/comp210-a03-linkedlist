package edu.unc.comp210.a03LinkedList;

// Starter Code provided with Assignment #3 for COMP210
// The given main method has some examples of how to create and modify the linked lists
// It contains suggestions on how to test your code after completing the TODO Tasks

public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(10);
        list.add(20);
        list.add(60);
        list.add(30);
        System.out.println("list = " + list.toString());
        System.out.println("size of list = " + list.size());
        System.out.println("list contains 10?: " + list.contains(10));
        System.out.println("list contains 50?: " + list.contains(50));
        System.out.println("set element at index 2 to be 10");
        list.set(2, 10);
        System.out.println("get element at index 2 = " + list.get(2));
        System.out.println("list = " + list.toString());
        System.out.println("Last Index of element 10 in list = " + list.lastIndexOf(10));

        list.remove(20);
        System.out.println("list after removing 20 = " + list.toString());

        System.out.println("index of '30' = " + list.indexOf(30));

        // Test task 1
        LinkedList list1 = new LinkedList();
        list1.add(1);
        list1.add(2);
        System.out.println("list1 = " + list1.toString());
        list.simpleMerge(list1);
        System.out.println("Task 1: list after simpleMerge with list1 = " + list.toString());

        // Test task 2
        list.removeAtIndex(1);
        System.out.println("Task 2: list after removing element at index 1 = " + list.toString());


        // Test task 3
        LinkedList list2 = new LinkedList();
        list2.add(100);
        list2.add(200);
        System.out.println("list2 = " + list2.toString());
        System.out.println("Task 3: list == list2 ?: " + list.isEqual(list2));
        LinkedList list3 = new LinkedList();
        list3.add(1);
        list3.add(10);
        list3.add(10);
        list3.add(30);
        System.out.println("list3 = " + list3.toString());
        System.out.println("Task 3: list == list3 ?: " + list.isEqual(list3));

        // Test task 4
        System.out.println("list before removing repeats = " + list.toString());
        list.removeRepeats();
        System.out.println("Task 4: list after removing repeats = " + list.toString());

        // Test task 5
        list.add(40);
        list.add(50);
        list.add(60);
        System.out.println("list before reversing = " + list.toString());
        list.reverse();
        System.out.println("Task 5: list after reversing = " + list.toString());

        // Test task 6
        System.out.println("list before merging = " + list.toString());
        System.out.println("list2 before merging = " + list2.toString());
        list.merge(list2);
        System.out.println("Task 6: list after merging = " + list.toString());
    }
}
