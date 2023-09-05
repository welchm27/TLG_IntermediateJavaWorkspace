package com.entertainment.client;

import com.entertainment.Television;
import com.entertainment.TelevisionChannelComparator;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

class TelevisionClient {
    public static void main(String[] args) {
        // examine behavior of == and equals()
        Television tvA = new Television("Sony", 50);
        Television tvB = new Television("Sony", 50);
        Television tvC = new Television("Sony", 52);
        Television tvD = new Television("Sony", 12);

        System.out.println("tvA == tvB: "      + (tvA == tvB));     // == asks if they are the same object(they are not)
        System.out.println("tvA.equals(tvB): " + tvA.equals(tvB));  // .equals asks if they exhibit "equality"
        System.out.println();

        Set<Television> tvs = new TreeSet<>();      // TreeSet will only use the compareTo, which sorts by Brand.
        tvs.add(tvA);
        tvs.add(tvB);
        tvs.add(tvC);
        tvs.add(tvD);
        System.out.println("The size of the set is: " + tvs.size());
        for (Television tv : tvs) {
            System.out.println(tv);
        }
    }
}