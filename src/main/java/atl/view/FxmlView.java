package atl.view;

import atl.controller.GraphicController;
import atl.model.Level;
import atl.model.ResultSorting;
import atl.model.Sorting;
import atl.model.SortingType;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;


import static atl.model.Messages.NEW_SORTING;

public class FxmlView implements Initializable, PropertyChangeListener {
    XYChart.Series<Number, Number> series;
    private GraphicController graphicController;
    @FXML
    private TableView<ResultSorting> table;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Spinner<Integer> threadSpinner;
    @FXML
    private TableColumn<Sorting, String> nameCol;
    @FXML
    private TableColumn<Sorting, Integer> sizeCol;
    @FXML
    private TableColumn<Sorting, Integer> swapCol;
    @FXML
    private TableColumn<Sorting, Integer> durationCol;
    @FXML
    private ChoiceBox<SortingType> sortChoice;
    @FXML
    private ChoiceBox<Level> configurationChoice;
    @FXML
    private LineChart<Number, Number> chart;
    @FXML
    private Label leftStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        graphicController = new GraphicController();

        sortChoice.getItems().addAll(SortingType.values());
        sortChoice.setValue(SortingType.values()[0]);

        configurationChoice.getItems().addAll(Level.values());
        configurationChoice.setValue(Level.VERYEASY);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1, 1);
        threadSpinner.setValueFactory(valueFactory);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("SortingType"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        swapCol.setCellValueFactory(new PropertyValueFactory<>("Operations"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("Duration"));

    }

    @FXML
    private void handleAddButtonAction() {
        series = new XYChart.Series<>();
        progressBar.setProgress(0);
        int size = configurationChoice.getValue().getLevel();
        Integer nbOfThreads = threadSpinner.getValue();

        SortingType type = sortChoice.getValue();
        graphicController.handleAddButtonAction(size, type, nbOfThreads, this, progressBar);
        leftStatus.setText("number of active threads " + Thread.activeCount());
        progressBar.setProgress(1.0);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(NEW_SORTING)) {
            ResultSorting resultSorting = (ResultSorting) evt.getNewValue();
            Platform.runLater(() -> {
                this.table.getItems().add(resultSorting);

                this.series.getData().add(new XYChart.Data<>(resultSorting.getSize(), resultSorting.getOperations()));
                series.setName(resultSorting.getSortingType());
                if (!chart.getData().contains(series)) {
                    this.chart.getData().add(series);
                }
            });
        }

    }
}
