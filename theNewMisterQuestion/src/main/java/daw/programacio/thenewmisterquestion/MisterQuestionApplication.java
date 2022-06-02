package daw.programacio.thenewmisterquestion;

import daw.programacio.thenewmisterquestion.controllers.MainController;
import daw.programacio.thenewmisterquestion.data.DBFacade;
import daw.programacio.thenewmisterquestion.models.CategoryModel;
import daw.programacio.thenewmisterquestion.models.PlayerModel;
import daw.programacio.thenewmisterquestion.models.QuestionModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MisterQuestionApplication extends Application {
    public static ArrayList<CategoryModel> categories;
    public static ArrayList<QuestionModel> questions;
    public static ArrayList<PlayerModel> users;

    @Override
    public void start(Stage stage) throws IOException {
        if(DBFacade.logOn()) {
            System.out.println("Connexion established");
            categories = DBFacade.selectCategories();
            questions =  DBFacade.selectQuestions();
            users = DBFacade.selectPlayers();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        ((MainController)fxmlLoader.getController()).setThisStage(stage);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}