package daw.programacio.thenewmisterquestion.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PlayerModel {
    private SimpleIntegerProperty position;
    private SimpleStringProperty name;
    private SimpleIntegerProperty score;
    private static int positionActual = 1;

    public PlayerModel(String name, int score) {
        this.position = new SimpleIntegerProperty(positionActual);
        this.name = new  SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(score);
        positionActual++;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getScore() {
        return score.get();
    }

    public SimpleIntegerProperty scoreProperty() {
        return score;
    }

    public void setScore(int score) {
        this.score.set(score);
    }
}
