package com.example.first.Controllers;

import com.example.first.Models.Question;
import com.example.first.Services.QuestionService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

import java.util.List;

public class MainController {

    @FXML
    private Button btnStartTest, btnFinish;
    @FXML
    private Label lblStatus, lblQuestion;
    @FXML
    private VBox vboxAnswers;
    @FXML
    private AnchorPane questionLayer;

    private String selectedLanguage;
    private int currentQuestionIndex = 0;
    private List<Question> questionsForTest;
    private ToggleGroup toggleGroup;
    private QuestionService questionService = new QuestionService();
    private boolean isTestRunning = false;


    @FXML
    public void initialize() {
        questionLayer.setVisible(false);
        btnStartTest.setDisable(true);
        toggleGroup = new ToggleGroup();
    }

    @FXML
    private void handleLanguageSelection(ActionEvent event) {
        if (isTestRunning) {
            showDialog("Нельзя выбирать язык во время теста!");
            return;
        }

        Button btn = (Button) event.getSource();
        selectedLanguage = btn.getText();
        lblStatus.setText("Выбран язык: " + selectedLanguage);
        btnStartTest.setDisable(false);
    }


    @FXML
    private void handleStartTest(ActionEvent event) {
        finishTest();
        if (questionService.getQuestionsForLanguage(selectedLanguage, 1).isEmpty()) {
            System.out.println();
        }
        isTestRunning = true;
        questionLayer.setVisible(true);
        if (selectedLanguage == null) {
            lblStatus.setText("Выберите язык!");
            return;
        }
        questionsForTest = questionService.getQuestionsForLanguage(selectedLanguage, 10);
        currentQuestionIndex = 0;
        showQuestion();
        btnStartTest.setDisable(true);
        btnFinish.setVisible(true);

    }


    @FXML
    private void handleFinish(ActionEvent event) {
        finishTest();
        showQuestion();
    }

    private void finishTest() {
        isTestRunning = false;
        questionLayer.setVisible(false);
        btnFinish.setVisible(false);
        btnStartTest.setDisable(false);
        currentQuestionIndex = 0;
    }


    private void showQuestion() {
        if (currentQuestionIndex >= questionsForTest.size()) {
            lblStatus.setText("Тест завершен!");
            return;
        }

        Question question = questionsForTest.get(currentQuestionIndex);
        lblQuestion.setText((currentQuestionIndex + 1) + ". " + question.getQuestion());

        vboxAnswers.getChildren().clear();
        toggleGroup.getToggles().clear();

        for (String answer : question.getOptions()) {
            RadioButton radioButton = new RadioButton(answer);
            radioButton.setToggleGroup(toggleGroup);
            radioButton.setStyle("-fx-font-size: 16px;");
            radioButton.setPadding(new Insets(5, 0, 5, 0));
            vboxAnswers.getChildren().add(radioButton);
        }

    }

    public void showDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Внимание");
        alert.setHeaderText(null); // можно оставить заголовок пустым или добавить
        alert.setContentText(message);

        // Показываем диалоговое окно и ждем, пока пользователь его закроет
        alert.showAndWait();
    }

    public void handleAnswer(ActionEvent event) {
        if (!isTestRunning) {
            return;
        }

        Toggle selectedToggle = toggleGroup.getSelectedToggle();
        if (selectedToggle == null) {
            return;
        }

        RadioButton radioButton = (RadioButton) selectedToggle;
        String userAnswer = radioButton.getText();
        System.out.println(userAnswer.toLowerCase());
    }
}