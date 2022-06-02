package daw.programacio.thenewmisterquestion.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PlayersController {
    private Stage thisStage;
    @FXML
    private Spinner playersValue;

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
    }

    @FXML
    protected void back(){
        System.out.println("back");
        thisStage.close();
    }

    @FXML
    protected void start(){
        System.out.println("start");
    }
}
