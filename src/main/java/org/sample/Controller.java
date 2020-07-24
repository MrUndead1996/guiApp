package org.sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import org.logic.NumberInExpandedForm;
import org.logic.SaveLoadController;
import org.logic.StringSort;
import org.logic.Tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Controller implements Initializable {
    @FXML
    private ComboBox<String> taskChanger;
    @FXML
    private GridPane root, stringsSort, numbers;
    @FXML
    private Button compute, load, save;
    @FXML
    private TextField result, words, parts, number;
    private final StringSort ss;
    private final NumberInExpandedForm numberInExpandedForm;
    private final SaveLoadController saveLoadController;
    private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());

    public Controller() {
        ss = new StringSort();
        numberInExpandedForm = new NumberInExpandedForm();
        saveLoadController = new SaveLoadController();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configLogger();
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Tasks t :
                Tasks.values()) {
            list.add(t.name());
        }
        taskChanger.setItems(list);
        taskChanger.valueProperty().addListener((ov, t, t1) -> {
            if (t1.equals("StringSort")) {
                numbers.setVisible(false);
                numbers.setDisable(true);
                stringsSort.setVisible(true);
                stringsSort.setDisable(false);
            } else {
                numbers.setVisible(true);
                numbers.setDisable(false);
                stringsSort.setVisible(false);
                stringsSort.setDisable(true);
            }
            dataValidate();
        });
        compute.setOnAction(actionEvent -> computeListener());
        words.textProperty().addListener(observable -> dataValidate());
        parts.textProperty().addListener(observable -> dataValidate());
        number.textProperty().addListener(observable -> dataValidate());
        load.setOnAction(actionEvent -> load());
        save.setOnAction(actionEvent -> save());

    }

    private void computeListener() {
        switch (taskChanger.getValue()) {
            case "StringSort" : {
                if (this.words.getText().isEmpty() || this.parts.getText().isEmpty()) {
                    LOGGER.log(Level.WARNING,"empty word or parts field");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Enter words");
                    alert.showAndWait();
                    return;
                }
                String[] words = this.words.getText().replace(" ", "").split(",");
                String[] parts = this.parts.getText().replace(" ", "").split(",");
                result.setText(Arrays.toString(ss.sort(words, parts)));
                break;
            }
            case "NumberInExpandedForm" : {
                try {
                    int number = Integer.parseInt(this.number.getText());
                    result.setText(numberInExpandedForm.getNumber(number));
                } catch (NumberFormatException numberFormatException) {
                    LOGGER.log(Level.WARNING,numberFormatException.getMessage());
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Enter number");
                    alert.showAndWait();
                }
                break;
            }
        }
    }

    private void dataValidate() {
        switch (taskChanger.getValue()) {
            case "StringSort" : {
                compute.setDisable(words.getText().isEmpty() || parts.getText().isEmpty());
                break;
            }
            case "NumberInExpandedForm" : {
                compute.setDisable(number.getText().isEmpty());
                break;
            }
            default : compute.setDisable(false);break;
        }
    }

    private void save() {
        switch (taskChanger.getValue()) {
            case "StringSort" : {
                try {
                    saveLoadController.save(Tasks.valueOf(taskChanger.getValue()), words.getText(), parts.getText());
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING,e.getMessage() + "can't save file");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    e.printStackTrace();
                    alert.setContentText("Can't save file. Try again");
                    alert.showAndWait();
                }
                break;
            }
            case "NumberInExpandedForm" : {
                //TODO this!
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Empty");
                alert.showAndWait();
                break;
            }
        }
    }

    private void load() {
        FileChooser fileChooser = new FileChooser();
        File dir = new File("./save/");
        dir.mkdir();
        fileChooser.setInitialDirectory(dir);
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("TXT", ".txt"));
        File file = fileChooser.showOpenDialog(root.getScene().getWindow());
        try {
            List<String> args = saveLoadController.load(file);
            this.taskChanger.setValue(args.get(0));
            switch (args.get(0)) {
                case "StringSort" : {
                    taskChanger.setValue(args.get(0));
                    words.setText(args.get(1));
                    parts.setText(args.get(2));
                    break;
                }
                case "NumberInExpandedForm" : {
                    taskChanger.setValue(args.get(0));
                    number.setText(args.get(1));
                    break;
                }
                default : {
                    throw new FileNotFoundException("wrong task");
                }
            }
            compute.fire();

        } catch (IOException e) {
            LOGGER.log(Level.WARNING,e.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Can't load file. Try again");
            alert.showAndWait();
        }

    }

    private void configLogger(){
        try {
            LogManager.getLogManager().readConfiguration(getClass().getResourceAsStream("/logger.properties"));
        } catch (IOException e) {
            LOGGER.log(Level.WARNING,e.getMessage());
        }
    }
}
