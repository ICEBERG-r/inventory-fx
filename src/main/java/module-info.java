module com.mwilson.partsinventory {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mwilson.inventoryfx to javafx.fxml;
    exports com.mwilson.inventoryfx;
}