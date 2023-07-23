package qual.algorithms.runtime.greedy;

import java.util.Comparator;

/**
 * an activity pair contains a pair of two items
 * Start type and End type
 */
public class ActivityPair<T extends Comparable<T>>
implements Comparable<ActivityPair<T>> {
    private T start;
    private T end;
    private Comparator<ActivityPair<T>> comparator = null;

    public ActivityPair(T start, T end) {
        this.start = start;
        this.end = end;
    }

    public static <T extends Comparable<T>> ActivityPair<T> 
    from(T start, T end) {
        return new ActivityPair(start, end);
    }

    public void setComparator(Comparator<ActivityPair<T>> cmp) {
        comparator = cmp;
    }

    public T getStart() {
        return this.start;
    }
    
    public T getEnd() {
        return this.end; }

    @Override
    public int compareTo(ActivityPair<T> o) {
        if (null == comparator) {
            return this.end.compareTo(o.getEnd());
        }
        return comparator.compare(this, o);
    }

    public boolean overlaps(ActivityPair<T> another) {
        return another.getStart().compareTo(end) < 0;
    }

    
}
