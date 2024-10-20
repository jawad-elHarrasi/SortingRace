package atl.controller;

import atl.model.Sorting;
import atl.model.SortingType;
import javafx.scene.control.ProgressBar;

import java.beans.PropertyChangeListener;

public class GraphicController {
    public void handleAddButtonAction(int nbOfTabs, SortingType type, int nbOfThreads,
                                      PropertyChangeListener observer, ProgressBar progressBar) {

        Sorting sorting = new Sorting(type, nbOfThreads, progressBar, nbOfTabs);
        sorting.addObserver(observer);

    }

}
