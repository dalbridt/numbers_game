module sub.guessnum {
    requires javafx.controls;
    requires javafx.fxml;


    opens sub.guessnum to javafx.fxml;
    exports sub.guessnum;
}