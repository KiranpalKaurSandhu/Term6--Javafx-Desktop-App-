module com.example.term6project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.term6project to javafx.fxml;
    exports com.example.term6project;
}