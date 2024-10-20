package atl.model;

import javafx.scene.control.ProgressBar;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static atl.model.Messages.NEW_SORTING;

public class Sorting implements PropertyChangeListener {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final SortingType sortingType;
    private final Pool poolOfThreads;
    private int size;
    private int operations;
    private Duration duration;


    public Sorting(SortingType sortingType, int nbOfThreads, ProgressBar progressBar, int nbOfTabs) {
        this.sortingType = sortingType;
        poolOfThreads = new Pool(nbOfThreads, this, progressBar, nbOfTabs);
        poolOfThreads.addObserver(this);
        poolOfThreads.launchThreads();
    }

    public synchronized void sort(Integer[] array) {
        this.operations = 0;
        this.duration = null;

        Instant start = Instant.now();
        if (sortingType == SortingType.BUBBLE_SORT) {
            bubbleSort(array);

        } else {
            mergeSort(array, array.length);
        }
        Instant end = Instant.now();

        this.duration = Duration.between(start, end);

    }

    private synchronized void bubbleSort(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                    this.operations += 3;
                }
                this.operations += 2;
            }
            this.operations++;
        }
    }

    private synchronized void mergeSort(Integer[] array, int size) {
        if (size < 2) {
            return;
        }
        int mid = size / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[size - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, size - mid);

        mergeSort(left, mid);
        mergeSort(right, size - mid);

        merge(array, left, right, mid, size - mid);
    }

    private void merge(Integer[] array, Integer[] leftArr, Integer[] rightArr, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (leftArr[i] <= rightArr[j]) {
                array[k++] = leftArr[i++];
            } else {
                array[k++] = rightArr[j++];
            }
            this.operations++; //every lap of the loop
        }
        while (i < left) {
            array[k++] = leftArr[i++];
            this.operations++; //every add operation
        }
        while (j < right) {
            array[k++] = rightArr[j++];
            this.operations++; //every add operation
        }
    }

    public SortingType getSortingType() {
        return this.sortingType;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOperations() {
        return this.operations;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public void addObserver(PropertyChangeListener observer) {
        support.addPropertyChangeListener(observer);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), NEW_SORTING)) {
            ResultSorting resultSorting = (ResultSorting) evt.getNewValue();

            support.firePropertyChange(NEW_SORTING, null, resultSorting);
        }
    }
}
