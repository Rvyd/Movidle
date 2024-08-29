module tr.edu.erciyes8038.movidle {
    requires javafx.controls;
    requires javafx.fxml;


    opens tr.edu.erciyes8038.movidle to javafx.fxml;
    exports tr.edu.erciyes8038.movidle;
}