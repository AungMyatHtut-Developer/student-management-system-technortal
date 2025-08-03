package com.technortal.studentmanagementsystemtechnortal;

import com.technortal.studentmanagementsystemtechnortal.common.CommonUtil;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class StudentManagementSystem extends Application {

    public StudentManagementSystem() {
    }

    public StudentManagementSystem(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        InputStream imageStream = getClass().getResourceAsStream("images/logo.png");
        Image image= new Image(imageStream);
        stage.getIcons().add(image);
        new CommonUtil(stage);

        Scene scene = new Scene(CommonUtil.loadFxmlLayout("login.fxml"));
        CommonUtil.getPrimaryStage().setTitle("Student Management System Technortal");
        CommonUtil.setResizable(false);
        CommonUtil.getPrimaryStage().setScene(scene);
        CommonUtil.getPrimaryStage().show();
    }

}