package com.tsystems.javaschool.tasks.duplicates;

import java.util.*;

public interface IDataGetter {
    List<String> getData();
    boolean writeData(Map<String, Integer> dataMap);

    /**
     *
     * @param list
     * @return sorted list
     */
    default Map<String, Integer> getSortedAndCountedMap(List<String> list) {
        Map<String, Integer> substringCounter = new TreeMap<>();
        if (list != null) {
            for (String str : list) {
                if (!substringCounter.containsKey(str)) {
                    substringCounter.put(str, 1);
                } else {
                    int value = substringCounter.get(str) + 1;
                    substringCounter.put(str, value);
                }
            }
        }
        return substringCounter;
    }
}
