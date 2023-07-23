package qual.algorithms.runtime.greedy;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class ActivitySelection<T extends Comparable<T>> {
    private ActivityPair<T>[] activities = null;
    private Comparator<ActivityPair<T>> comparator 
    = (left, right) -> left.getEnd().compareTo(right.getEnd());

    private LinkedList<ActivityPair<T>> stack = new LinkedList<>();

    public ActivitySelection(Collection<ActivityPair<T>> activities) {
        this.activities = (ActivityPair<T>[]) activities.stream().toArray();
    }
    
    public ActivitySelection(Queue<ActivityPair<T>> activities) {
        this.activities = (ActivityPair<T>[]) activities.stream().toArray();
    }

    private void checkIfStateIsValid() {
        if (isEmpty()) {
            throw new RuntimeException("no activity item was found.");
        }
    }

    public boolean isEmpty() {
        return null == activities || activities.length == 0; }

    public Optional<List<ActivityPair<T>>> findMaxActivitiesCount() {
        checkIfStateIsValid();
        sortByEndTime();
        scheduleIfNotOverlappingWithLastMinRange();
        return Optional.ofNullable(stack);
    }

    private void sortByEndTime() {
        Arrays.sort(activities, comparator);
    }

    private void scheduleIfNotOverlappingWithLastMinRange() {
        stack.addLast(activities[0]);
        for ( ActivityPair<T> act : activities) {
            if (act == activities[0]) {
                continue;
            }
            if (act.overlaps(stack.peekLast())) {
                continue;
            }
            stack.addLast(act);
        }

    }

    public void setComparator(Comparator<ActivityPair<T>> cmp) {
        this.comparator = cmp;
    }

}
