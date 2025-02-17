package com.lead.dashboard.config;

import java.util.HashMap;
import java.util.*;
public class Test {

	public static void insertionSort(ArrayList<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int key = list.get(i);  // The element to be inserted in the sorted part
            int j = i - 1;
            
            // Move elements of list[0..i-1] that are greater than key to one position ahead
            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));  // Shift element to the right
                j = j - 1;
            }
            list.set(j + 1, key);  // Insert the key at the correct position
        }
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(11);
        list.add(13);
        list.add(5);
        list.add(6);
        
        System.out.println("Original ArrayList: " + list);
        
        insertionSort(list);  // Sort the list
        
        System.out.println("Sorted ArrayList: " + list);
    }

}
