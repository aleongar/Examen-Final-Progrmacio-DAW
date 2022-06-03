package daw.programacio.thenewmisterquestion.models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PlayerModel {
    private SimpleIntegerProperty position;
    private SimpleStringProperty name;
    private SimpleIntegerProperty score;
    private static int positionActual = 0;

    public PlayerModel(String name, int score) {
        PlayerModel.positionActual++;
        int tempPosition = PlayerModel.positionActual;
        this.position = new SimpleIntegerProperty(tempPosition);
        this.name = new  SimpleStringProperty(name);
        this.score = new SimpleIntegerProperty(score);
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

    public int getPosition() {
        return position.get();
    }

    public SimpleIntegerProperty positionProperty() {
        return position;
    }

    public void setPosition(int position) {
        this.position.set(position);
    }
}
