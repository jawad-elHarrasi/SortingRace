package atl.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static atl.model.Messages.END_OF_SORT;

public class SortingThread extends Thread {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final Sorting sorting;
    private final Integer[] array;

    public SortingThread(Sorting sorting, Integer[] array) {
        this.sorting = sorting;
        this.array = array;
    }

    @Override
    public void run() {
        this.sorting.sort(array);
        sorting.setSize(array.length - 1);
        ResultSorting result =
                new ResultSorting(sorting.getSortingType(), sorting.getSize(), sorting.getOperations(), sorting.getDuration());
        support.firePropertyChange(END_OF_SORT, null, result);
    }

    public Sorting getSorting() {
        return sorting;
    }

    public void addObserver(PropertyChangeListener observer) {
        support.addPropertyChangeListener(observer);
    }
}
