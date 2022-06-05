package daw.programacio.thenewmisterquestion.controllers;

import daw.programacio.thenewmisterquestion.MisterQuestionApplication;
import daw.programacio.thenewmisterquestion.data.DBFacade;
import daw.programacio.thenewmisterquestion.models.PlayerModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PlayersController {
    private Stage thisStage;
    private Stage playStage;

    @FXML
    private Spinner turnsSpinner;

    @FXML
    private TextField player1TextField;

    @FXML
    private TextField player2TextField;

    @FXML
    private TextField player3TextField;

    @FXML
    private TextField player4TextField;

    public void initialize(Stage stage){
        thisStage = stage;
        playStage = new Stage();
        turnsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 6, 1));
    }
    @FXML
    protected void back(){
        System.out.println("back");
        thisStage.close();
    }

    @FXML
    protected void start() {
        System.out.println("start");
        FXMLLoader fxmlLoader = new FXMLLoader(MisterQuestionApplication.class.getResource("views/game-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ((GameController) fxmlLoader.getController()).initialize(playStage, getPlayers(), (int)turnsSpinner.getValue());
        playStage.setTitle("Hello!");
        playStage.setScene(scene);
        playStage.show();
    }

    private PlayerModel[] getPlayers(){
        PlayerModel[] players = new PlayerModel[4];
        if(player1TextField.getText().compareTo("") == 0){
            players[0] = null;
        }
        else{
            players[0] = DBFacade.getPlayer(player1TextField.getText());
            if(players[0] == null){
                MisterQuestionApplication.users.add(new PlayerModel(player1TextField.getText(), 0));
                DBFacade.addPlayer(player1TextField.getText(), 0);
                players[0] = MisterQuestionApplication.users.get(MisterQuestionApplication.users.size() - 1);
            }
        }
        if(player2TextField.getText().compareTo("") == 0){
            players[1] = null;
        }
        else{
            players[1] = DBFacade.getPlayer(player2TextField.getText());
            if(players[1] == null){
                MisterQuestionApplication.users.add(new PlayerModel(player2TextField.getText(), 0));
                DBFacade.addPlayer(player2TextField.getText(), 0);
                players[1] = MisterQuestionApplication.users.get(MisterQuestionApplication.users.size() - 1);
            }
        }
        if(player3TextField.getText().compareTo("") == 0){
            players[2] = null;
        }
        else{
            players[2] = DBFacade.getPlayer(player3TextField.getText());
            if(players[2] == null){
                MisterQuestionApplication.users.add(new PlayerModel(player3TextField.getText(), 0));
                DBFacade.addPlayer(player3TextField.getText(), 0);
                players[2] = MisterQuestionApplication.users.get(MisterQuestionApplication.users.size() - 1);
            }
        }
        if(player4TextField.getText().compareTo("") == 0){
            players[3] = null;
        }
        else{
            players[3] = DBFacade.getPlayer(player4TextField.getText());
            if(players[3] == null){
                MisterQuestionApplication.users.add(new PlayerModel(player4TextField.getText(), 0));
                DBFacade.addPlayer(player1TextField.getText(), 0);
                players[3] = MisterQuestionApplication.users.get(MisterQuestionApplication.users.size() - 1);
            }
        }
        return players;
    }
}
