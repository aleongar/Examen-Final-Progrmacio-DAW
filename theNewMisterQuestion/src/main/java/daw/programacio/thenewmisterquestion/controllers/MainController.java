package daw.programacio.thenewmisterquestion.controllers;

import daw.programacio.thenewmisterquestion.MisterQuestionApplication;
import daw.programacio.thenewmisterquestion.data.DBFacade;
import daw.programacio.thenewmisterquestion.models.PlayerModel;
import daw.programacio.thenewmisterquestion.models.QuestionModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static daw.programacio.thenewmisterquestion.MisterQuestionApplication.users;

public class MainController {
    private Stage thisStage,questionStage,playStage;

    private TableColumn position;

    private TableColumn name;

    private TableColumn points;

    @FXML
    private TableView tableView;

    private ObservableList<PlayerModel> players;

    public void setThisStage(Stage thisStage) {
        this.thisStage = thisStage;
    }

    private void updateList(){
        players.clear();
        for (PlayerModel p : users) {
            players.add(p);
        }
        tableView.setItems(players);
    }

    public void initialize(){
        position = new TableColumn("position");
        name = new TableColumn("name");
        points = new TableColumn("score");
        tableView.getColumns().clear();
        players = FXCollections.observableArrayList();

        position.setCellValueFactory(
                new PropertyValueFactory<PlayerModel, Integer>("position")
        );
        name.setCellValueFactory(
                new PropertyValueFactory<PlayerModel, String>("name")
        );
        points.setCellValueFactory(
                new PropertyValueFactory<PlayerModel, Integer>("score")
        );
        tableView.getColumns().addAll(position, name, points);
        updateList();
        questionStage = new Stage();
        playStage = new Stage();
    }


    @FXML
    protected void  goToQuestions(){
        System.out.println("questions");
        FXMLLoader fxmlLoader = new FXMLLoader(MisterQuestionApplication.class
                .getResource("views/questions-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            ((QuestionsController)fxmlLoader.getController()).initialize(questionStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        questionStage.setTitle("Questions");
        questionStage.setScene(scene);
        questionStage.show();
        questionStage.setOnHidden((windowEvent) -> {
            thisStage.show();
        });
        thisStage.hide();
    }

    @FXML
    protected void goToPlay(){
        System.out.println("play");
        FXMLLoader fxmlLoader = new FXMLLoader(MisterQuestionApplication.class
                .getResource("views/players-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            ((PlayersController)fxmlLoader.getController()).initialize(playStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        playStage.setTitle("Players");
        playStage.setScene(scene);
        playStage.show();
        playStage.setOnHidden((windowEvent) -> {
            thisStage.show();
            updateList();
        });
        thisStage.hide();
    }
}