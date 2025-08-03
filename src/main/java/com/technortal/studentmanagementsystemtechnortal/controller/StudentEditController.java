package com.technortal.studentmanagementsystemtechnortal.controller;

import com.technortal.studentmanagementsystemtechnortal.dao.StudentDao;
import com.technortal.studentmanagementsystemtechnortal.entity.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StudentEditController {

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button saveBtn;

    private Student student;
    private StudentDao studentDao = new StudentDao();

    @FXML
    void cancelBtnClicked(ActionEvent event) {
        ((Stage) cancelBtn.getScene().getWindow()).close();
    }

    @FXML
    void saveBtnClicked(ActionEvent event) {
        student.setName(nameTextField.getText());
        student.setAddress(addressTextField.getText());
        student.setAge(Integer.parseInt(ageTextField.getText()));
        studentDao.updateStudent(student);

        ((Stage) saveBtn.getScene().getWindow()).close();
    }

    public void giveStudent(Student student) {
        this.student = student;
        nameTextField.setText(student.getName());
        ageTextField.setText(String.valueOf(student.getAge()));
        addressTextField.setText(student.getAddress());
    }
}
