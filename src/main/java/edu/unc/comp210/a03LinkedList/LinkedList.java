package edu.unc.comp210.a03LinkedList;

// Starter Code provided with Assignment #3 for COMP210

public class LinkedList {
    private Node _head = null;
    private Node _tail = null;
    private int _size = 0;

    /**
     * Task 1
     * Merge the given list (list2) at the start of the current list.
     * After merging, list2 should be empty.
     *
     * Note: Do NOT create and return a new list, merge the second list at the start of the first one.
     *
     * ex: list: 1 -> 2 -> 3
     *     list2: 4 -> 5 -> 6
     *     list after simpleMerge: 4 -> 5 -> 6 -> 1 -> 2 -> 3
     *     list2 after simpleMerge: (empty)
     *
     * @param list2 - list to be merged
     */
    public void simpleMerge(LinkedList list2) {
        if (list2.isEmpty()){
            return;
        }
        if (isEmpty()){
            _head = list2.gethead();
            _tail = list2._tail;
            list2.clear();
            return;
        }
        _size += list2.size();
        list2._tail.setNext(gethead());
        _head = list2.gethead();
        list2.clear();
    }

    /**
     * Task 2
     * Remove the node at index i of the list.
     * Note that the first element is at index 0
     * If i is not a valid index (less than 0, or greater than or equal to the size of the list),
     * throw an IndexOutOfBoundsException
     *
     * ex: list: 1 -> 4 -> 2 -> 3
     *     i: 1
     *     list after removeAtIndex: 1 -> 2 -> 3
     *
     * @param i    - index of node to remove
     */
    public void removeAtIndex(int i) {
        if (i < 0 || i >= size()){
            throw new IndexOutOfBoundsException("Invalid index: " + i);
        }
        _size--;
        if (i == 0){
            _head = _head.getNext();
            return;
        }
        int index = 0;
        Node current = _head;
        Node previous = _head;
        while (index < i){
            current = current.getNext();
            index++;
            if (index == i){
                //edge case! If the removed element is the tail, set the tail to the previous element!
                if (i == size() - 1){
                    _tail = previous;
                }
                break;
            }
            previous = current;
        }

        previous.setNext(current.getNext());
        current = null;
    }

    /**
     * Task 3
     * Return true if this linked list is equal to the list argument, false otherwise.
     * Two lists are equal if they have the same size, and the same
     * elements in the same order.
     * ex:  list: 1 -> 4 -> 2
     *      list2: 1 -> 4 -> 2
     *      return: true
     *
     *      list: 1 -> 5
     *      list2: 2 -> 5
     *      return false;
     *
     * @param list2 - the list to compare with the current list
     * @return true if the lists have the same elements in the same order, false otherwise
     */
    public boolean isEqual(LinkedList list2) {
        // TODO
        if (size() != list2.size()){
            return false;
        }
        if (isEmpty() && list2.isEmpty()){
            return true;
        }

        Node current = gethead();
        Node compare = list2.gethead();

        for (int i = 0; i < size(); i++){
            if (current.getValue() != compare.getValue()){
                return false;
            }
            current = current.getNext();
            compare = compare.getNext();
        }

        return true;
    }

    /**
     * Task 4
     * Given a sorted linked list, remove the duplicate values from the list
     * ex: list: 5 -> 6 -> 7 -> 7 -> 7 -> 8 -> 8 -> 9
     *     list after removeRepeats: 5 -> 6 -> 7 -> 8 -> 9
     *
     */
    public void removeRepeats() {
        if (size() <= 1){
            return;
        }

        Node current = gethead();
        Node uniqueNode = gethead();
        int i = 0;
        int originalSize = size();

        while (current.hasNext()){
            i++;
            current = current.getNext();
            int thisValue = current.getValue();

            //Repeat values get skipped and size decrements
            //It works by only connecting a unique node to the last unique node
            if (thisValue != uniqueNode.getValue()){
                uniqueNode.setNext(current);
                uniqueNode = current;
                continue;
            }

            //To get here this node must be a repeat
            _size--;
            //edge case! When the tail is a repeat it cannot be removed by rerouting pointers
            //So set it directly to null
            if (i == originalSize - 1){
                _tail = uniqueNode;
                uniqueNode.setNext(null);
            }

        }

    }

     /**
     * Task 5
     * Reverse the list.
     * eg list:  10 -> 9 -> 8 -> 7
     * list after reverse: 7 -> 8 -> 9 -> 10
     */
    public void reverse() {
        if (size() <= 1){
            return;
        }
        //The tail will become the head and vice versa
        Node newHead = _tail;
        Node newTail = _head;

        Node stored;
        Node current = gethead();
        Node previous = null;
        while (current != null){
            //I like to think of this as splitting up the linked list and then putting it back together
            stored = current.getNext();

            //Temporary variable so that previous = current works properly
            //previous should be current BEFORE current is modified
            Node snapshot = current;

            current.setNext(previous);
            previous = snapshot;
            current = stored;
        }
        _head = newHead;
        _tail = newTail;
    }

