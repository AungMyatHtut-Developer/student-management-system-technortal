module com.technortal.studentmanagementsystemtechnortal {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.technortal.studentmanagementsystemtechnortal to javafx.fxml;
    exports com.technortal.studentmanagementsystemtechnortal;
}