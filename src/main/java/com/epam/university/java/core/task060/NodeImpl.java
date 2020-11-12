package com.epam.university.java.core.task060;

public class NodeImpl implements Node {
    private String value;
    private int key;
    private Node left;
    private Node right;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getPrev() {
        return left;
    }

    public void setPrev(Node left) {
        this.left = left;
    }

    public Node getNext() {
        return right;
    }

    public void setNext(Node right) {
        this.right = right;
    }
}
