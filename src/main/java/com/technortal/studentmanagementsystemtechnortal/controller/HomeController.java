package com.technortal.studentmanagementsystemtechnortal.controller;

import com.technortal.studentmanagementsystemtechnortal.dao.StudentDao;
import com.technortal.studentmanagementsystemtechnortal.entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private TableColumn<Student, Void> actionColumn;

    @FXML
    private Button addNewStudentBtn;

    @FXML
    private TableColumn<Student, String> addressColumn;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, Integer> noColumn;

    @FXML
    private TableColumn<Student, LocalDateTime> createTimeColumn;

    @FXML
    private Button studentMenuBtn;

    @FXML
    private VBox tableParent;

    @FXML
    private TableView<Student> tableView;


    @FXML
    private Button teachersMenuBtn;

    private StudentDao studentDao = new StudentDao();

    @FXML
    void addNewStudentBtnClicked(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/technortal/studentmanagementsystemtechnortal/common/student-create.fxml"));

        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Create Student");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        loadStudents();
    }

    @FXML
    void studentMenuBtnClicked(ActionEvent event) {
        tableParent.getChildren().clear();
        tableParent.getChildren().addAll(addNewStudentBtn,tableView);
    }

    @FXML
    void teachersMenuBtnClicked(ActionEvent event) {

        TableView<String> tableView = new TableView<>();
        TableColumn<String,String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableView.getColumns().add(nameColumn);


        Label label = new Label("Teacher Table");
        tableParent.getChildren().clear();
        tableParent.getChildren().addAll(label,tableView);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        noColumn.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getIndex() >= tableView.getItems().size()) {
                    setText(null);
                } else {
                    setText(String.valueOf(getIndex() + 1));
                }
            }
        });

        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("address"));
        createTimeColumn.setCellValueFactory(new PropertyValueFactory<>("createTime"));
        createTimeColumn.setCellFactory(col -> new TableCell<>() {

            private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                }else{
                    setText(formatter.format(item));
                }
            }
        });

        actionColumn.setCellFactory(col -> new TableCell<>() {

            private Button editButton = new Button("Edit");
            private Button deleteButton = new Button("Delete");
            private HBox hbox = new HBox(20, editButton, deleteButton);

            {
                editButton.setOnAction(event -> {
                    Student student = getTableView().getItems().get(getIndex());
                    try {
                        showEditPanel(student);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                        throw new RuntimeException(e);
                    }
                });

                deleteButton.setOnAction(event -> {
                    Student student = getTableView().getItems().get(getIndex());
                    studentDao.delete(student.getId());
                    loadStudents();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(hbox);
                }
            }
        });

        loadStudents();
    }

    private void loadStudents() {
        List<Student> studentList = studentDao.getAllStudents();
        ObservableList<Student> studentObservableList = FXCollections.observableList(studentList);
        tableView.setItems(studentObservableList);
    }

    private void showEditPanel(Student student) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/technortal/studentmanagementsystemtechnortal/common/student-edit.fxml"));

        Parent parent = fxmlLoader.load();
        StudentEditController studentEditController =  fxmlLoader.getController();
        studentEditController.giveStudent(student);

        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Edit Student");
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        loadStudents();
    }
}
