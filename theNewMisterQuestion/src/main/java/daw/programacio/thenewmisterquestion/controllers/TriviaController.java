package daw.programacio.thenewmisterquestion.controllers;

import daw.programacio.thenewmisterquestion.MisterQuestionApplication;
import daw.programacio.thenewmisterquestion.data.DBFacade;
import daw.programacio.thenewmisterquestion.models.PlayerModel;
import daw.programacio.thenewmisterquestion.models.QuestionModel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TriviaController {

    @FXML
    private Label questionLabel;

    @FXML
    private ComboBox answersBox;


    public QuestionModel questionModel;
    public PlayerModel playerModel;
    public Stage thisStage;

    @FXML
    protected void accept(){
        if(questionModel.getCorrectAnswer().compareTo
                (Integer.toString(answersBox.getSelectionModel().getSelectedIndex() + 1)) == 0){
            playerModel.setScore(playerModel.getScore() + questionModel.getValue());
            MisterQuestionApplication.users.remove(playerModel);
            MisterQuestionApplication.users.add(playerModel);
            DBFacade.updateScore(playerModel);
        }
        thisStage.close();
    }

    public void initialize(PlayerModel p, Stage stage, String category){
        playerModel = p;
        questionModel = DBFacade.getRandomQuestionFromCategory(category);
        thisStage = stage;
        questionLabel.setText(questionModel.getQuestion());
        answersBox.getItems().add(questionModel.getAnswerA());
        answersBox.getItems().add(questionModel.getAnswerB());
        answersBox.getItems().add(questionModel.getAnswerC());
    }
}
