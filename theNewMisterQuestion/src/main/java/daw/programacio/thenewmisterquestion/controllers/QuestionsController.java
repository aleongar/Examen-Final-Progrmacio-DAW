package daw.programacio.thenewmisterquestion.controllers;

import daw.programacio.thenewmisterquestion.MisterQuestionApplication;
import daw.programacio.thenewmisterquestion.data.DBFacade;
import daw.programacio.thenewmisterquestion.models.*;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class QuestionsController {
    private Stage questionStage;

    private TableColumn questionColumn;

    private TableColumn categoryColumn;

    private TableColumn usageColumn;
    @FXML
    private TableView tableView;

    private ObservableList<QuestionModel> questions;

    private int index;


    public void initialize() {
        System.out.println("hola");
        questionColumn = new TableColumn("Pregunta");
        categoryColumn = new TableColumn("Categoria");
        usageColumn = new TableColumn("Usos");
        tableView.getColumns().clear();
        questions = FXCollections.observableArrayList();
        for (QuestionModel q : MisterQuestionApplication.questions) {
            questions.add(q);
            System.out.println(q.getQuestion());
        }
        questionColumn.setCellValueFactory(
                new PropertyValueFactory<QuestionModel, String>("question")
        );
        categoryColumn.setCellValueFactory(
                new PropertyValueFactory<QuestionModel, String>("category")
        );
        usageColumn.setCellValueFactory(
                new PropertyValueFactory<QuestionModel, Integer>("usage")
        );
        tableView.getColumns().addAll(questionColumn, categoryColumn, usageColumn);
        tableView.setItems(questions);
        index = tableView.getSelectionModel().getSelectedIndex();
        tableView.setOnMouseClicked((mouseEvent)->{
            if(mouseEvent.getButton() == MouseButton.PRIMARY){
                if(tableView.getSelectionModel().getSelectedItem() != null){
                    index = tableView.getSelectionModel().getSelectedIndex();
                }
            }
        });
    }

    @FXML
    protected void addQuestion(){
        questionStage = new Stage();
        System.out.println("Add question");
        System.out.println("questions");
        FXMLLoader fxmlLoader = new FXMLLoader(MisterQuestionApplication.class
                .getResource("views/question-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            if(tableView.getSelectionModel().getSelectedItem() != null){
                ((QuestionController)fxmlLoader.getController()).initialize(MisterQuestionApplication.questions.get(index));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        questionStage.setTitle("Questions");
        questionStage.setScene(scene);
        questionStage.show();
    }

    @FXML
    protected void deleteQuestion(){
        System.out.println("Delete question");
        DBFacade.deleteQuestion(MisterQuestionApplication.questions.get(index).getQuestion());
    }

    @FXML
    protected void  goToCategories(){
        System.out.println("Categories");
        questionStage = new Stage();
        System.out.println("Add question");
        System.out.println("questions");
        FXMLLoader fxmlLoader = new FXMLLoader(MisterQuestionApplication.class
                .getResource("views/question-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            if(tableView.getSelectionModel().getSelectedItem() != null){
                ((CategoryController)fxmlLoader.getController()).initialize();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        questionStage.setTitle("Categories");
        questionStage.setScene(scene);
        questionStage.show();
    }

    @FXML
    protected void backToMain(){
        System.out.println("main");
    }
}
