package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;


/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root = new Entry<>("0");
    List<Entry<String>> entryList = new LinkedList<>();


    public CustomTree() {
        entryList.add(root);
    }

    public static void main(String[] args) {
        CustomTree list = new CustomTree();
        for (int i = 1; i < 131; i++) {
            list.add(String.valueOf(i));
        }

        String s = "129";
        System.out.println(list.size());
        //System.out.println(entryList.size());
        //System.out.println(entryList.get(0).elementName);
        System.out.println("Parent of "+s+ " is "+ list.getParent(s));
        //System.out.println("Expected 3, actual is " + ((Dual) list).getParent("8"));
        list.remove("3");
        System.out.println("Parent of "+s+ " is "+ list.getParent(s));
        System.out.println(list.size());
        list.remove("5");
        System.out.println(list.size());
        list.remove("6");
        System.out.println(list.size());
        list.remove("4");
        System.out.println(list.size());
        //System.out.println("Parent of "+s+ " is "+ list.getParent(s));
        //System.out.println("Expected null, actual is " + ((CustomTree) list).getParent("11"));
        //s = "16";
        //list.add(s);
        //System.out.println("Parent of "+s+ " is "+ list.getParent(s));
    }

    @Override
    public boolean add(String s) {
        boolean x = false;
        for (Entry<String> entry : entryList) {
            entry.checkChildren();
            if (entry.isAvailableToAddChildren()) {
                x = true;
                Entry<String> curr = new Entry<>(s);
                curr.parent = entry;
                entryList.add(curr);
                if (entry.availableToAddLeftChildren) {
                    entry.leftChild = curr;
                    break;
                } else {
                    entry.rightChild = curr;
                    break;
                }
            }
        }
        return x;
    }

    @Override
    public boolean remove(Object o) {
        boolean x = false;
        for (Entry<String> entry : entryList) {
            if(o.toString().equals(entry.elementName)){
                x = true;
                if(entry.leftChild!=null){
                    remove(entry.leftChild.elementName);
                }
                if(entry.rightChild!=null){
                    remove(entry.rightChild.elementName);
                }
                entryList.remove(entry);
                break;
            }
        }
        return x;
    }

    @Override
    public int size() {
        return entryList.size() - 1;
    }

    public String getParent(String s) {
        for (Entry<String> entry : entryList) {
            if (s.equals(entry.elementName)) {
                return entry.parent.elementName;
            }
        }
        return null;
    }

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

    static class Entry<T> implements Serializable {

        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public void checkChildren() {
            if (leftChild != null) {
                availableToAddLeftChildren = false;
            }
            if (rightChild != null) {
                availableToAddRightChildren = false;
            }
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren | availableToAddRightChildren;
        }
    }
}