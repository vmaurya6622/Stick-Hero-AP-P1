module com.mycompany.vishal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires junit;

    opens com.mycompany.vishal to javafx.fxml;
    exports com.mycompany.vishal;
}
