package sub.guessnum;

import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;


public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<Data, String> combinationsShow;

    @FXML
    private TableColumn<Data, Integer> guessedShow;

    @FXML
    private TableColumn<Data, Integer> inPlaceShow;

    @FXML
    private TextField inputField;

    @FXML
    private TableView<Data> tableView;

    @FXML
    private TextArea textArea;

    @FXML
    void initialize() {
        assert combinationsShow != null : "fx:id=\"combinationsShow\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert guessedShow != null : "fx:id=\"guessedShow\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert inPlaceShow != null : "fx:id=\"inPlaceShow\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert inputField != null : "fx:id=\"inputField\" was not injected: check your FXML file 'hello-view.fxml'.";
        combinationsShow.setCellValueFactory(new PropertyValueFactory<Data, String>("suggested"));
        guessedShow.setCellValueFactory(new PropertyValueFactory<>("guessed"));
        inPlaceShow.setCellValueFactory(new PropertyValueFactory<>("inPlace"));
        combination = getRandomCombination();
        String test = Arrays.toString(combination);
        textArea.setText(test);

    }
    ObservableList<Data> dataItems = FXCollections.observableArrayList();
    int [] combination = new int [4];
    @FXML
    private void handleKeyPress(KeyEvent event) {
        if (event.getCode().equals(javafx.scene.input.KeyCode.ENTER)) {
            getInput();
        }
    }
    public void getInput(){
        Data current = new Data();
        if(current.setSuggested(inputField.getText())) {
            current.getScore(current.suggested, combination);
            dataItems.add(current);
            tableView.setItems(dataItems);
        } else {
            textArea.setText("your input may contain only 4 non-repeating digits from 0 to 9");
        }

        inputField.clear();
    }
    private int [] getRandomCombination(){
        int[] digits = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        Random rand = new Random();
        for (int i = 0; i < digits.length; i++) {
            int randomIndexToSwap = rand.nextInt(digits.length);
            int temp = digits[randomIndexToSwap];
            digits[randomIndexToSwap] = digits[i];
            digits[i] = temp;
        }
        int [] combination = new int [4];
        for (int i = 0; i < 4; i++) {
            combination[i] = digits[i];
        }
        return combination;
    }


}
