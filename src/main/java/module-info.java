module com.technortal.studentmanagementsystemtechnortal {
    requires javafx.controls;
    requires javafx.fxml;
    requires spring.jdbc;
    requires com.zaxxer.hikari;
    requires static lombok;
    requires java.rmi;

    opens com.technortal.studentmanagementsystemtechnortal to javafx.fxml;
    exports com.technortal.studentmanagementsystemtechnortal;
    exports com.technortal.studentmanagementsystemtechnortal.controller;
    exports com.technortal.studentmanagementsystemtechnortal.entity;
    opens com.technortal.studentmanagementsystemtechnortal.controller to javafx.fxml;
}