package com.example.questapp;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/quest")
@Slf4j
public class QuestServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        if (request.getSession().getAttribute("question") == null) {
            log.info("Generation quest");
            setQuestionToSession(request, QuestGeneration.generate());
            setStatisticToSession(request);
            getServletContext().getRequestDispatcher("/quest.jsp").forward(request, response);
        } else if (request.getParameter("answer") != null) {
            Question question = (Question) request.getSession().getAttribute("question");
            Object answer;
            if (request.getParameter("answer").equals("1")) {
                answer = question.getOne();
            } else answer = question.getTwo();
            if (answer instanceof Question) {
                setQuestionToSession(request, (Question) answer);
                getServletContext().getRequestDispatcher("/quest.jsp").forward(request, response);
            } else {
                setResultToSession(request, (Result) answer);
                getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
            }
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        log.info("Start new game");
        setQuestionToSession(request, QuestGeneration.generate());
        incrementGameCount(request);
        getServletContext().getRequestDispatcher("/quest.jsp").forward(request, response);
    }

    private void incrementGameCount(HttpServletRequest request) {
        Statistic statistic = getStatistic(request);
        log.info("Increment game for user {}", statistic.getName());
        statistic.setCount(statistic.getCount() + 1);
        request.getSession().setAttribute("statistic", statistic);
    }

    private void setResultToSession(HttpServletRequest request, Result result) {
        request.getSession().setAttribute("result", result);
    }

    private void setQuestionToSession(HttpServletRequest request, Question question) {
        request.getSession().setAttribute("question", question);
    }

    private Statistic getStatistic(HttpServletRequest request) {
        return (Statistic) request.getSession().getAttribute("statistic");
    }

    private void setStatisticToSession(HttpServletRequest request) {
        Statistic statistic = new Statistic();
        statistic.setIpAddress(request.getRemoteAddr());
        statistic.setName(request.getParameter("name"));
        request.getSession().setAttribute("statistic", statistic);
    }
}