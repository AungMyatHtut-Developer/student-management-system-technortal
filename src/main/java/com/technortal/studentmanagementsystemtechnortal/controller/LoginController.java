package com.technortal.studentmanagementsystemtechnortal.controller;

import com.technortal.studentmanagementsystemtechnortal.common.CommonUtil;
import com.technortal.studentmanagementsystemtechnortal.dao.StudentDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    private StudentDao studentDao = new StudentDao();

    @FXML
    void loginBtnClicked(ActionEvent event) {
        if (usernameField.getText().contentEquals("admin") &&
                passwordField.getText().contentEquals("0000")) {
            CommonUtil.setResizable(true);
            CommonUtil.getPrimaryStage().setScene(new Scene(CommonUtil.loadFxmlLayout("home.fxml")));
        }
    }

}