    /**
     * Task 6
     * Merge the given linked list2 into the current list. The 2 lists will always be
     * either the same size, or the current list will be longer than list2.
     * The examples below show how to handle each case.
     * After merging, list2 should be empty.
     *
     * Note: Do NOT create and return a new list, merge the second list into the first one.
     *
     * ex: list: 1 -> 2 -> 3
     *     list2: 4 -> 5 -> 6
     *     list after merge: 4 -> 1 -> 5 -> 2 -> 6 -> 3
     *
     *     list: 1 -> 2 -> 3 -> 4
     *     list2: 5 -> 6
     *     list after merge: 5 -> 1 -> 6 -> 2 -> 3 -> 4
     *
     * @param list2 - list to interleave into the current list
     */

    public void merge(LinkedList list2) {
        if (list2.isEmpty()){
            return;
        }
        if (isEmpty()){
            _head = list2.gethead();
            _tail = list2._tail;
            return;
        }


        Node newTail = _tail;
        Node newHead = list2.gethead();
        //when this list is less than or the same size as the compared list
        //The tail is garunteed to be the list2 tail
        if (size() <= list2.size()){
            newTail = list2._tail;
        }

        Node current = list2.gethead();
        Node stored = current.getNext();
        Node inserted = gethead();

        while (current.hasNext()){
            //Split the current node and store its next node
            stored = current.getNext();

            //Make the next node of the current node the inserted value
            current.setNext(inserted);

            //Iterate the current node
            current = inserted;

            //In the next iteration the inserted node will be the stored node
            inserted = stored;
        }

        //Once one of the linked lists is fully iterated through, the remaining nodes will be appended to the end of the merged linked list
        current.setNext(stored);

        _size += list2.size();

        list2.clear();
        _head = newHead;
        _tail = newTail;
    }


    /* Implementations below are being given to you. Do not modify below this line. */

    public int size() {
        return _size;
    }

    public boolean isEmpty() {
        return _size == 0;
    }

    public void clear() {
        _head = null;
        _tail = null;
        _size = 0;
    }

    public boolean contains(int element) {
        Node current = _head;
        while(current != null) {
            if(current.getValue() == element) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public int[] toArray() {
        int[] arr =   new int[size()];
        Node current = _head;
        int i = 0;
        if(isEmpty()) {
            return arr;
        }
        while(current != null){
            arr[i] = current.getValue();
            current = current.getNext();
            i++;
        }
        return arr;
    }

    public void add(int element) {
        Node newNode = new NodeImpl(element, null);
        if(isEmpty()) {
            _head = newNode;
            _tail = newNode;
            _size++;
        } else {
            _tail.setNext(newNode);
            _tail = newNode;
            _size++;
        }

    }

    public boolean remove(int element) {
        Node current = _head;
        if(isEmpty()) {
            return false;
        }
        if(current.getValue() == element){
            _head = _head.getNext();
            if(_head == null) {
                _tail = null;
            }
            _size--;
            return true;
        }
        while(current.getNext() != null && current.getNext().getValue() != element) {
            current = current.getNext();
        }
        if(current.getNext() == null) {
            return false;
        }
        if(current.getNext().getNext() == null) {
            _tail = current;
        }
        current.setNext(current.getNext().getNext());
        _size--;
        return true;
    }

    public int get(int index) {
        validIndex(index);
        Node current = _head;
        int i = 0;
        while (i < index) {
            current = current.getNext();
            i++;
        }
        return current.getValue();
    }

    public int set(int index, int element) {
        validIndex(index);
        Node current = _head;
        int prevValue = 0;
        int i = 0;
        if(index == 0) {
            prevValue = _head.getValue();
            _head.setValue( element);
        } else {
            while(current != null) {
                if(i == index) {
                    prevValue = current.getValue();
                    current.setValue( element);
                    return prevValue;
                }
                current = current.getNext();
                i++;
            }
        }

        return prevValue;
    }

    public void add(int index, int element) {
        if(index < 0 || index > _size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node current = _head;
        int i = 0;
        if(index == 0) {
            if(isEmpty()) {
                add(element);
                return;
            } else {
                Node newNode = new NodeImpl( element, _head);
                _head = newNode;
                _size++;
                return;
            }

        }  else if(index == _size) {
            add(element);
            return;
        }
        while(current != null) {
            if(i == (index - 1)) {
                Node temp = current.getNext();
                Node newNode = new NodeImpl( element, temp);
                current.setNext(newNode);
                _size++;
                return;
            } else {
                current = current.getNext();
                i++;
            }
        }
    }

    public int indexOf(int element) {
        Node current = _head;
        int index = 0;
        while(current != null) {
            if(current.getValue() == element) {
                return index;
            }
            index++;
            current = current.getNext();
        }
        return -1;
    }

    public int lastIndexOf(int element) {
        Node current = _head;
        int index = -1;
        int i = 0;
        while(current != null) {
            if(current.getValue() == element) {
                index = i;
            }
            i++;
            current = current.getNext();
        }
        return index;
    }

    public void validIndex(int i) {
        if(i < 0 || i >= _size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }
    public Node gethead() {
        return _head;
    }

    @Override
    public String toString() {
        String list = "";
        Node current = _head;
        while(current != null) {
            if(current.getNext() == null)
                list+= current.getValue();
            else
                list += current.getValue() + " -> ";
            current = current.getNext();
        }
        return list;
    }
}