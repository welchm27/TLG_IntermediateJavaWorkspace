package com.entertainment;

import javax.print.attribute.standard.PresentationDirection;
import java.util.Comparator;

public class TelevisionBrandChannelComparator implements Comparator<Television> {

    @Override
    public int compare(Television t1, Television t2) {
        // first we compare brand
        int compare = t1.getBrand().compareTo(t2.getBrand());

        // if result is 0, we're tied on brand, compare by channel
        if(compare == 0) {
            compare = Integer.compare(t1.getCurrentChannel(),t2.getCurrentChannel());
        }
        return compare;
    }
}