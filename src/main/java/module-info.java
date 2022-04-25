module com.intrograph {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens com.intrograph to javafx.fxml;
    exports com.intrograph;
}
