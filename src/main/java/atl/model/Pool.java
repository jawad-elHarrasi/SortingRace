package atl.model;

import javafx.scene.control.ProgressBar;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static atl.model.Messages.END_OF_SORT;
import static atl.model.Messages.NEW_SORTING;

public class Pool implements PropertyChangeListener {
    private final List<SortingThread> threadsList = new ArrayList<>();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final List<Integer[]> listOfTabs;
    private int index;
    private final ProgressBar progressBar;
    static final Random randomNumberGenerator = new Random();

    public Pool(int nbOfThreads, Sorting sorting, ProgressBar progressBar, int nbOfTabs) {
        this.listOfTabs = new ArrayList<>();

        for (int i = 1; i <= nbOfTabs + 1; i += nbOfTabs/10) {
            Integer[] array = new Integer[i];
            for (int j = 0; j < i; j++) {
                array[j] = randomNumberGenerator.nextInt();
            }
            listOfTabs.add(array);
        }

        this.progressBar = progressBar;

        for (int i = 0; i < nbOfThreads && i < listOfTabs.size(); i++) {
            SortingThread thread = new SortingThread(sorting, listOfTabs.get(i));
            threadsList.add(thread);
            thread.addObserver(this);
        }
        index = threadsList.size();

    }

    public void launchThreads() {
        for (SortingThread sortingThread : threadsList) {
            sortingThread.start();
        }
    }

    @Override
    public synchronized void propertyChange(PropertyChangeEvent evt) {
        if (Objects.equals(evt.getPropertyName(), END_OF_SORT)) {
            ResultSorting resultSorting = (ResultSorting) evt.getNewValue();
            progressBar.setProgress((double) resultSorting.getSize() / listOfTabs.get(listOfTabs.size() - 1).length);
            support.firePropertyChange(NEW_SORTING, null, resultSorting);
            if (this.index < listOfTabs.size()) {
                SortingThread t = new SortingThread(threadsList.get(0).getSorting(), listOfTabs.get(this.index));
                this.threadsList.add(t);
                this.index++;
                t.addObserver(this);
                t.start();
            }
        }
    }

    public void addObserver(PropertyChangeListener observer) {
        support.addPropertyChangeListener(observer);
    }
}

