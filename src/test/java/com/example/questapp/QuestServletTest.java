package com.example.questapp;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuestServletTest {
    QuestServlet questServlet;
    HttpServletRequest request;
    HttpServletResponse response;
    ServletConfig servletConfig;
    ServletContext servletContext;
    RequestDispatcher requestDispatcher;
    Statistic statistic;
    HttpSession session;

    @BeforeEach
    @SneakyThrows
    void init() {
        questServlet = new QuestServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servletConfig = mock(ServletConfig.class);
        servletContext = mock(ServletContext.class);
        requestDispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
        statistic = new Statistic();
        statistic.setCount(0);
        statistic.setName("Name");
        statistic.setIpAddress("127.0.0.1");

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("statistic")).thenReturn(statistic);
        when(servletConfig.getServletContext()).thenReturn(servletContext);
        doNothing().when(requestDispatcher).forward(request, response);

        questServlet.init(servletConfig);
    }

    @Test
    @SneakyThrows
    void doPost() {
        questServlet.init(servletConfig);
        when(request.getRemoteAddr()).thenReturn("localhost");
        when(request.getParameter("name")).thenReturn("test");
        when(servletContext.getRequestDispatcher("/quest.jsp")).thenReturn(requestDispatcher);
        questServlet.doPost(request, response);

        verify(response).setContentType("text/html");
        verify(session).setAttribute(eq("question"), any(Question.class));
        verify(session).setAttribute(eq("statistic"), any(Statistic.class));
    }

    @Test
    @SneakyThrows
    void doGet() {
        when(servletContext.getRequestDispatcher("/quest.jsp")).thenReturn(requestDispatcher);

        questServlet.init(servletConfig);
        questServlet.doGet(request, response);

        verify(response).setContentType("text/html");
        verify(session).setAttribute(eq("question"), any(Question.class));
        verify(session).setAttribute("statistic", statistic);
        assertEquals(1, statistic.getCount());
    }
}