package daw.programacio.thenewmisterquestion.controllers;

import daw.programacio.thenewmisterquestion.MisterQuestionApplication;
import daw.programacio.thenewmisterquestion.data.DBFacade;
import daw.programacio.thenewmisterquestion.models.CategoryModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CategoryController {

    public void initialize(){
        updateTable();
    }

    private void updateTable(){
        categoriesListView.getItems().clear();
        for(CategoryModel c: MisterQuestionApplication.categories){
            categoriesListView.getItems().add(c.getName());
        }
    }

    @FXML
    private ListView categoriesListView;

    @FXML
    private TextField categoryTextField;
    private int index;

    @FXML
    protected void add(){
        if(categoryTextField.getText().compareTo("") != 0){
            System.out.println("add");
            DBFacade.addCategory(categoryTextField.getText());
            MisterQuestionApplication.categories.add(new CategoryModel(1, categoryTextField.getText()));
        }
        updateTable();
    }

    @FXML
    protected void delete(){
        if(categoriesListView.getSelectionModel().getSelectedItem() != null) {
            System.out.println("delete");
            DBFacade.deleteCategory(categoryTextField.getText());
            MisterQuestionApplication.categories.remove(categoriesListView.getSelectionModel().getSelectedIndex());
        }
        updateTable();
    }

    @FXML
    protected void back(){
        System.out.println("back");
    }

    @FXML
    protected void selectIndex(){
        if(categoriesListView.getSelectionModel().getSelectedItem() != null){
            index = categoriesListView.getSelectionModel().getSelectedIndex();
            categoryTextField.setText(MisterQuestionApplication.categories.get(index).getName());
        }
    }
}
