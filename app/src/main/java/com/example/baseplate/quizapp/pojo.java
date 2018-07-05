package com.example.baseplate.quizapp;

public class pojo {

    String question, answer;
    String[] options;

    public pojo(String question, String answer, String[] options) {
        this.question = question;
        this.answer = answer;
        this.options = options;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getOptions(int i) {
        switch (i) {
            case 0:
                return options[0];
            case 1:
                return options[1];
            case 2:
                return options[2];
            case 3:
                return options[3];

            default:
                break;
        }
        return "Error";
    }

}