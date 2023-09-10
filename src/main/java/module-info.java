module com.example.mvcepic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.mvcepic to javafx.fxml;
    exports com.example.mvcepic;
}