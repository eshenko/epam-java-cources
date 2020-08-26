package com.epam.university.java.core.task011;

import java.util.ArrayList;
import java.util.LinkedList;

public class Task011Impl implements Task011 {
    @Override
    public String getLastName(String[] collection) {
        if (collection == null || collection.length == 0) {
            throw new IllegalArgumentException();
        }
        int index = 0;
        while (collection.length > 1) {
            collection[index] = null;
            index++;
            if (index == collection.length) {
                index = 1;
            }
            if (index == collection.length - 1) {
                index = 0;
            }

            String[] tmp = new String[collection.length - 1];
            int indexTmp = 0;

            for (String s : collection) {
                if (s != null) {
                    tmp[indexTmp] = s;
                    indexTmp++;
                }
            }
            collection = tmp;
        }
        return collection[0];
    }

    @Override
    public String getLastName(ArrayList<String> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String lastname = null;
        while (!collection.isEmpty()) {
            lastname = collection.remove(0);
            if (!collection.isEmpty()) {
                collection.add(collection.remove(0));
            }
        }
        return lastname;
    }

    @Override
    public String getLastName(LinkedList<String> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException();
        }
        String lastname = null;
        while (!collection.isEmpty()) {
            lastname = collection.remove();
            if (!collection.isEmpty()) {
                collection.add(collection.remove());
            }
        }
        return lastname;
    }
}
