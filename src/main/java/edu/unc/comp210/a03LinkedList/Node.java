package edu.unc.comp210.a03LinkedList;

public interface Node {

    int getValue();

    void setValue(int value);

    Node getNext();

    void setNext(Node next);

    default boolean hasNext() {
        return (getNext() != null);
    }
}