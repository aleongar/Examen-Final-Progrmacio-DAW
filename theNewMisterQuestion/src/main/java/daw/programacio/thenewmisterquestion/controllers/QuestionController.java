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

public class QuestionController {
    private boolean update;
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

    public void initialize(){
        ToggleGroup tg = new ToggleGroup();
        ARadioButton.setToggleGroup(tg);
        BRadioButton.setToggleGroup(tg);
        CRadioButton.setToggleGroup(tg);
        for(CategoryModel c : MisterQuestionApplication.categories)
            categoriesComboBox.getItems().add(c);
        update = false;
    }

    public void initialize(QuestionModel question){
        initialize();
        update = true;
        categoriesComboBox.getSelectionModel().select(question.getCategory());
    }

    @FXML
    protected void save(){
        if(update)
            DBFacade.deleteQuestion(questionTextField.getText());
        DBFacade.addQuestion(questionTextField.getText(), ansATextField.getText(),
                ansBTextField.getText(), ansCTextField.getText(), ARadioButton.isSelected() ? 1 : BRadioButton.isSelected() ? 2 : 3, (String)categoriesComboBox.getSelectionModel().getSelectedItem(), Integer.parseInt(valueTextField.getText()));
        System.out.println("saving");
    }

    @FXML
    protected void delete(){
        System.out.println("deleting");
    }

    @FXML
    protected void cancel(){
        System.out.println("backing");
    }
}
