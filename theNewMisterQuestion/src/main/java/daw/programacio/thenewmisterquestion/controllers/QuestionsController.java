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
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;

public class QuestionsController {
    private Stage questionStage;
    private Stage thisStage;

    private TableColumn questionColumn;

    private TableColumn categoryColumn;

    private TableColumn usageColumn;
    @FXML
    private TableView tableView;

    private ObservableList<QuestionModel> questions;

    private int index;


    public void initialize(Stage stage) {
        thisStage = stage;
        System.out.println("hola");
        questionColumn = new TableColumn("Pregunta");
        categoryColumn = new TableColumn("Categoria");
        usageColumn = new TableColumn("Usos");
        tableView.getColumns().clear();
        questions = FXCollections.observableArrayList();
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
        updateList();
        index = tableView.getSelectionModel().getSelectedIndex();
        tableView.setOnMouseClicked((mouseEvent)->{
            if(mouseEvent.getButton() == MouseButton.PRIMARY){
                if(tableView.getSelectionModel().getSelectedItem() != null){
                    index = tableView.getSelectionModel().getSelectedIndex();
                }
            }
        });
    }

    private void updateList(){
        questions.clear();
        for (QuestionModel q : MisterQuestionApplication.questions) {
            questions.add(q);
        }
        tableView.setItems(questions);
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
                ((QuestionController)fxmlLoader.getController())
                        .initialize(questionStage ,MisterQuestionApplication.questions.get(index));
            } else{
                ((QuestionController)fxmlLoader.getController())
                        .initialize(questionStage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        questionStage.setTitle("Questions");
        questionStage.setScene(scene);
        questionStage.show();
        questionStage.setOnHidden((windowEvent) -> {
            if(((QuestionController)fxmlLoader.getController()).isSaving()){
                updateList();
            }
        });
    }

    @FXML
    protected void deleteQuestion(){
        System.out.println("Delete question");
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            DBFacade.deleteQuestion(MisterQuestionApplication.questions.get(index).getQuestion());
            MisterQuestionApplication.questions.remove(index);
        }
        updateList();
    }

    @FXML
    protected void  goToCategories(){
        System.out.println("Categories");
        questionStage = new Stage();
        System.out.println("Add question");
        System.out.println("questions");
        FXMLLoader fxmlLoader = new FXMLLoader(MisterQuestionApplication.class
                .getResource("views/categories-view.fxml"));
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
        thisStage.close();
    }
}
