package daw.programacio.thenewmisterquestion.controllers;

import daw.programacio.thenewmisterquestion.MisterQuestionApplication;
import daw.programacio.thenewmisterquestion.data.DBFacade;
import daw.programacio.thenewmisterquestion.models.CategoryModel;
import daw.programacio.thenewmisterquestion.models.QuestionModel;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class QuestionController {
    private boolean update;
    private boolean saving;
    private Stage thisStage;
    @FXML
    private TextField questionTextField;

    @FXML
    private TextField valueTextField;

    @FXML
    private TextField ansATextField;

    @FXML
    private TextField ansBTextField;

    @FXML
    private TextField ansCTextField;

    @FXML
    private RadioButton ARadioButton;

    @FXML
    private RadioButton BRadioButton;

    @FXML
    private RadioButton CRadioButton;

    @FXML
    private ComboBox categoriesComboBox;
    private QuestionModel question;


    public void initialize(Stage stage){
        thisStage = stage;
        ToggleGroup tg = new ToggleGroup();
        ARadioButton.setToggleGroup(tg);
        BRadioButton.setToggleGroup(tg);
        CRadioButton.setToggleGroup(tg);
        for(CategoryModel c : MisterQuestionApplication.categories)
            categoriesComboBox.getItems().add(c.getName());
        update = false;
        saving = false;
    }

    public void initialize(Stage stage,QuestionModel question){
        initialize(stage);
        this.question = question;
        update = true;
        questionTextField.setText(question.getQuestion());
        valueTextField.setText(Integer.toString(question.getValue()));
        ansATextField.setText(question.getAnswerA());
        ansBTextField.setText(question.getAnswerB());
        ansCTextField.setText(question.getAnswerC());
        if(question.getCorrectAnswer().compareTo("1") == 0)
            ARadioButton.setSelected(true);
        else if(question.getCorrectAnswer().compareTo("2") == 0)
            BRadioButton.setSelected(true);
        else
            CRadioButton.setSelected(true);
        categoriesComboBox.getSelectionModel().select(question.getCategory());
    }

    @FXML
    protected void save(){
        if(update) {
            DBFacade.deleteQuestion(question.getQuestion());
            MisterQuestionApplication.questions.remove(question);
        }
        int id = DBFacade.getCategoryId((String)categoriesComboBox.getSelectionModel().getSelectedItem());
        System.out.println(id);
        DBFacade.addQuestion(questionTextField.getText(), ansATextField.getText(),
                ansBTextField.getText(), ansCTextField.getText(), ARadioButton.isSelected() ? 1 : BRadioButton.isSelected() ? 2 : 3,
                id, Integer.parseInt(valueTextField.getText()));
        MisterQuestionApplication.questions.add(new QuestionModel(1 ,questionTextField.getText(), ansATextField.getText(),
                ansBTextField.getText(), ansCTextField.getText(), ARadioButton.isSelected() ? "1" : BRadioButton.isSelected() ? "2" : "3",
                (String)categoriesComboBox.getSelectionModel().getSelectedItem(), Integer.parseInt(valueTextField.getText())));
        System.out.println("saving");
        saving = true;
        thisStage.close();
    }

    public boolean isSaving() {
        return saving;
    }

    @FXML
    protected void cancel(){
        System.out.println("backing");
        saving = false;
        thisStage.close();
    }
}
