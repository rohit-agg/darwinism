package com.codegym.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Build a tree (part 1)

*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root = new Entry<>("0");

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String s) {

        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        Entry<String> node;
        boolean added = false;

        while ((node = queue.poll()) != null) {

            if (node.availableToAddLeftChildren) {

                node.leftChild = new Entry<>(s);
                node.leftChild.parent = node;
                node.availableToAddLeftChildren = false;
                added = true;
                break;

            } else if (node.availableToAddRightChildren) {

                node.rightChild = new Entry<>(s);
                node.rightChild.parent = node;
                node.availableToAddRightChildren = false;
                added = true;
                break;

            } else {

                if (node.leftChild == null && node.rightChild == null) {

                    node.leftChild = new Entry<>(s);
                    node.leftChild.parent = node;
                    node.availableToAddRightChildren = true;
                    added = true;
                    break;

                } else {
                    if (node.leftChild != null) {
                        queue.add(node.leftChild);
                    }
                    if (node.rightChild != null) {
                        queue.add(node.rightChild);
                    }
                }
            }
        }

        return added;
    }

    @Override
    public int size() {

        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        Entry<String> node;
        int size = 0;

        while ((node = queue.poll()) != null) {

            size++;
            if (node.leftChild != null) {
                queue.add(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.add(node.rightChild);
            }
        }

        return size - 1;
    }

    public String getParent(String value) {

        if (root.elementName.equals(value)) {
            return null;
        }

        Entry<String> node = findNode(root.leftChild, value);
        if (node == null) {
            node = findNode(root.rightChild, value);
        }

        if (node == null || node.parent == null) {
            return null;
        }

        return node.parent.elementName;
    }

    private Entry<String> findNode(Entry<String> node, String value) {

        if (node == null) {
            return null;
        } else if (node.elementName.equals(value)) {
            return node;
        }

        Entry<String> childNode = findNode(node.leftChild, value);
        if (childNode == null) {
            childNode = findNode(node.rightChild, value);
        }
        return childNode;
    }

    public boolean remove(Object val) {

        if (!(val instanceof String)) {
            throw new UnsupportedOperationException();
        }

        Entry<String> node = findNode(root.leftChild, val.toString());
        if (node == null) {
            node = findNode(root.rightChild, val.toString());
        }

        if (node == null) {
            return false;
        }

        Entry<String> parent = node.parent;
        if (parent.leftChild == node) {
            parent.leftChild = null;
        } else if (parent.rightChild == node) {
            parent.rightChild = null;
        }

        return true;
    }

    static class Entry<T> implements Serializable {

        String elementName;

        boolean availableToAddLeftChildren;

        boolean availableToAddRightChildren;

        Entry<T> parent;

        Entry<T> leftChild;

        Entry<T> rightChild;

        public Entry(String eN) {
            this.elementName = eN;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return (availableToAddLeftChildren || availableToAddRightChildren);
        }
    }
}
