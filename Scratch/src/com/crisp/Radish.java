package com.crisp;

/*
 * Natural order is given by 'size' (double).
 * This is called the "sort key."
 */
class Radish implements Comparable<Radish> {
    private String color;
    private double size;
    private double tailLength;
    private int greenThingies;

    // ctor
    public Radish(String color, double size, double tailLength, int greenThingies) {
        setColor(color);
        setSize(size);
        setTailLength(tailLength);
        setGreenThingies(greenThingies);
    }

    // accessor methods
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getTailLength() {
        return tailLength;
    }

    public void setTailLength(double tailLength) {
        this.tailLength = tailLength;
    }

    public int getGreenThingies() {
        return greenThingies;
    }

    public void setGreenThingies(int greenThingies) {
        this.greenThingies = greenThingies;
    }

    @Override       // interface Comparable
    public int compareTo(Radish other) {
        return Double.compare(this.getSize(), other.getSize());
    }

    @Override
    public String toString() {
        return String.format("%s: color=%s, size=%s, tailLength=%s, greenThingies=%s",
                getClass().getSimpleName(), getColor(), getSize(), getTailLength(), getGreenThingies());
    }
}