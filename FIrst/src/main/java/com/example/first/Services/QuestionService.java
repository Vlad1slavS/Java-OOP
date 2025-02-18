package com.example.first.Services;

import com.example.first.Models.Question;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionService {

    private List<Question> questions = new ArrayList<Question>();
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();


    public List<Question> getQuestionsForLanguage(String language, int count) {
        List<Question> questions = loadQuestionsFromFile(language);
        Collections.shuffle(questions);

        return questions.size() > count ? questions.subList(0, count) : questions;
    }

    private List<Question> loadQuestionsFromFile(String language) {
        String fileName = "/questions_" + language.toLowerCase() + ".json";
        try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(fileName), "UTF-8")) {
            Type listType = new TypeToken<ArrayList<Question>>(){}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }



}
