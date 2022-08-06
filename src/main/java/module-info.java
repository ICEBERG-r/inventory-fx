module com.mwilson.partsinventory {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mwilson.partsinventory to javafx.fxml;
    exports com.mwilson.partsinventory;
}