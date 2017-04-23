package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        if (checkLists(x, y)) {
            throw new IllegalArgumentException();
        }
        return isSubsequenceExist(x, y);
    }

    private boolean checkLists(List x, List y) {
        return x == null || y == null;
    }

    private boolean isSubsequenceExist(List x, List y) {
        int limit = 0;
        int counter = 0;
        for (Object aX : x) {
            for (int i = limit; i < y.size(); i++) {
                if (y.get(i).equals(aX)) {
                    limit = i;
                    counter++;
                    break;
                }
            }
        }
        return counter == x.size();
    }
}
