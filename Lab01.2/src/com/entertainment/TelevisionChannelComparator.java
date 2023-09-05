package com.entertainment;

import com.entertainment.Television;

import java.util.Comparator;

public class TelevisionChannelComparator implements Comparator<Television> {

    @Override
    public int compare(Television t1, Television t2) {
        /*
         * For primitive sort keys, use the appropriate wrapper class:
         * Integer.compare()    for ints
         * Double.compare()     for doubles
         * Long.compare()       for longs
         * Boolean.compare()    for booleans
         */

        return Integer.compare(t1.getCurrentChannel(),t2.getCurrentChannel());
    }
}