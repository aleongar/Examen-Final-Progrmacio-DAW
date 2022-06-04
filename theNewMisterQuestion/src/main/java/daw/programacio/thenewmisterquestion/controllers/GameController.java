package daw.programacio.thenewmisterquestion.controllers;

import daw.programacio.thenewmisterquestion.MisterQuestionApplication;
import daw.programacio.thenewmisterquestion.models.PlayerModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class GameController {
    private Stage thisStage;
    private Button[] buttons;
    @FXML
    private Button ctg1;
    @FXML
    private Button ctg2;
    @FXML
    private Button ctg3;
    @FXML
    private Button ctg4;
    @FXML
    private Button ctg5;
    @FXML
    private Button ctg6;
    @FXML
    private Button ctg7;
    @FXML
    private Button ctg8;
    @FXML
    private Button ctg9;
    @FXML
    private Button ctg10;
    @FXML
    private Button ctg11;
    @FXML
    private Button ctg12;
    @FXML
    private Button ctg13;
    @FXML
    private Button ctg14;
    @FXML
    private Button ctg15;
    @FXML
    private RadioButton player1turn;
    @FXML
    private RadioButton player2turn;
    @FXML
    private RadioButton player3turn;
    @FXML
    private RadioButton player4turn;
    @FXML
    private Label player1points;
    @FXML
    private Label player2points;
    @FXML
    private Label player3points;
    @FXML
    private Label player4points;

    public void initialize(Stage stage, PlayerModel[] players){
        thisStage = stage;
        if(players[0] != null){
            player1turn.setVisible(true);
            player1turn.setText(players[0].getName());
            player1points.setText(Integer.toString(players[0].getScore()));
            player1points.setVisible(true);
        }
        if(players[1] != null){
            player2turn.setVisible(true);
            player2turn.setText(players[1].getName());
            player2points.setText(Integer.toString(players[1].getScore()));
            player2points.setVisible(true);
        }
        if(players[2] != null){
            player3turn.setVisible(true);
            player3turn.setText(players[2].getName());
            player3points.setText(Integer.toString(players[2].getScore()));
            player3points.setVisible(true);
        }
        if(players[3] != null){
            player4turn.setVisible(true);
            player4turn.setText(players[3].getName());
            player4points.setText(Integer.toString(players[3].getScore()));
            player4points.setVisible(true);
        }
        createButtonsArray();
        showButtons();
    }

    private void createButtonsArray(){
        buttons = new Button[]{ctg1, ctg2, ctg3, ctg4, ctg5, ctg6, ctg7, ctg8, ctg9,
                ctg10, ctg11, ctg12, ctg13, ctg14, ctg15};
    }

    private void showButtons(){
        for (int i = 0; i < MisterQuestionApplication.categories.size(); i++){
            buttons[i].setText(MisterQuestionApplication.categories.get(i).getName());
            buttons[i].setVisible(true);
        }
    }

    @FXML
    private void question(){
        System.out.println("question");
    }
}
