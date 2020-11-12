package com.epam.university.java.core.task060;

import java.util.HashMap;

public class CacheImpl implements Cache {
    private final HashMap<Integer, Node> hashmap;
    private Node start;
    private Node end;
    private final int cacheSize;

    public CacheImpl(int size) {
        this.cacheSize = size;
        this.hashmap = new HashMap<>();
    }

    /**
     * Get Node from cache.
     * @param key of Node.
     * @return Node from cache.
     */
    public String getNode(int key) {
        if (hashmap.containsKey(key)) {
            Node node = hashmap.get(key);
            removeNode(node);
            moveToTop(node);
            return node.getValue();
        }
        return "0";
    }

    /**
     * Add or update Node in cache.
     * @param key of Node.
     * @param value of Node.
     */
    public void setNode(int key, String value) {
        if (hashmap.containsKey(key)) {
            Node node = hashmap.get(key);
            node.setValue(value);
            removeNode(node);
            moveToTop(node);
        } else {
            Node newNode = new NodeImpl();
            newNode.setPrev(null);
            newNode.setNext(null);
            newNode.setValue(value);
            newNode.setKey(key);
            if (hashmap.size() >= cacheSize) {
                hashmap.remove(end.getKey());
                removeNode(end);
            }
            moveToTop(newNode);
            hashmap.put(key, newNode);
        }
    }

    /**
     * Method move <code>node</code> to top of the cache.
     * @param node is moved.
     */
    public void moveToTop(Node node) {
        node.setNext(start);
        node.setPrev(null);
        if (start != null) {
            start.setPrev(node);
        }
        start = node;
        if (end == null) {
            end = start;
        }
    }

    /**
     * Method remove <code>node</code> from the cache.
     * @param node is removed.
     */
    public void removeNode(Node node) {
        if (node.getPrev() != null) {
            node.getPrev().setNext(node.getNext());
        } else {
            start = node.getNext();
        }

        if (node.getNext() != null) {
            node.getNext().setPrev(node.getPrev());
        } else {
            end = node.getPrev();
        }
    }
}
