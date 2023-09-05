package com.crisp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class RadishSortTest {
    public static void main(String[] args) {
        List<Radish> radishes = new ArrayList<>();
        radishes.add(new Radish("red", 1.0, 2.2, 3));     // color, size, tailLength, greenThingies
        radishes.add(new Radish("black", 3.5, 0.0, 0));
        radishes.add(new Radish("red", 0.75, 3.1, 7));
        radishes.add(new Radish("white", 1.0, 1.0, 2));

        System.out.println("Natural order (size)");
        radishes.sort(null);    // passing null means natural order
        dump(radishes);
        System.out.println();

        radishes.sort(new RadishColorComparator());
        System.out.println("Sorted by color");
        dump(radishes);
        System.out.println();

        // anonymous Class that implements Comparator so we don't have to build a new Comparator
        System.out.println("Sort by tailLength, via anonymous Comparator");
        radishes.sort(new Comparator<Radish>(){
            @Override
            public int compare(Radish r1, Radish r2) {
                return Double.compare(r1.getTailLength(),r2.getTailLength());
            }
        } );
        dump(radishes);
        System.out.println();

        System.out.println("Sort by guys on top, via lambda");
        // sort takes a 'Comparator'
        // 'Comparator' is a functional interface
        // anywhere you can pass a functional interface, you can use a Lambda
        // r1 and r2 are coming IN as a Comparator  // the right side is the line of code you'd do in the Comparator class
        radishes.sort( (r1, r2) -> Integer.compare(r1.getGreenThingies(), r2.getGreenThingies()) );     // don't have to say return

        dump(radishes);
        System.out.println();



    }   // end of main


    private static void dump(List<Radish> radishList) {
        for (Radish radish : radishList) {
            System.out.println(radish);
        }
    }
}   // end of RadishSortTest Class