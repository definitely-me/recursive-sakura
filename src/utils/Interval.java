package utils;

public record Interval<T extends Comparable<T>>(T lBound, T rBound) {
    public Interval {
        if (lBound.compareTo(rBound) > 0) {
            throw new IllegalArgumentException("lBound cant be greater than rBound");
        }
    }
}