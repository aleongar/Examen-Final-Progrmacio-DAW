package daw.programacio.thenewmisterquestion.controllers;

import daw.programacio.thenewmisterquestion.MisterQuestionApplication;
import daw.programacio.thenewmisterquestion.models.PlayerModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;

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
    private String buttonPressed;
    private Stage triviaStage;
    private PlayerModel[] players;
    private ToggleGroup tg;
    private int turns;

    public void initialize(Stage stage, PlayerModel[] players, int turns){
        thisStage = stage;
        this.players = players;
        tg = new ToggleGroup();
        initializePlayers();
        initializeButtons();
        showButtons();
        this.turns = turns;
    }

    private void initializePlayers(){
        if(players[0] != null){
            player1turn.setVisible(true);
            player1turn.setText(players[0].getName());
            player1points.setText(Integer.toString(players[0].getScore()));
            player1points.setVisible(true);
            if(tg.getSelectedToggle() == null)
                player1turn.setSelected(true);
        }
        if(players[1] != null){
            player2turn.setVisible(true);
            player2turn.setText(players[1].getName());
            player2points.setText(Integer.toString(players[1].getScore()));
            player2points.setVisible(true);
            if(tg.getSelectedToggle() == null)
                player2turn.setSelected(true);
        }
        if(players[2] != null){
            player3turn.setVisible(true);
            player3turn.setText(players[2].getName());
            player3points.setText(Integer.toString(players[2].getScore()));
            player3points.setVisible(true);
            if(tg.getSelectedToggle() == null)
                player3turn.setSelected(true);
        }
        if(players[3] != null){
            player4turn.setVisible(true);
            player4turn.setText(players[3].getName());
            player4points.setText(Integer.toString(players[3].getScore()));
            player4points.setVisible(true);
            if(tg.getSelectedToggle() == null)
                player4turn.setSelected(true);
        }
        initializeRadioButtons();
    }

    private void initializeRadioButtons(){

        player1turn.setToggleGroup(tg);
        player2turn.setToggleGroup(tg);
        player3turn.setToggleGroup(tg);
        player4turn.setToggleGroup(tg);
    }

    private void initializeButtons(){
        buttons = new Button[]{ctg1, ctg2, ctg3, ctg4, ctg5, ctg6, ctg7, ctg8, ctg9,
                ctg10, ctg11, ctg12, ctg13, ctg14, ctg15};
        for(Button button: buttons){
            button.setOnMouseEntered((mouseEvent) -> {
                buttonPressed = button.getText();
            });
        }
    }

    private void showButtons(){
        for (int i = 0; i < MisterQuestionApplication.categories.size(); i++){
            buttons[i].setText(MisterQuestionApplication.categories.get(i).getName());
            buttons[i].setVisible(true);
        }
    }

    @FXML
    private void question(){
        System.out.println(buttonPressed);
        System.out.println("play");
        triviaStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MisterQuestionApplication.class
                .getResource("views/trivia-view.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            ((TriviaController)fxmlLoader.getController()).initialize(getPlayerPlaying(), triviaStage, buttonPressed);
        } catch (IOException e) {
            e.printStackTrace();
        }
        triviaStage.setTitle("Stage");
        triviaStage.setScene(scene);
        triviaStage.show();
        triviaStage.setOnHidden((windowEvent) -> {
            thisStage.show();
            checkRadioButton((RadioButton) tg.getSelectedToggle());
        });
        thisStage.hide();
    }

    private void checkRadioButton(RadioButton rb) {
        if (rb == player1turn && player1turn.isSelected()) {
            if (player2turn.isVisible()) {
                player2turn.setSelected(true);
            } else if (player3turn.isVisible()) {
                player3turn.setSelected(true);
            } else if (player4turn.isVisible() && !player4turn.isDisable()) {
                player4turn.setSelected(true);
            }
            player1turn.setSelected(false);
            player1turn.setDisable(true);
        }
        else if (rb == player2turn && player2turn.isSelected()) {
            if (player1turn.isVisible()) {
                player1turn.setSelected(true);
            } else if (player3turn.isVisible()) {
                player3turn.setSelected(true);
            } else if (player4turn.isVisible()) {
                player4turn.setSelected(true);
            }
            player2turn.setSelected(false);
            player2turn.setDisable(true);
        } else if (rb == player3turn && player3turn.isSelected()) {
            if (player1turn.isVisible()) {
                player1turn.setSelected(true);
            } else if (player2turn.isVisible()) {
                player2turn.setSelected(true);
            } else if (player4turn.isVisible()) {
                player4turn.setSelected(true);
            }
            player3turn.setSelected(false);
            player3turn.setDisable(true);
        } else if (rb == player4turn && player4turn.isSelected()) {
            if (player1turn.isVisible()) {
                player1turn.setSelected(true);
            } else if (player2turn.isVisible()) {
                player2turn.setSelected(true);
            } else if (player3turn.isVisible()) {
                player3turn.setSelected(true);
            }
            player4turn.setSelected(false);
            player4turn.setDisable(true);
        } else{
            turns--;
            if(turns == 0){
                triviaStage.close();
            }else{
                setAllEnabled();
            }
        }
    }

    private void setAllEnabled(){
        if(player1turn.isVisible()){
            player1turn.setDisable(false);
            player1turn.setSelected(true);
        }
        if(player2turn.isVisible()){
            player2turn.setDisable(false);
            if(!player1turn.isVisible())
                player2turn.setSelected(true);
        }
        if(player3turn.isVisible()){
            player3turn.setDisable(false);
            if(!player2turn.isVisible())
                player2turn.setSelected(true);
        }
        if(player4turn.isVisible()){
            player4turn.setDisable(false);
            if(!player3turn.isVisible())
                player2turn.setSelected(true);
        }
    }

    private PlayerModel getPlayerPlaying(){
        if(player1turn.isSelected())
            return players[0];
        if(player2turn.isSelected())
            return players[1];
        if(player3turn.isSelected())
            return players[2];
        if(player4turn.isSelected())
            return players[3];
        return null;
    }

}
