package org.logic;

import java.util.Set;
import java.util.TreeSet;

public class StringSort {

    public String[] sort(String[] arr1, String[] arr2){
        Set<String> result = new TreeSet<>(); // TreeSet for lexicographical sort and distinct

        for (String string : arr1) {  // get string from array
            for (String part : arr2) {  // get string from parts array
                if (string.contains(part))  // if string contains part
                    result.add(part);      //  add it to sorted set
            }
        }
        return result.toArray(new String[0]); // profit
    }
}
