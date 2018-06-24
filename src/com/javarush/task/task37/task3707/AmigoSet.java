package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Serializable, Cloneable, Set<E> {

    private static final Object PRESENT = new Object();
    private transient HashMap<E, Object> map;


    public AmigoSet() {
        map = new HashMap<>();
        //HashSet<String> set = new HashSet();
        //set.clone();
    }

    public AmigoSet(Collection<? extends E> collection){
        int capacity = Math.max(16, (int) Math.ceil(collection.size()/.75f));
        map = new HashMap<>(capacity);
        for(E e : collection){
            map.put(e, PRESENT);
        }
    }

    @Override
    public Iterator iterator() {

        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean add(E e) {
        boolean modified = false;
        if (map.put(e, PRESENT)==null){
            modified = true;
        }
        return modified;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean remove(Object o) {
        boolean isRemoved = false;
        if(map.remove(o)== null){
            isRemoved = true;
        }
        return isRemoved;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Object clone()  {
        try {
            AmigoSet<E> amigoSet = (AmigoSet<E>) super.clone();
            amigoSet.map = (HashMap<E, Object>) map.clone();
            return amigoSet;
        } catch (Exception e) {
            throw new InternalError(e);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();

        out.writeFloat((HashMapReflectionHelper.callHiddenMethod(map, "loadFactor")));
        out.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        out.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "size"));
        for (E e : map.keySet())
            out.writeObject(e);


    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        //Set keys = (Set) in.readObject();
        float loadFactor = in.readFloat();
        int capacity =  in.readInt();
        int size = in.readInt();
        map = new HashMap<>(capacity, loadFactor);
        for (int i = 0; i <size ; i++) {
            E e = (E) in.readObject();
            map.put(e, PRESENT);
        }
    }
}
