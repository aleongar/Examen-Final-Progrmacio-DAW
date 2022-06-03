package daw.programacio.thenewmisterquestion.models;

import daw.programacio.thenewmisterquestion.MisterQuestionApplication;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class QuestionModel {
    private SimpleIntegerProperty id;
    private SimpleStringProperty question;
    private SimpleStringProperty answerA;
    private SimpleStringProperty answerB;
    private SimpleStringProperty answerC;
    private SimpleStringProperty correctAnswer;
    private SimpleStringProperty category;
    private SimpleIntegerProperty value;
    private SimpleIntegerProperty usage = new SimpleIntegerProperty(0);

    public QuestionModel(int id, String question, String answerA, String answerB, String answerC, String correctAnswer, String category, int value) {
        this.id = new SimpleIntegerProperty(id);
        this.question = new SimpleStringProperty(question);
        this.answerA = new SimpleStringProperty(answerA);
        this.answerB = new SimpleStringProperty(answerB);
        this.answerC = new SimpleStringProperty(answerC);
        this.correctAnswer = new SimpleStringProperty(correctAnswer);
        this.category = new SimpleStringProperty(category);
        this.value = new SimpleIntegerProperty(value);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getQuestion() {
        return question.get();
    }

    public SimpleStringProperty questionProperty() {
        return question;
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public String getAnswerA() {
        return answerA.get();
    }

    public SimpleStringProperty answerAProperty() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA.set(answerA);
    }

    public String getAnswerB() {
        return answerB.get();
    }

    public SimpleStringProperty answerBProperty() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB.set(answerB);
    }

    public String getAnswerC() {
        return answerC.get();
    }

    public SimpleStringProperty answerCProperty() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC.set(answerC);
    }

    public String getCorrectAnswer() {
        return correctAnswer.get();
    }

    public SimpleStringProperty correctAnswerProperty() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer.set(correctAnswer);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public int getValue() {
        return value.get();
    }

    public SimpleIntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }
}
