package com.example.questapp;

public class QuestGeneration {

    public static Question generate(){
        Question question1 = new Question();
        Question question2 = new Question();
        Question question3 = new Question();
        question1.setQuestion("Ты потерял память. Принять вызов НЛО?");
        question1.setAnswerOne("Принять вызов");
        question1.setAnswerTwo("Отклонить вызов");
        question2.setQuestion("Ты принял вызов. Поднимаешься на мостик к капитану?");
        question2.setAnswerOne("Подняться на мостик");
        question2.setAnswerTwo("Отказаться подниматься на мостик");
        question3.setQuestion("Ты поднялся на мостик. Ты кто?");
        question3.setAnswerOne("Рассказать правду о себе");
        question3.setAnswerTwo("Солгать о себе");
        Result result1 = new Result(false, "Ты отклонил вызов");
        Result result2 = new Result(false, "Ты не пошел на переговоры");
        Result result3 = new Result(false, "Твою ложь разоблачили");
        Result result4 = new Result(true, "Тебя вернули домой");

        question1.setOne(question2);
        question1.setTwo(result1);
        question2.setOne(question3);
        question2.setTwo(result2);
        question3.setOne(result4);
        question3.setTwo(result3);

        return question1;
    }
}
